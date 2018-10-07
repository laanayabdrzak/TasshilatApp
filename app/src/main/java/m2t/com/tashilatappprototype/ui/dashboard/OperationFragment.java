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
import java.util.HashMap;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.InvoiceAdapter;
import m2t.com.tashilatappprototype.adapter.LastOperationAdapter;
import m2t.com.tashilatappprototype.common.pojo.ConsultationTrxReq;
import m2t.com.tashilatappprototype.common.pojo.Invoice;
import m2t.com.tashilatappprototype.common.utils.SessionManager;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.data.remote.ApiClient;
import m2t.com.tashilatappprototype.data.remote.ApiInterface;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperationFragment extends Fragment implements InvoiceAdapter.OnCardClickListner{

    private static final String TAG = OperationFragment.class.getSimpleName();
    // Store instance variables
    private String title;
    private int page;
    private RecyclerView listView;
    private SessionManager sessionManager;

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
        setHasOptionsMenu(true);
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
        sessionManager = new SessionManager(getActivity().getApplicationContext());
        List<Invoice> invoiceItems = new ArrayList();
        invoiceItems.add(new Invoice("1 facture payer N° 21344452", "", "Facture(s) RADEEJ déjà", "13/01/2017", R.drawable.ic_library_books_black_24dp));
        invoiceItems.add(new Invoice("Compte paiement N° 1", "", "Changement de status du compte de paiement", "13/04/2017", R.drawable.ic_credit_card_black_24dp));
        invoiceItems.add(new Invoice("N° 09554332", "", "Facture(s) IAM", "29/04/2017", R.drawable.ic_library_books_black_24dp));
        invoiceItems.add(new Invoice("Compte N°2", "", "Alimentation du compte ", "13/05/2017", R.drawable.ic_credit_card_black_24dp));
        invoiceItems.add(new Invoice("1 facture payer N° 12322111", "", "Facture(s) ONEP", "13/06/2017", R.drawable.ic_library_books_black_24dp));
        LastOperationAdapter invoiceAdapter = new LastOperationAdapter(getActivity(), invoiceItems);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        listView.setLayoutManager(llm);
        listView.setAdapter(invoiceAdapter);

        return rootView;
    }


    @Override
    public void OnCardClicked(View view, int position) {

    }

    private void getLastTrx() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> user = sessionManager.getUserDetails();
        Call<String> call = apiService.getConsulationTrx(new ConsultationTrxReq("21/09/2018","21/09/2018"), user.get(SessionManager.KEY_PHPSESSID) + ";"
                + user.get(SessionManager.KEY_COOKIE));
    }
}
