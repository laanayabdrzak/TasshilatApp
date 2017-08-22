package m2t.com.tashilatappprototype.UI.Home;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import m2t.com.tashilatappprototype.Adapter.FavouriteAdapter;
import m2t.com.tashilatappprototype.Adapter.InvoiceAdapter;
import m2t.com.tashilatappprototype.Common.POJO.Favourite;
import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.UI.MainActivity;

public class HomeFragment extends Fragment
		implements InvoiceAdapter.OnCardClickListner, FavouriteAdapter.OnCardClickListner {

	private RecyclerView listView, collectionView;
	private PieChart mChart;

	public HomeFragment() {
		super();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(R.string.accueil_title);
		listView = (RecyclerView) v.findViewById(R.id.list_view);
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
		List<Favourite> favouriteItems = new ArrayList();
		favouriteItems.add(new Favourite("Lydec", R.drawable.b0001));
		favouriteItems.add(new Favourite("Redal", R.drawable.b0002));
		favouriteItems.add(new Favourite("Amanty", R.drawable.b0004));
		favouriteItems.add(new Favourite("CTM", R.drawable.b0006));
		favouriteItems.add(new Favourite("IAM", R.drawable.b0007));

		FavouriteAdapter favouriteAdapter = new FavouriteAdapter(getActivity(), favouriteItems);
		favouriteAdapter.setOnCardClickListner(this);
		final GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
		glm.setOrientation(LinearLayoutManager.VERTICAL);
		collectionView.setLayoutManager(glm);
		collectionView.setAdapter(favouriteAdapter);

		List invoiceItems = new ArrayList();
		for (int i = 0; i < 6; i++)
			invoiceItems.add("");
		InvoiceAdapter invoiceAdapter = new InvoiceAdapter(getActivity(), invoiceItems);
		invoiceAdapter.setOnCardClickListner(this);
		final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
		listView.setLayoutManager(llm);
		listView.setAdapter(invoiceAdapter);
	}

	protected PieData generatePieData() {
		int count = 4;
		ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

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

	}
}
