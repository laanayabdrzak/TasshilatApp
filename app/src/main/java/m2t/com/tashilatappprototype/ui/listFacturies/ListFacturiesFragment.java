package m2t.com.tashilatappprototype.ui.listFacturies;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.Facture;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFacturiesFragment extends Fragment {

    private static final String TAG = ListFacturiesFragment.class.getSimpleName();
    private ArrayList<Facture> factureList;
    private TableLayout tl;

    public ListFacturiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_facturies, container, false);

        tl = (TableLayout)rootView.findViewById(R.id.table_facture);
        Bundle b = this.getActivity().getIntent().getExtras();
        if (b != null) {
            factureList = b.getParcelableArrayList("facturie_list");
            if (factureList!=null  && factureList.size()>0) {
                for (Facture facture : factureList) {
                    TableRow row = new TableRow(this.getActivity());
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    TextView dateLimite = new TextView(this.getActivity());
                    dateLimite.setText(facture.getDateLimite());

                    TextView dateEcheance = new TextView(this.getActivity());
                    dateEcheance.setText(facture.getEcheance());

                    TextView montant = new TextView(this.getActivity());
                    montant.setText(facture.getMntFraisHt());

                    TextView local = new TextView(this.getActivity());
                    local.setText(facture.getAdresse());

                    row.addView(dateLimite);
                    row.addView(dateEcheance);
                    row.addView(montant);
                    row.addView(local);
                    tl.addView(row);
                }
            }
        }

        return rootView;
    }


}
