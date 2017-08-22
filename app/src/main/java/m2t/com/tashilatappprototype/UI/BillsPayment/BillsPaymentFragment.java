package m2t.com.tashilatappprototype.UI.BillsPayment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.Adapter.AccountPaymentAdapter;
import m2t.com.tashilatappprototype.Adapter.util.GridSpacingItemDecoration;
import m2t.com.tashilatappprototype.Adapter.util.RecyclerTouchListener;
import m2t.com.tashilatappprototype.Common.POJO.Account;
import m2t.com.tashilatappprototype.Common.utils.Utils;
import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.UI.ConfigureOperator.ConfigureOperatorFragment;
import m2t.com.tashilatappprototype.UI.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillsPaymentFragment extends Fragment {

    private RecyclerView recyclerView;
    private AccountPaymentAdapter adapter;
    private Fragment fragment;
    private List<Account> accountsList;

    public BillsPaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bills_payment, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().show();
        ((MainActivity) getActivity()).setActionBarTitle(R.string.paiemnt_fac_title);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        accountsList = new ArrayList<>();
        adapter = new AccountPaymentAdapter(this.getActivity(), accountsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Utils.dpToPx(this.getActivity(), 10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        /**
         * RecyclerView: Implementing single item click and long press (Part-II)
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), "onCLick", Toast.LENGTH_LONG).show();
                fragment = new ConfigureOperatorFragment();
                Bundle bundle = new Bundle();
                bundle.putString("logo_operator","");
                Utils.replaceFragement(fragment, getActivity());
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "onLongClick", Toast.LENGTH_LONG).show();
            }
        }));
        prepareAccounts();

        return rootView;
    }

    /**
     * Adding few accounts for testing
     */
    private void prepareAccounts() {
        int[] covers = new int[]{
                R.drawable.b0001,
                R.drawable.b0002,
                R.drawable.b0009,
                R.drawable.b0011,
                R.drawable.b0013,
                R.drawable.b0019,
                R.drawable.b0025,
                R.drawable.b0026,
                R.drawable.b0027,
                R.drawable.b0029,
                R.drawable.b0030,
                R.drawable.b0032,
                R.drawable.b0035,
                R.drawable.b0051,
        };

        Account a = new Account("", 1332333214, covers[0]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[1]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[2]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[3]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[4]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[5]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[6]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[7]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[8]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[9]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[10]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[11]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[12]);
        accountsList.add(a);

        a = new Account("", 1332333214, covers[13]);
        accountsList.add(a);


        adapter.notifyDataSetChanged();
    }
}
