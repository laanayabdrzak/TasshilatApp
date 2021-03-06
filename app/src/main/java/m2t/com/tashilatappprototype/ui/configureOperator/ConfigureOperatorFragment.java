package m2t.com.tashilatappprototype.ui.configureOperator;


import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.CodeCenReq;
import m2t.com.tashilatappprototype.common.pojo.CodeCenRes;
import m2t.com.tashilatappprototype.common.pojo.FacturieRequest;
import m2t.com.tashilatappprototype.common.pojo.FacturieResponse;
import m2t.com.tashilatappprototype.common.pojo.OperatorFAV;
import m2t.com.tashilatappprototype.common.pojo.Param;
import m2t.com.tashilatappprototype.common.pojo.Req;
import m2t.com.tashilatappprototype.common.pojo.SearchForm;
import m2t.com.tashilatappprototype.common.utils.SessionManager;
import m2t.com.tashilatappprototype.common.utils.Utility;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.data.local.DatabaseHandler;
import m2t.com.tashilatappprototype.data.remote.ApiClient;
import m2t.com.tashilatappprototype.data.remote.ApiInterface;
import m2t.com.tashilatappprototype.ui.MainActivity;
import m2t.com.tashilatappprototype.ui.listFacturies.ListFacturiesFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureOperatorFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ImageView imgOperator;
    private TextView titleLogo;
    private AppCompatButton validateBtn;
    private Spinner spinnerTypeId;
    private Spinner spinnermodPaiement;
    private SearchableSpinner spinnerCodeCen;
    private EditText identifiantEdt;
    private SessionManager sessionManager;
    private DatabaseHandler db;
    private String valueSerachCrit;
    private String codeCenter;
    private String modPaiement;
    HashMap<String, String> data;
    List<String> list;
    private boolean mToolBarNavigationFavorisListenerIsRegistered;


    public ConfigureOperatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_configure_operator, container, false);
        ((MainActivity) getActivity()).enableViews(true);
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.conf_oper_title, R.color.secondColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.secondColor));
        }
        imgOperator = (ImageView) rootView.findViewById(R.id.img_operator);
        titleLogo = (TextView) rootView.findViewById(R.id.title_logo);
        identifiantEdt = (EditText) rootView.findViewById(R.id.input_identifiant);
        validateBtn = (AppCompatButton) rootView.findViewById(R.id.btn_validate);
        spinnerTypeId = (Spinner) rootView.findViewById(R.id.type_ident_spinner);
        spinnermodPaiement = (Spinner) rootView.findViewById(R.id.mod_paiement_spinner);
        spinnerCodeCen = (SearchableSpinner) rootView.findViewById(R.id.code_centre_spinner);
        sessionManager = new SessionManager(getActivity().getApplicationContext());
        db = new DatabaseHandler(getActivity().getApplicationContext());
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.type_identifiant_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerTypeId.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterModPaie = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.mod_paiement_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterModPaie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermodPaiement.setAdapter(adapterModPaie);


        spinnerTypeId.setOnItemSelectedListener(this);
        spinnermodPaiement.setOnItemSelectedListener(this);
        spinnerCodeCen.setOnItemSelectedListener(this);

        spinnerCodeCen.setTitle("Code Centre");
        spinnerCodeCen.setPositiveButton("Cancel");


        Utils.hideKeyboardFrom(getActivity(), rootView.findViewById(R.id.parent));
        rootView.findViewById(R.id.parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.hideKeyboardFrom(getActivity(), rootView.findViewById(R.id.parent));
            }
        });

        if (getArguments() != null && !getArguments().getString("logo_operator").trim().equals("")
                && getArguments().getString("flag").equals("new")) {

            mToolBarNavigationFavorisListenerIsRegistered = false;
            int id = getActivity().getResources().getIdentifier("b" + getArguments().getString("logo_operator"),
                    "drawable", getActivity().getPackageName());

            if (id != 0) imgOperator.setImageResource(id);
            titleLogo.setText(getArguments().getString("title_operator"));

            if (getArguments().get("logo_operator").equals("0013")) {
                spinnerCodeCen.setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.tv_code_centre).setVisibility(View.VISIBLE);
                getCodeCenter();
            }
        }

        if (getArguments() != null && !getArguments().getString("logo_operator").trim().equals("")
                && getArguments().getString("flag").equals("favoris")) {

            int id = getActivity().getResources().getIdentifier("b" + getArguments().getString("logo_operator"),
                    "drawable", getActivity().getPackageName());
            mToolBarNavigationFavorisListenerIsRegistered = true;
            if (id != 0) imgOperator.setImageResource(id);
            titleLogo.setText(getArguments().getString("title_operator"));

            if (getArguments().getString("modPaiement").equals("ESPECE"))
                spinnermodPaiement.setSelection(0);
            else spinnermodPaiement.setSelection(1);

            identifiantEdt.setText(getArguments().getString("ident"));
            if (getArguments().getString("identType").equals("6"))
                spinnerTypeId.setSelection(0);
            else if (getArguments().getString("identType").equals("7"))
                spinnerTypeId.setSelection(1);
            else if (getArguments().getString("identType").equals("3"))
                spinnerTypeId.setSelection(2);
            else if (getArguments().getString("identType").equals("4"))
                spinnerTypeId.setSelection(3);
            else if (getArguments().getString("identType").equals("1"))
                spinnerTypeId.setSelection(4);
        }

        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate())
                    getListFactures();
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_log_out).setVisible(true);
        menu.findItem(R.id.action_favoris).setVisible(true);
        if (getArguments().getString("flag").equals("new")) menu.getItem(1).setIcon(ContextCompat.getDrawable(this.getActivity(), R.drawable.ic_favorite_border_black_24dp));
        else menu.getItem(1).setIcon(ContextCompat.getDrawable(this.getActivity(), R.drawable.ic_favorite_black_24dp));
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_favoris:
                if (!mToolBarNavigationFavorisListenerIsRegistered) {
                    item.setIcon(ContextCompat.getDrawable(this.getActivity(), R.drawable.ic_favorite_black_24dp));
                    addTofavorite();
                    mToolBarNavigationFavorisListenerIsRegistered = true;
                } else {
                    item.setIcon(ContextCompat.getDrawable(this.getActivity(), R.drawable.ic_favorite_border_black_24dp));
                    deleteFromFavoris(getArguments().getString("logo_operator"));
                    mToolBarNavigationFavorisListenerIsRegistered = false;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public boolean validate() {
        boolean valid = true;

        String identifiant = identifiantEdt.getText().toString();

        if (identifiant.isEmpty() || identifiant.length() < 4 ) {
            identifiantEdt.setError("Entrer un identifiant valide");
            valid = false;
        } else {
            identifiantEdt.setError(null);
        }

        return valid;
    }

    private void addTofavorite() {
        OperatorFAV operatorFAV = new OperatorFAV();
        operatorFAV.setID_OPER(getArguments().getString("logo_operator"));
        operatorFAV.setName(getArguments().getString("title_operator"));
        operatorFAV.setIdent(identifiantEdt.getText().toString());
        operatorFAV.setPayment(modPaiement);
        operatorFAV.setTypeIdent(valueSerachCrit);
        operatorFAV.setFavorite(1);

        db.addOperatorFAV(operatorFAV);
    }

    private void deleteFromFavoris(String opID) {
        db.deleteOperatorFAV(opID);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.type_ident_spinner) {
            valueSerachCrit = getResources().getStringArray(R.array.type_identifiant_array_values)[i];
        } else if(spinner.getId() == R.id.code_centre_spinner) {
            codeCenter = list.get(i);//data.get(i);
        } else if (spinner.getId() == R.id.mod_paiement_spinner) {
            modPaiement = getResources().getStringArray(R.array.mod_paiement_array)[i];
            //Toast.makeText(getActivity(), modPaiement, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getListFactures() {
        validateBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(this.getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Patienter SVP...");
        progressDialog.show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> user = sessionManager.getUserDetails();
        final FacturieRequest req = new FacturieRequest();
        SearchForm searchForm = new SearchForm();
        searchForm.setCodecentre("4W1");
        searchForm.setSearchCrit(valueSerachCrit);
        switch (Integer.parseInt(valueSerachCrit)) {

            case 1:
                searchForm.setNocin(identifiantEdt.getText().toString());
                break;
            case 3:
                searchForm.setNotournee(identifiantEdt.getText().toString());
                break;
            case 4:
                searchForm.setNofacture(identifiantEdt.getText().toString());
                break;
            case 6:
                searchForm.setNopolice(identifiantEdt.getText().toString());
                break;
            case 7:
                searchForm.setNocompteur(identifiantEdt.getText().toString());
                break;
        }

        req.setSearchForm(searchForm);
        req.setTokenCSFR(user.get(SessionManager.KEY_TOKEN_CSFR));
        req.setModPaiement(modPaiement);
        req.setReq(new Req(getArguments().getString("logo_operator")));
        Call<FacturieResponse> call = apiService.getListFactures( req, user.get(SessionManager.KEY_PHPSESSID) + ";"
                + user.get(SessionManager.KEY_COOKIE));
        call.enqueue(new Callback<FacturieResponse>() {
            @Override
            public void onResponse(Call<FacturieResponse> call, Response<FacturieResponse> response) {
                validateBtn.setEnabled(true);
                progressDialog.dismiss();

                if (response.body().getCodeError().equals("0")) {

                    Fragment fragment = new ListFacturiesFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("logo_operator", getArguments().getString("logo_operator"));
                    bundle.putString("title_operator", getArguments().getString("title_operator"));
                    bundle.putString("FacturesRef", response.body().getFactureRef());
                    bundle.putString("MntFraisTtc", response.body().getMntFraisTtc());
                    bundle.putString("MntTimbre", response.body().getMntTimbre());
                    //bundle.putString("montantTTC",Utils.montantTTC("", response.body().getMntFraisTtc()));
                    bundle.putParcelableArrayList("facturie_list", (ArrayList<? extends Parcelable>) response.body().getFactureList());
                    fragment.setArguments(bundle);
                    Utility.replaceFragement(fragment, getActivity());
                } else {
                    Toast.makeText(getActivity(), response.body().getMsgError(),
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<FacturieResponse> call, Throwable t) {
                validateBtn.setEnabled(true);
                progressDialog.dismiss();
            }
        });
    }

    private void getCodeCenter() {
        final ProgressDialog progressDialog = new ProgressDialog(this.getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Patienter SVP...");
        progressDialog.show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> user = sessionManager.getUserDetails();
        final CodeCenReq req = new CodeCenReq();
        req.setType("onee_codes_centre");
        req.setCrit("TEST");
        req.setValeCrit("");
        Call<CodeCenRes> call = apiService.getCodeCenter(req, user.get(SessionManager.KEY_PHPSESSID) + ";"
                + user.get(SessionManager.KEY_COOKIE));

        call.enqueue(new Callback<CodeCenRes>() {
            @Override
            public void onResponse(Call<CodeCenRes> call, Response<CodeCenRes> response) {
                progressDialog.dismiss();

                if (response.body().getCodeError().equals("0")) {
                    list = new ArrayList<>();
                    data = new HashMap<>();

                    for (Param param : response.body().getParams()) {
                        Log.d("RESPONSE", param.toString());
                        list.add(param.getLabel());
                        data.put(param.getLabel(), param.getCode());
                    }

                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCodeCen.setAdapter(spinnerAdapter);
                    spinnerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CodeCenRes> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}
