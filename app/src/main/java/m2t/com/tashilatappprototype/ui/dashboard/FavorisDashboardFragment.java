package m2t.com.tashilatappprototype.ui.dashboard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.FavouriteAdapter;
import m2t.com.tashilatappprototype.common.pojo.Merchant;
import m2t.com.tashilatappprototype.common.pojo.Operator;
import m2t.com.tashilatappprototype.common.utils.Utility;
import m2t.com.tashilatappprototype.data.local.DatabaseHandler;
import m2t.com.tashilatappprototype.ui.configureOperator.ConfigureOperatorFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavorisDashboardFragment extends Fragment implements FavouriteAdapter.OnCardClickListner {

    private static final String TAG = FavorisDashboardFragment.class.getSimpleName();
    private String title;
    private PieChart mChart;
    private int page;
    private RecyclerView collectionView;
    private FavouriteAdapter favouriteAdapter;
    private List<Merchant> merchantList;
    private DatabaseHandler db;

    // newInstance constructor for creating fragment with arguments
    public static FavorisDashboardFragment newInstance(int page, String title) {
        FavorisDashboardFragment  fragmentThird = new FavorisDashboardFragment ();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentThird.setArguments(args);
        return fragmentThird;
    }

    public FavorisDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_favoris_dashboard, container, false);

        collectionView = (RecyclerView) rootView.findViewById(R.id.collection_view);

        merchantList = new ArrayList();
        db = new DatabaseHandler(getActivity().getApplicationContext());
        favouriteAdapter = new FavouriteAdapter(getActivity(), merchantList);
        favouriteAdapter.setOnCardClickListner(this);
        final GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        glm.setOrientation(LinearLayoutManager.VERTICAL);
        collectionView.setLayoutManager(glm);
        collectionView.setAdapter(favouriteAdapter);

        prepareAccounts();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void OnCardClicked(View view, int position) {
        if (view.getParent() == collectionView) {
            int itemPosition = collectionView.getChildLayoutPosition(view);
            Merchant item = merchantList.get(itemPosition);

            Fragment fragment = new ConfigureOperatorFragment();
            Bundle bundle = new Bundle();
            bundle.putString("logo_operator", item.getThumbnail());
            bundle.putString("title_operator", item.getName());
            fragment.setArguments(bundle);
            Utility.replaceFragement(fragment, getActivity());

        }
    }

    private void prepareAccounts() {
        Merchant merchant;
        for (Operator op :db.getAllOperators()) {
            if (op.getFavorite() == 1) {
                merchant = new Merchant();
                merchant.setName(op.getName());
                merchant.setThumbnail(op.getID_OPER());
                Log.d(TAG, "name : " + merchant.getName() + " id : " + merchant.getThumbnail());
                merchantList.add(merchant);
            }
        }
        favouriteAdapter.notifyDataSetChanged();
    }
}
