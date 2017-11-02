package m2t.com.tashilatappprototype.ui.billsPayment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.adapter.AccountPaymentAdapter;
import m2t.com.tashilatappprototype.adapter.util.GridSpacingItemDecoration;
import m2t.com.tashilatappprototype.adapter.util.RecyclerTouchListener;
import m2t.com.tashilatappprototype.common.pojo.Account;
import m2t.com.tashilatappprototype.common.pojo.Operator;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.data.local.DatabaseHandler;
import m2t.com.tashilatappprototype.ui.configureOperator.ConfigureOperatorFragment;
import m2t.com.tashilatappprototype.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillsPaymentFragment extends Fragment {

    private static final String TAG = BillsPaymentFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private AccountPaymentAdapter adapter;
    private Fragment fragment;
    private List<Account> accountsList;
    private DatabaseHandler db;

    public BillsPaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bills_payment, container, false);
        ((MainActivity) getActivity()).enableViews(false);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.paiemnt_fac_title);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        accountsList = new ArrayList<>();
        adapter = new AccountPaymentAdapter(this.getActivity(), accountsList);
        db = new DatabaseHandler(getActivity().getApplicationContext());

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

                int itemPosition = recyclerView.getChildLayoutPosition(view);
                Account item = accountsList.get(itemPosition);
                fragment = new ConfigureOperatorFragment();
                Bundle bundle = new Bundle();
                bundle.putString("logo_operator",item.getThumbnail());
                bundle.putString("title_operator",item.getName());
                fragment.setArguments(bundle);
                Utils.replaceFragement(fragment, getActivity());
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Ajouter aux favourites", Toast.LENGTH_LONG).show();
            }
        }));
        prepareAccounts();

        return rootView;
    }

    /**
     * Adding few accounts for testing
     */
    private void prepareAccounts() {

        Account account;
        for (Operator op :db.getAllOperators()) {
            account = new Account();
            account.setName(op.getName());
            account.setThumbnail(op.getID_OPER());
            Log.d(TAG,"name : " + account.getName() +" id : "+ account.getThumbnail());
            accountsList.add(account);
        }
        /*int[] covers = new int[]{
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

        Account a = new Account("Lydec", 1332333214, covers[0]);
        accountsList.add(a);

        a = new Account("Redal", 1332333214, covers[1]);
        accountsList.add(a);

        a = new Account("RAMSA", 1332333214, covers[2]);
        accountsList.add(a);

        a = new Account("AMENDIS", 1332333214, covers[3]);
        accountsList.add(a);

        a = new Account("ONEE", 1332333214, covers[4]);
        accountsList.add(a);

        a = new Account("RADEEMA", 1332333214, covers[5]);
        accountsList.add(a);

        a = new Account("RADEEO", 1332333214, covers[6]);
        accountsList.add(a);

        a = new Account("RADEEF", 1332333214, covers[7]);
        accountsList.add(a);

        a = new Account("RAK", 1332333214, covers[8]);
        accountsList.add(a);

        a = new Account("STAREO", 1332333214, covers[9]);
        accountsList.add(a);

        a = new Account("RADEES", 1332333214, covers[10]);
        accountsList.add(a);

        a = new Account("RADEEL", 1332333214, covers[11]);
        accountsList.add(a);

        a = new Account("RADEEJ", 1332333214, covers[12]);
        accountsList.add(a);

        a = new Account("RADEM", 1332333214, covers[13]);
        accountsList.add(a);
        */

        adapter.notifyDataSetChanged();
    }
}
