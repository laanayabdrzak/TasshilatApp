package m2t.com.tashilatappprototype.ui.impot;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.AccountPaymentAdapter;
import m2t.com.tashilatappprototype.adapter.util.GridSpacingItemDecoration;
import m2t.com.tashilatappprototype.common.pojo.Merchant;
import m2t.com.tashilatappprototype.common.pojo.Operator;
import m2t.com.tashilatappprototype.common.utils.Utility;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.data.local.DatabaseHandler;
import m2t.com.tashilatappprototype.ui.configureOperator.ConfigureOperatorFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImpotsTaxesFragment extends Fragment implements AccountPaymentAdapter.AccountAdapterListener{


    private static final String TAG = ImpotsTaxesFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private AccountPaymentAdapter adapter;
    private List<Merchant> merchantList;
    private DatabaseHandler db;


    // newInstance constructor for creating fragment with arguments
    public static ImpotsTaxesFragment newInstance(int page, String title) {
        ImpotsTaxesFragment  fourth = new ImpotsTaxesFragment ();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fourth.setArguments(args);
        return fourth;
    }

    public ImpotsTaxesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_impots_taxes, container, false);
        //((MainActivity) getActivity()).enableViews(false);
        //((MainActivity) getActivity()).setActionBarTitle(R.string.impot_taxes_title);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        db = new DatabaseHandler(getActivity().getApplicationContext());
        merchantList = new ArrayList<>();
        adapter = new AccountPaymentAdapter(this.getActivity(), merchantList, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Utils.dpToPx(this.getActivity(), 10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAccounts();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.options_menu, menu);
        //MenuItem item = menu.findItem(R.id.search);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, query);
                adapter.getFilter().filter(query);
                adapter.notifyDataSetChanged();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, newText);
                adapter.getFilter().filter(newText);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    /**
     * Adding few albums for testing
     */
    private void prepareAccounts() {

        Merchant merchant;
        for (Operator op :db.getOperatorByCategorie("Impot Et taxe")) {
            merchant = new Merchant();
            merchant.setName(op.getName());
            merchant.setThumbnail(op.getID_OPER());
            Log.d(TAG,"name : " + merchant.getName() +" id : "+ merchant.getThumbnail());
            merchantList.add(merchant);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onContactSelected(Merchant merchant) {
        Fragment fragment = new ConfigureOperatorFragment();
        Bundle bundle = new Bundle();
        bundle.putString("logo_operator", merchant.getThumbnail());
        bundle.putString("title_operator", merchant.getName());
        bundle.putString("flag", "new");
        bundle.putString("ident", "");
        bundle.putString("identType", "");
        bundle.putString("modPaiement", "");
        fragment.setArguments(bundle);
        Utility.replaceFragement(fragment, getActivity());
    }
}
