package m2t.com.tashilatappprototype.ui.listFacturies;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.adapter.FacturesAdapter;
import m2t.com.tashilatappprototype.common.pojo.EncaisseRequest;
import m2t.com.tashilatappprototype.common.pojo.EncaisseResponse;
import m2t.com.tashilatappprototype.common.pojo.Facture;
import m2t.com.tashilatappprototype.common.pojo.Req;
import m2t.com.tashilatappprototype.common.utils.HtmlToPDF;
import m2t.com.tashilatappprototype.common.utils.MsgDialog;
import m2t.com.tashilatappprototype.common.utils.SessionManager;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.data.remote.ApiClient;
import m2t.com.tashilatappprototype.data.remote.ApiInterface;
import m2t.com.tashilatappprototype.ui.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFacturiesFragment extends Fragment implements FacturesAdapter.FactureAdapterListener {

    private static final String TAG = ListFacturiesFragment.class.getSimpleName();
    private List<Facture> factureList;
    //private FactureAdapter adapter;
    private RecyclerView recyclerView;
    private SessionManager sessionManager;
    private FacturesAdapter mAdapter;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;


    public ListFacturiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_facturies, container, false);
        ((MainActivity) getActivity()).enableViews(false);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).setActionBarTitle(R.string.list_facture_title, R.color.secondColor);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        factureList = new ArrayList<>();
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        mAdapter = new FacturesAdapter(this.getActivity(), factureList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        actionModeCallback = new ActionModeCallback();

        getDataintoRecycler();

        ((MainActivity) getActivity()).toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() == 0) {
                    getActivity().onBackPressed();
                } else {
                    getFragmentManager().popBackStack();
                }
            }
        });

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

    private void getDataintoRecycler() {

        ArrayList<Facture> factures = getArguments().getParcelableArrayList("facturie_list");

        for (Facture f: factures) {
            factureList.add(f);
            Log.d(TAG, f.toString());
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onIconClicked(int position) {
        //Toast.makeText(getActivity(), "IconClicked " + position, Toast.LENGTH_LONG).show();
        popInPayment(position);
    }

    @Override
    public void onIconImportantClicked(int position) {
        Toast.makeText(getActivity(), "onIconImportantClicked " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFactureRowClicked(final int position) {

    }

    private void popInPayment(final int position) {
        String webData = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <td>Total de la facture</td>\n" +
                "    <td>:  <b>"+ Utils.convertPrice(factureList.get(position).getMntHt())+" Dhs</b></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Total des frais</td>\n" +
                "    <td>:  <b>1.50 Dhs</b></td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Montant avec frais</td>\n" +
                "    <td>:  <font color=\"red\"><b>"+ Utils.mntWithFees(Utils.convertPrice(factureList.get(position).getMntHt())) +" Dhs</b></font></td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "\n" +
                "<h4 align=\"center\">Voulez vous payer?</h4>"+
                "</body>\n" +
                "</html>\n";
        WebView webView = new WebView(getActivity());
        webView.loadData(webData, "text/html", "utf-8");
        Utils.showDialogBill("Confirmation", webView, new MsgDialog() {
            @Override
            public void yesCase() throws ParseException {
                encaisseFactures(factureList.get(position));
            }

            @Override
            public void noCase() {

            }
        }, getActivity());
    }

    @Override
    public void onRowLongClicked(int position) {
        Toast.makeText(getActivity(), "onRowLongClicked " + position, Toast.LENGTH_LONG).show();
    }

    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

            // disable swipe refresh if action mode is enabled
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    // delete all the selected messages
                    deleteMessages();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mAdapter.clearSelections();
            actionMode = null;
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.resetAnimationIndex();
                    // mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    // deleting the messages from recycler view
    private void deleteMessages() {
        mAdapter.resetAnimationIndex();
        List<Integer> selectedItemPositions =
                mAdapter.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            mAdapter.removeData(selectedItemPositions.get(i));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void encaisseFactures(final Facture f) {
        final ProgressDialog progressDialog = new ProgressDialog(this.getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Patienter SVP...");
        progressDialog.show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> user = sessionManager.getUserDetails();
        EncaisseRequest request = new EncaisseRequest();
        List<Facture> factures = new ArrayList<>();
        factures.add(f);
        request.setListeFactures(factures);
        request.setModPaiement("ESPECE");
        request.setMontantTTC(Utils.montantTTC(f.getMntTTC(), getArguments().getString("MntFraisTtc")));
        request.setMontantHT(f.getMntHt());
        request.setFacturesRef(getArguments().getString("FacturesRef"));
        request.setMntFraisTtc(getArguments().getString("MntFraisTtc"));
        request.setMntTimbre(f.getMntTimbre());
        request.setReq(new Req(getArguments().getString("logo_operator")));

        Call<EncaisseResponse> call = apiService.encaisserFacture(request, user.get(SessionManager.KEY_PHPSESSID) + ";"
                + user.get(SessionManager.KEY_COOKIE));

        call.enqueue(new Callback<EncaisseResponse>() {
            @Override
            public void onResponse(Call<EncaisseResponse> call, final Response<EncaisseResponse> response) {
                progressDialog.dismiss();
                if (response.body().getCodeError().equals("0")) {
                    try {
                        Utils.AlerDialogFacture("Recu de Facture", new MsgDialog() {
                        @Override
                        public void yesCase() {
                            HtmlToPDF.createFilePDF(getActivity(), "recu facture ", getArguments().getString("title_operator"), f, response.body());
                            getActivity().onBackPressed();
                        }

                        @Override
                        public void noCase() {

                        }
                        }, getActivity(), getArguments().getString("title_operator"), f, response.body());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getActivity(), response.body().getMsgError(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EncaisseResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}
