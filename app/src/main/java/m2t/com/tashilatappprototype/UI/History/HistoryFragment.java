package m2t.com.tashilatappprototype.UI.History;

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

import m2t.com.tashilatappprototype.Adapter.InvoiceAdapter;
import m2t.com.tashilatappprototype.Common.utils.DayAxisValueFormatter;
import m2t.com.tashilatappprototype.Common.utils.MyAxisValueFormatter;
import m2t.com.tashilatappprototype.Common.utils.XYMarkerView;
import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.UI.MainActivity;

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
		((MainActivity) getActivity()).setActionBarTitle(R.string.histo_comptes_title);
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
		List invoiceItems = new ArrayList();
		for (int i = 0; i < 6; i++)
			invoiceItems.add("");
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
