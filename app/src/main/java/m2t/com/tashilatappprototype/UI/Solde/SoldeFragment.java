package m2t.com.tashilatappprototype.UI.Solde;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.Adapter.SoldeAdapter;
import m2t.com.tashilatappprototype.Common.POJO.Account;
import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.UI.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoldeFragment extends Fragment {

    private RecyclerView recyclerView;
    private SoldeAdapter adapter;

    private List<Account> accountsList;

    public SoldeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_solde, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().show();
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

    /**
     * Adding few accounts for testing
     */
    private void prepareAccounts() {
        int[] covers = new int[]{
                R.drawable.cihbank,
                R.drawable.cihbank,
                R.drawable.citibank};

        Account a = new Account("BMCE BANK", 1332333214, 12313133f);
        accountsList.add(a);

        a = new Account("CIH BANK", 1332333214, 12.33f);
        accountsList.add(a);

        a = new Account("Citi BANK", 1332333214, 12121.43f);
        accountsList.add(a);



        adapter.notifyDataSetChanged();
    }
}