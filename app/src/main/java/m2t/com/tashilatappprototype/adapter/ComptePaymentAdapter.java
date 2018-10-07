package m2t.com.tashilatappprototype.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.Merchant;
import m2t.com.tashilatappprototype.common.utils.Utility;
import m2t.com.tashilatappprototype.ui.configureOperator.ConfigureOperatorFragment;

/**
 * Created by laanaya on 8/11/17.
 */

public class ComptePaymentAdapter extends RecyclerView.Adapter<ComptePaymentAdapter.MyViewHolder> implements Filterable {

    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Context mContext;
    private List<Merchant> merchantList;
    private List<Merchant> merchantListFiltered;
    private List<Merchant> itemsPendingRemoval;
    private AccountAdapterListener listener;
    boolean undoOn; // is undo on, you can turn it on from the toolbar menu
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<Merchant, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be


    public ComptePaymentAdapter(Context mContext, List<Merchant> merchantList, AccountAdapterListener listener) {
        this.mContext = mContext;
        this.merchantList = merchantList;
        this.merchantListFiltered = merchantList;
        this.listener = listener;
        itemsPendingRemoval = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.compte_paiement_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Merchant acc = merchantListFiltered.get(position);
        holder.title.setText(acc.getName());

        int id = mContext.getResources().getIdentifier("b" + acc.getThumbnail(), "drawable", mContext.getPackageName());
        if (id != 0)
            holder.thumbnail.setImageResource(id);

        // loading acc cover using Glide library
        //Glide.with(mContext).load(acc.getThumbnail()).into(holder.thumbnail);

        // Delete Process
        final Merchant item = merchantListFiltered.get(position);

        if (itemsPendingRemoval.contains(item)) {
            // we need to show the "undo" state of the row
            holder.itemView.setBackgroundColor(Color.RED);

        } else {
            // we need to show the "normal" state
            holder.itemView.setBackgroundColor(Color.WHITE);

        }
    }


    public void notifyData(List<Merchant> merchantList) {
        Log.d("notifyData ", merchantList.size() + "");
        this.merchantListFiltered = merchantList;
        notifyDataSetChanged();
    }
    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_dots, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    public boolean isUndoOn() {
        return undoOn;
    }

    public void pendingRemoval(int position) {
        final Merchant item = merchantListFiltered.get(position);
        if (!itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.add(item);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(merchantListFiltered.indexOf(item));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(item, pendingRemovalRunnable);
        }
    }

    public void remove(int position) {
        Merchant item = merchantListFiltered.get(position);
        if (itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.remove(item);
        }
        if (merchantListFiltered.contains(item)) {
            merchantListFiltered.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isPendingRemoval(int position) {
        Merchant item = merchantListFiltered.get(position);
        return itemsPendingRemoval.contains(item);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    merchantListFiltered = merchantList;
                } else {
                    List<Merchant> filteredList = new ArrayList<>();
                    for (Merchant row : merchantList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    merchantListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = merchantListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                merchantListFiltered = (ArrayList<Merchant>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Fragment fragment = null;
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Ajouter aux favouris", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    fragment = new ConfigureOperatorFragment();
                    Utility.replaceFragementFromContext(fragment, mContext);
                    return true;
                case R.id.action_paid_biller:
                    fragment = new ConfigureOperatorFragment();
                    Utility.replaceFragementFromContext(fragment, mContext);
                    return true;
                case R.id.action_recharge:
                    fragment = new ConfigureOperatorFragment();
                    Utility.replaceFragementFromContext(fragment, mContext);
                    return true;
                case R.id.action_achat_ticket:
                    fragment = new ConfigureOperatorFragment();
                    Utility.replaceFragementFromContext(fragment, mContext);
                    return true;
                default:
            }
            return false;
        }
    }


    @Override
    public int getItemCount() {
        return merchantListFiltered.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onContactSelected(merchantListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface AccountAdapterListener {
        void onContactSelected(Merchant merchant);
    }
}
