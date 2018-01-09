package m2t.com.tashilatappprototype.ui.dashboard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.InvoiceAdapter;
import m2t.com.tashilatappprototype.common.pojo.Invoice;
import m2t.com.tashilatappprototype.common.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperationFragment extends Fragment implements InvoiceAdapter.OnCardClickListner{

    private static final String TAG = OperationFragment.class.getSimpleName();
    // Store instance variables
    private String title;
    private int page;
    private RecyclerView listView;

    // newInstance constructor for creating fragment with arguments
    public static OperationFragment newInstance(int page, String title) {
        OperationFragment fragmentFirst = new OperationFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public OperationFragment() {
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
        Log.d(TAG, "onCreateView");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_operation, container, false);
        Utils.hideKeyboardFrom(getActivity(), rootView.findViewById(R.id.parent));
        listView = (RecyclerView) rootView.findViewById(R.id.list_view);
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

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"onViewCreated");
    }

    @Override
    public void OnCardClicked(View view, int position) {

    }
}
