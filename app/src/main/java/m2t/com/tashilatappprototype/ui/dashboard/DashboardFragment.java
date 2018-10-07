package m2t.com.tashilatappprototype.ui.dashboard;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.DashboardPagerAdapter;
import m2t.com.tashilatappprototype.common.pojo.Balance;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private TextView solde;
    private TextView soldeAtempsReelle;
    private List<Balance> listCompt = new ArrayList<>();

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //((MainActivity) getActivity()).enableViews(false);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.accueil_title, R.color.firstColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.firstColor));
        }
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // Inflate the layout for this fragment
        // Initialize the ViewPager and set an adapter
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        solde = (TextView) rootView.findViewById(R.id.solde);
        soldeAtempsReelle = (TextView) rootView.findViewById(R.id.solde_a_temps_reelle);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);


        listCompt.add(new Balance("Compte 1", "1111111", 0, "1200,00 DH"));
        listCompt.add(new Balance("Compte 2", "1332333214", 0, "1300,00 DH"));
        listCompt.add(new Balance("Compte 3", "1332333214", 0, "400,00 DH"));
        // Spinner Drop down elements
        List<String> comptes = new ArrayList<>();
        comptes.add("Compte PAIEMENT N°1");
        comptes.add("Compte PAIEMENT N°2");
        comptes.add("Compte PAIEMENT N°3");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, comptes);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        ViewPager pager = (ViewPager) rootView.findViewById(R.id.vpPager);
        pager.setAdapter(new DashboardPagerAdapter(getChildFragmentManager()));

        // Attach the page change listener inside the activity
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        Utils.hideKeyboardFrom(getActivity(), rootView.findViewById(R.id.header_view));

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
        menu.findItem(R.id.action_favoris).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        solde.setText(listCompt.get(position).getSolde());
        soldeAtempsReelle.setText(listCompt.get(position).getSolde());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
