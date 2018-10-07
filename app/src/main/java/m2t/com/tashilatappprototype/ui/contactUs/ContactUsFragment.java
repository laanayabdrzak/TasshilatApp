package m2t.com.tashilatappprototype.ui.contactUs;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.ContactUsAdapter;
import m2t.com.tashilatappprototype.common.pojo.ContactUs;
import m2t.com.tashilatappprototype.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<ContactUs> data;
    private ProgressDialog pDialog;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment with arguments
    public static ContactUsFragment newInstance(int page, String title) {
        ContactUsFragment  fifth = new ContactUsFragment ();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fifth.setArguments(args);
        return fifth;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ((MainActivity) getActivity()).enableViews(false);

        pDialog = new ProgressDialog(this.getActivity());
        pDialog.setCancelable(false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        data = new ArrayList<>();
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ContactUsAdapter(data);


        mRecyclerView.setAdapter(mAdapter);
        prepareAccounts();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_log_out).setVisible(true);
        menu.findItem(R.id.action_favoris).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    private void prepareAccounts() {

        ContactUs a = new ContactUs("Compte 1", "text1", "text2", "text3", "text4", "text5", "text6", "text7");
        data.add(a);


        mAdapter.notifyDataSetChanged();
    }
}
