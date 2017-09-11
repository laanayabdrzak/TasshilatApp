package m2t.com.tashilatappprototype.Adapter;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import m2t.com.tashilatappprototype.Common.POJO.Account;
import m2t.com.tashilatappprototype.Common.utils.Utils;
import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.UI.ConfigureOperator.ConfigureOperatorFragment;

/**
 * Created by laanaya on 8/11/17.
 */

public class AccountPaymentAdapter extends RecyclerView.Adapter<AccountPaymentAdapter.MyViewHolder> {

    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Context mContext;
    private List<Account> accountList;
    private List<Account> itemsPendingRemoval;
    boolean undoOn; // is undo on, you can turn it on from the toolbar menu

    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<Account, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be


    public AccountPaymentAdapter(Context mContext, List<Account> accountList) {
        this.mContext = mContext;
        this.accountList = accountList;
        itemsPendingRemoval = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Account acc = accountList.get(position);
        holder.title.setText(acc.getName());
        //holder.count.setText("UAN : " + acc.getUan());

        // loading acc cover using Glide library
        Glide.with(mContext).load(acc.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
        // Delete Process
        final Account item = accountList.get(position);

        if (itemsPendingRemoval.contains(item)) {
            // we need to show the "undo" state of the row
            holder.itemView.setBackgroundColor(Color.RED);

        } else {
            // we need to show the "normal" state
            holder.itemView.setBackgroundColor(Color.WHITE);

        }
    }

    public void notifyData(List<Account> accountList) {
        Log.d("notifyData ", accountList.size() + "");
        this.accountList = accountList;
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
        final Account item = accountList.get(position);
        if (!itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.add(item);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(accountList.indexOf(item));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(item, pendingRemovalRunnable);
        }
    }

    public void remove(int position) {
        Account item = accountList.get(position);
        if (itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.remove(item);
        }
        if (accountList.contains(item)) {
            accountList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isPendingRemoval(int position) {
        Account item = accountList.get(position);
        return itemsPendingRemoval.contains(item);
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
                    Utils.replaceFragementFromContext(fragment, mContext);
                    return true;
                case R.id.action_paid_biller:
                    fragment = new ConfigureOperatorFragment();
                    Utils.replaceFragementFromContext(fragment, mContext);
                    return true;
                case R.id.action_recharge:
                    fragment = new ConfigureOperatorFragment();
                    Utils.replaceFragementFromContext(fragment, mContext);
                    return true;
                case R.id.action_achat_ticket:
                    fragment = new ConfigureOperatorFragment();
                    Utils.replaceFragementFromContext(fragment, mContext);
                    return true;
                default:
            }
            return false;
        }
    }


    @Override
    public int getItemCount() {
        return accountList.size();
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
        }
    }
}
