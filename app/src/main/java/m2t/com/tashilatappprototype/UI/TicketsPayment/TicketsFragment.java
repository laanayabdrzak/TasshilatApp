package m2t.com.tashilatappprototype.UI.TicketsPayment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.Adapter.AccountPaymentAdapter;
import m2t.com.tashilatappprototype.Adapter.util.GridSpacingItemDecoration;
import m2t.com.tashilatappprototype.Common.POJO.Account;
import m2t.com.tashilatappprototype.Common.utils.Utils;
import m2t.com.tashilatappprototype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketsFragment extends Fragment {


    private RecyclerView recyclerView;
    private AccountPaymentAdapter adapter;

    private List<Account> accountsList;

    public TicketsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tickets, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        accountsList = new ArrayList<>();
        adapter = new AccountPaymentAdapter(this.getActivity(), accountsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Utils.dpToPx(this.getActivity(), 10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAccounts();
        return rootView;
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAccounts() {
        int[] covers = new int[]{
                R.drawable.cihbank,
                R.drawable.cihbank,
                R.drawable.citibank};

        Account a = new Account("BMCE BANK", 1332333214, covers[0]);
        accountsList.add(a);

        a = new Account("CIH BANK", 1332333214, covers[1]);
        accountsList.add(a);

        a = new Account("Citi BANK", 1332333214, covers[2]);
        accountsList.add(a);



        adapter.notifyDataSetChanged();
    }
}
