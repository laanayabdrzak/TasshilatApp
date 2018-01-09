package m2t.com.tashilatappprototype.ui.dashboard;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
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

import m2t.com.tashilatappprototype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvolutionSoldeFragment extends Fragment {

    private String title;
    private PieChart mChart;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static EvolutionSoldeFragment newInstance(int page, String title) {
        EvolutionSoldeFragment  fragmentSecond = new EvolutionSoldeFragment ();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentSecond.setArguments(args);
        return fragmentSecond;
    }

    public EvolutionSoldeFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_evolution_solde, container, false);

        mChart = (PieChart) rootView.findViewById(R.id.piechart);
        mChart.getDescription().setEnabled(false);

        //mChart.setCenterText("Tasshilat");
        mChart.setCenterTextSize(10f);

        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        mChart.setData(generatePieData());

        return rootView;
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Tasshilat");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    protected PieData generatePieData() {
        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(20, "Debit"));
        entries.add(new PieEntry(30, "Credit"));
        //entries.add(new PieEntry(15, "IAM"));
       // entries.add(new PieEntry(35, "ONEP"));

        PieDataSet ds1 = new PieDataSet(entries, "");
        ds1.setColors(ColorTemplate.MATERIAL_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE) ;
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        return d;
    }

}
