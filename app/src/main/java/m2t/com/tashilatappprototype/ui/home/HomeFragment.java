package m2t.com.tashilatappprototype.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.FavouriteAdapter;
import m2t.com.tashilatappprototype.adapter.HomeAccountAdapter;
import m2t.com.tashilatappprototype.adapter.InvoiceAdapter;
import m2t.com.tashilatappprototype.common.pojo.Invoice;
import m2t.com.tashilatappprototype.common.pojo.Merchant;
import m2t.com.tashilatappprototype.ui.MainActivity;
import m2t.com.tashilatappprototype.ui.configureOperator.ConfigureOperatorFragment;

public class HomeFragment extends Fragment
		implements InvoiceAdapter.OnCardClickListner, FavouriteAdapter.OnCardClickListner {

	private RecyclerView listView, listviewCompte, collectionView;
	private PieChart mChart;
    private List<Merchant> favouriteItems;

	public HomeFragment() {
		super();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);
		((MainActivity) getActivity()).enableViews(false);
        //((MainActivity) getActivity()).setActionBarTitle(R.string.accueil_title);

		listView = (RecyclerView) v.findViewById(R.id.list_view);
		listviewCompte = (RecyclerView) v.findViewById(R.id.list_view_compte);
		collectionView = (RecyclerView) v.findViewById(R.id.collection_view);

		mChart = (PieChart) v.findViewById(R.id.piechart);
		mChart.getDescription().setEnabled(false);

		mChart.setCenterText("Tasshilat");
		mChart.setCenterTextSize(10f);

		mChart.setHoleRadius(45f);
		mChart.setTransparentCircleRadius(50f);

		Legend l = mChart.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
		l.setOrientation(Legend.LegendOrientation.VERTICAL);
		l.setDrawInside(false);

		mChart.setData(generatePieData());

		setHasOptionsMenu(true);

		return v;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        favouriteItems = new ArrayList();


		FavouriteAdapter favouriteAdapter = new FavouriteAdapter(getActivity(), favouriteItems);
		favouriteAdapter.setOnCardClickListner(this);
		final GridLayoutManager glm = new GridLayoutManager(getActivity(), 6);
		glm.setOrientation(LinearLayoutManager.VERTICAL);
		collectionView.setLayoutManager(glm);
		collectionView.setAdapter(favouriteAdapter);

		List<Invoice> invoiceItems = new ArrayList();
		invoiceItems.add(new Invoice("N° 21344452", "231,55 Dhs", "Payé", "13 Aout 2017 13h05", R.drawable.b0006));
		invoiceItems.add(new Invoice("N° 54665444", "60,65 Dhs", "Payé", "13 Aout 2017 13h05", R.drawable.b0011));
		invoiceItems.add(new Invoice("N° 09554332", "342,09 Dhs", "Payé", "29 Juin 2017 12h33", R.drawable.b0007));
		invoiceItems.add(new Invoice("N° 33456666", "603,12 Dhs", "Payé", "13 Aout 2017 22h57", R.drawable.b0004));
		invoiceItems.add(new Invoice("N° 12322111", "14,06 Dhs", "Payé", "13 Aout 2017 09h11", R.drawable.b0009));
		InvoiceAdapter invoiceAdapter = new InvoiceAdapter(getActivity(), invoiceItems);
		invoiceAdapter.setOnCardClickListner(this);
		final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
		listView.setLayoutManager(llm);
		listView.setAdapter(invoiceAdapter);

        List<Merchant> accItems = new ArrayList<>();

        Merchant a ;

        a = new Merchant();
        a.setUan(000000122);
        a.setSolde(121.2121f);
        accItems.add(a);

        a = new Merchant();
        a.setUan(000000132);
        a.setSolde(331.2121f);
        accItems.add(a);

        a = new Merchant();
        a.setUan(000000135);
        a.setSolde(631.2121f);
        accItems.add(a);


        HomeAccountAdapter accAdapter = new HomeAccountAdapter(getActivity(), accItems);
		final LinearLayoutManager llmC = new LinearLayoutManager(getActivity());
		listviewCompte.setLayoutManager(llmC);
		listviewCompte.setAdapter(accAdapter);
	}

	protected PieData generatePieData() {
		int count = 4;
		ArrayList<PieEntry> entries = new ArrayList<>();

		entries.add(new PieEntry(20, "Lydec"));
		entries.add(new PieEntry(30, "Orange"));
		entries.add(new PieEntry(15, "IAM"));
		entries.add(new PieEntry(35, "ONEP"));

		PieDataSet ds1 = new PieDataSet(entries, "");
		ds1.setColors(ColorTemplate.MATERIAL_COLORS);
		ds1.setSliceSpace(2f);
		ds1.setValueTextColor(Color.WHITE);
		ds1.setValueTextSize(12f);

		PieData d = new PieData(ds1);
		return d;
	}

	@Override
	public void OnCardClicked(View view, int position) {
		if (view.getParent() == collectionView) {
            int itemPosition = collectionView.getChildLayoutPosition(view);
            Merchant item = favouriteItems.get(itemPosition);

            Fragment fragment = new ConfigureOperatorFragment();
            Bundle bundle = new Bundle();

            fragment.setArguments(bundle);
			getFragmentManager().beginTransaction().replace(R.id.frame_container, fragment)
					.commit();
		}
	}
}
