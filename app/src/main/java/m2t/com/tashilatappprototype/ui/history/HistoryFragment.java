package m2t.com.tashilatappprototype.ui.history;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.adapter.InvoiceAdapter;
import m2t.com.tashilatappprototype.common.pojo.Invoice;
import m2t.com.tashilatappprototype.common.utils.DayAxisValueFormatter;
import m2t.com.tashilatappprototype.common.utils.MyAxisValueFormatter;
import m2t.com.tashilatappprototype.common.utils.XYMarkerView;
import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.ui.MainActivity;

public class HistoryFragment extends Fragment {

	private RecyclerView rv;
	private BarChart mChart;

	public HistoryFragment() {
		super();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_history, container, false);
		((MainActivity) getActivity()).enableViews(false);
        if (getArguments().getString("nav").equals("history_accounts"))
		    ((MainActivity) getActivity()).setActionBarTitle(R.string.histo_comptes_title);
        else ((MainActivity) getActivity()).setActionBarTitle(R.string.histo_trans_title);
		rv = (RecyclerView) v.findViewById(R.id.rv);

		mChart = (BarChart) v.findViewById(R.id.chart1);

		mChart.setDrawBarShadow(false);
		mChart.setDrawValueAboveBar(true);

		mChart.getDescription().setEnabled(false);
		mChart.setMaxVisibleValueCount(60);
		mChart.setPinchZoom(true);

		mChart.setDrawGridBackground(false);

		IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

		XAxis xAxis = mChart.getXAxis();
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		xAxis.setGranularity(1f);
		xAxis.setLabelCount(7);
		xAxis.setValueFormatter(xAxisFormatter);

		IAxisValueFormatter custom = new MyAxisValueFormatter();

		YAxis leftAxis = mChart.getAxisLeft();
		leftAxis.setLabelCount(8, false);
		leftAxis.setValueFormatter(custom);
		leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
		leftAxis.setSpaceTop(15f);
		leftAxis.setAxisMinimum(0f);

		YAxis rightAxis = mChart.getAxisRight();
		rightAxis.setDrawGridLines(false);
		rightAxis.setLabelCount(8, false);
		rightAxis.setValueFormatter(custom);
		rightAxis.setSpaceTop(15f);
		rightAxis.setAxisMinimum(0f);

		XYMarkerView mv = new XYMarkerView(getActivity(), xAxisFormatter);
		mv.setChartView(mChart);
		mChart.setMarker(mv);

		setData(10, 300);
		setHasOptionsMenu(true);

		return v;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        List<Invoice> invoiceItems = new ArrayList();
        invoiceItems.add(new Invoice("N° 21344452", "231,55 Dhs", "Payé", "13 Aout 2017 13h05", R.drawable.b0006));
        invoiceItems.add(new Invoice("N° 54665444", "60,65 Dhs", "Payé", "13 Aout 2017 13h05", R.drawable.b0011));
        invoiceItems.add(new Invoice("N° 09554332", "342,09 Dhs", "Payé", "29 Juin 2017 12h33", R.drawable.b0007));
        invoiceItems.add(new Invoice("N° 33456666", "603,12 Dhs", "Payé", "13 Aout 2017 22h57", R.drawable.b0004));
        invoiceItems.add(new Invoice("N° 12322111", "14,06 Dhs", "Payé", "13 Aout 2017 09h11", R.drawable.b0009));

		InvoiceAdapter invoiceAdapter = new InvoiceAdapter(getActivity(), invoiceItems);
		final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
		rv.setLayoutManager(llm);
		rv.setAdapter(invoiceAdapter);
	}

	private void setData(int count, float range) {

		float start = 1f;

		ArrayList<BarEntry> yVals1 = new ArrayList();

		for (int i = (int) start; i < start + count + 1; i++) {
			float mult = (range + 1);
			float val = (float) (Math.random() * mult);
			yVals1.add(new BarEntry(i, val));
		}

		BarDataSet set1;

		if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
			set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
			set1.setValues(yVals1);
			mChart.getData().notifyDataChanged();
			mChart.notifyDataSetChanged();
		} else {
			set1 = new BarDataSet(yVals1, "2017");
			set1.setColors(ColorTemplate.VORDIPLOM_COLORS);

			ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
			dataSets.add(set1);

			BarData data = new BarData(dataSets);
			data.setValueTextSize(10f);
			data.setBarWidth(0.9f);

			mChart.setData(data);
		}
	}
}
