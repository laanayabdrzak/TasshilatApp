package m2t.com.tashilatappprototype.ui.solde;


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

import m2t.com.tashilatappprototype.adapter.SoldeAdapter;
import m2t.com.tashilatappprototype.common.pojo.Account;
import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.ui.MainActivity;

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
        ((MainActivity) getActivity()).enableViews(false);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.solde_title);
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
                R.drawable.citibank
        };

        Account a = new Account("Compte 1", 1332333214, 12313133f);
        accountsList.add(a);

        a = new Account("Compte 2", 1332333214, 12.33f);
        accountsList.add(a);

        a = new Account("Compte 3", 1332333214, 12121.43f);
        accountsList.add(a);



        adapter.notifyDataSetChanged();
    }
}
