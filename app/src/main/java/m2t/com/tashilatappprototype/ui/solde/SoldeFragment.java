package m2t.com.tashilatappprototype.ui.solde;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.SoldeAdapter;
import m2t.com.tashilatappprototype.common.pojo.Balance;
import m2t.com.tashilatappprototype.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoldeFragment extends Fragment {

    private RecyclerView recyclerView;
    private SoldeAdapter adapter;

    private List<Balance> accountsList;

    public SoldeFragment() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment with arguments
    public static SoldeFragment newInstance(int page, String title) {
        SoldeFragment  fifth = new SoldeFragment ();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fifth.setArguments(args);
        return fifth;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_solde, container, false);
        ((MainActivity) getActivity()).enableViews(false);
       // ((MainActivity) getActivity()).setActionBarTitle(R.string.solde_title);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        accountsList = new ArrayList<>();
        adapter = new SoldeAdapter(this.getActivity(), accountsList);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAccounts();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.mainmenu, menu);
    }
    /**
     * Adding few accounts for testing
     */
    private void prepareAccounts() {
        Balance a = new Balance("Compte 1", "1332333214", 0, "1200,00 DH");
        accountsList.add(a);

        a = new Balance("Compte 2", "1332333214", 1,"5000, 00 DH");
        accountsList.add(a);

        a = new Balance("Compte 3", "1332333214", 1, "12121,43 DH");
        accountsList.add(a);



        adapter.notifyDataSetChanged();
    }
}
