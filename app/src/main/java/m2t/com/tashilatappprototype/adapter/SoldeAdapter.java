package m2t.com.tashilatappprototype.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import m2t.com.tashilatappprototype.common.pojo.Merchant;
import m2t.com.tashilatappprototype.R;

/**
 * Created by laanaya on 8/11/17.
 */

public class SoldeAdapter extends RecyclerView.Adapter<SoldeAdapter.MyViewHolder> {

    private Context mContext;
    private List<Merchant> merchantList;

    public SoldeAdapter(Context mContext, List<Merchant> merchantList) {
        this.mContext = mContext;
        this.merchantList = merchantList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_solde_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Merchant acc = merchantList.get(position);
        holder.title.setText(acc.getName());
        holder.count.setText("UAN : " + acc.getUan());
        holder.solde.setText("Solde : " + acc.getSolde());

        // loading acc cover using Glide library
        /*Glide.with(mContext).load(acc.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
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

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Ajouter next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }


    @Override
    public int getItemCount() {
        return merchantList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, solde;
        public ImageView overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            solde = (TextView) view.findViewById(R.id.solde);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }
}
