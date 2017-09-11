package m2t.com.tashilatappprototype.UI.TicketsPayment;


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
public class TicketsFragment extends Fragment {


    private RecyclerView recyclerView;
    private AccountPaymentAdapter adapter;
    private Fragment fragment;
    private List<Account> accountsList;

    public TicketsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tickets, container, false);
        ((MainActivity) getActivity()).enableViews(false);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.achat_ticket_billet_title);
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

                int itemPosition = recyclerView.getChildLayoutPosition(view);
                Account item = accountsList.get(itemPosition);
                fragment = new ConfigureOperatorFragment();
                Bundle bundle = new Bundle();
                bundle.putString("logo_operator",String.valueOf(item.getThumbnail()));
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
     * Adding few albums for testing
     */
    private void prepareAccounts() {
        int[] covers = new int[]{
                R.drawable.b0050_,
                R.drawable.b0006,
                };

        Account a = new Account("iTaxi", 1332333214, covers[0]);
        accountsList.add(a);

        a = new Account("CTM", 1332333214, covers[1]);
        accountsList.add(a);

        adapter.notifyDataSetChanged();
    }
}
