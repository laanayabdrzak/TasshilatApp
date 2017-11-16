package m2t.com.tashilatappprototype.ui.configureOperator;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.Facture;
import m2t.com.tashilatappprototype.common.pojo.FacturieRequest;
import m2t.com.tashilatappprototype.common.pojo.FacturieResponse;
import m2t.com.tashilatappprototype.common.pojo.Req;
import m2t.com.tashilatappprototype.common.pojo.SearchForm;
import m2t.com.tashilatappprototype.common.utils.SessionManager;
import m2t.com.tashilatappprototype.common.utils.Utils;
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
    private EditText identifiantEdt;
    SessionManager sessionManager;
    private String valueSerachCrit;


    public ConfigureOperatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_configure_operator, container, false);
        ((MainActivity) getActivity()).enableViews(true);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.conf_oper_title);
        imgOperator = (ImageView) rootView.findViewById(R.id.img_operator);
        titleLogo = (TextView) rootView.findViewById(R.id.title_logo);
        identifiantEdt = (EditText) rootView.findViewById(R.id.input_identifiant);
        validateBtn = (AppCompatButton) rootView.findViewById(R.id.btn_validate);
        spinnerTypeId = (Spinner) rootView.findViewById(R.id.type_ident_spinner);
        sessionManager = new SessionManager(getActivity().getApplicationContext());
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.type_identifiant_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerTypeId.setAdapter(adapter);
        spinnerTypeId.setOnItemSelectedListener(this);

        if (getArguments() != null && !getArguments().getString("logo_operator").trim().equals("")) {
            int id = getActivity().getResources().getIdentifier("b" + getArguments().getString("logo_operator"),
                    "drawable", getActivity().getPackageName());

            if (id != 0) imgOperator.setImageResource(id);
            titleLogo.setText(getArguments().getString("title_operator"));
        }

        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getListFactures();
                /*Fragment fragment = new ListFacturiesFragment();
                Bundle bundle = new Bundle();
                bundle.putString("logo_operator", getArguments().getString("logo_operator"));
                bundle.putString("title_operator", getArguments().getString("title_operator"));
                fragment.setArguments(bundle);
                Utils.replaceFragement(fragment, getActivity());*/
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((MainActivity) getActivity()).setDrawerLocked(false);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        valueSerachCrit = getResources().getStringArray(R.array.type_identifiant_array_values)[i];
        Toast.makeText(getActivity(), valueSerachCrit, Toast.LENGTH_LONG).show();
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
        FacturieRequest req = new FacturieRequest();
        SearchForm searchForm = new SearchForm();
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
        req.setModPaiement("ESPECE");
        req.setReq(new Req(getArguments().getString("logo_operator")));
        Call<FacturieResponse> call = apiService.getListFactures( req, user.get(SessionManager.KEY_PHPSESSID) + ";"
                + user.get(SessionManager.KEY_COOKIE));
        call.enqueue(new Callback<FacturieResponse>() {
            @Override
            public void onResponse(Call<FacturieResponse> call, Response<FacturieResponse> response) {
                validateBtn.setEnabled(true);
                progressDialog.dismiss();

                Fragment fragment = new ListFacturiesFragment();
                Bundle bundle = new Bundle();
                bundle.putString("logo_operator", getArguments().getString("logo_operator"));
                bundle.putString("title_operator", getArguments().getString("title_operator"));
                bundle.putParcelableArrayList("facturie_list", (ArrayList<? extends Parcelable>) createFakeData());
                fragment.setArguments(bundle);
                Utils.replaceFragement(fragment, getActivity());

                /*if (response.body().getCodeError().equals("0")) {

                    Fragment fragment = new ListFacturiesFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("logo_operator", getArguments().getString("logo_operator"));
                    bundle.putString("title_operator", getArguments().getString("title_operator"));
                    bundle.putParcelable("facturie_list", (Parcelable) createFakeData());
                    fragment.setArguments(bundle);
                    Utils.replaceFragement(fragment, getActivity());
                } else {
                    Toast.makeText(getActivity(), response.body().getMsgError(),
                            Toast.LENGTH_LONG).show();
                }*/

            }

            @Override
            public void onFailure(Call<FacturieResponse> call, Throwable t) {
                validateBtn.setEnabled(true);
                progressDialog.dismiss();
            }
        });
    }

    private List<Facture> createFakeData() {

        List<Facture> list = new ArrayList<>();
        Facture f1 = new Facture();
        f1.setAdresse("sect 12");
        f1.setMntFraisHt("12323");
        f1.setDateLimite("01/09/1233");
        f1.setEcheance("03.93.99");
        Facture f2 = new Facture();
        f1.setAdresse("sect 12");
        f1.setMntFraisHt("12323");
        f1.setDateLimite("01/09/1233");
        f1.setEcheance("03.93.99");
        Facture f3 = new Facture();
        f1.setAdresse("sect 12");
        f1.setMntFraisHt("12323");
        f1.setDateLimite("01/09/1233");
        f1.setEcheance("03.93.99");
        Facture f4 = new Facture();
        f1.setAdresse("sect 12");
        f1.setMntFraisHt("12323");
        f1.setDateLimite("01/09/1233");
        f1.setEcheance("03.93.99");

        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);

        return list;
    }
}
