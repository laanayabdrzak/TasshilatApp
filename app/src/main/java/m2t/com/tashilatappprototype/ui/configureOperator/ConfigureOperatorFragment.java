package m2t.com.tashilatappprototype.ui.configureOperator;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.ui.MainActivity;
import m2t.com.tashilatappprototype.ui.printBills.PrintBillsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureOperatorFragment extends Fragment {

    private ImageView imgOperator;
    private TextView titleLogo;
    private AppCompatButton validateBtn;
    private EditText identifiantEdt, montantEdt, numContratEdt;


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
        montantEdt = (EditText) rootView.findViewById(R.id.input_montant);
        numContratEdt = (EditText) rootView.findViewById(R.id.input_num_contrat);
        validateBtn = (AppCompatButton) rootView.findViewById(R.id.btn_validate);

        if (getArguments() != null && !getArguments().getString("logo_operator").trim().equals("")) {
            int id = getActivity().getResources().getIdentifier("b" + getArguments().getString("logo_operator"),
                    "drawable", getActivity().getPackageName());

            if (id != 0) imgOperator.setImageResource(id);
            titleLogo.setText(getArguments().getString("title_operator"));
        }

        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Fragment fragment = new PrintBillsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("logo_operator", getArguments().getString("logo_operator"));
                    bundle.putString("title_operator", getArguments().getString("title_operator"));
                    fragment.setArguments(bundle);
                    Utils.replaceFragement(fragment, getActivity());
                }
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
        String numContrat = numContratEdt.getText().toString();
        String montant = montantEdt.getText().toString();

        if (identifiant.isEmpty() || identifiant.length() < 4 ) {
            identifiantEdt.setError("Entrer un identifiant valide");
            valid = false;
        } else {
            identifiantEdt.setError(null);
        }

        if (numContrat.isEmpty() || numContrat.length() < 4 ) {
            numContratEdt.setError("Entrer un N° de contrat valide");
            valid = false;
        } else {
            numContratEdt.setError(null);
        }

        if (montant.isEmpty() || montant.length() < 2 || montant.length() > 10) {
            montantEdt.setError("Entrer un montant valide");
            valid = false;
        } else {
            montantEdt.setError(null);
        }



        return valid;
    }
}
