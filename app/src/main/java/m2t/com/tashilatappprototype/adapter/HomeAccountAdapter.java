package m2t.com.tashilatappprototype.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import m2t.com.tashilatappprototype.common.pojo.Merchant;
import m2t.com.tashilatappprototype.R;

public class HomeAccountAdapter extends RecyclerView.Adapter<HomeAccountAdapter.ViewHolder> {

    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ref, status;
        CardView cardview;

        ViewHolder(View itemView) {
            super(itemView);
            ref = (TextView) itemView.findViewById(R.id.ref_account);
            status = (TextView) itemView.findViewById(R.id.status);
            cardview = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    List<Merchant> items;
    OnCardClickListner onCardClickListner;

    public HomeAccountAdapter(Context ctx, List<Merchant> items) {
        this.items = items;
        context = ctx;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_account_list, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.ref.setText("Compte N°" + items.get(position).getUan());
        holder.status.setText(items.get(position).getSolde() + " Dhs");
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onCardClickListner.OnCardClicked(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnCardClickListner {
        void OnCardClicked(View view, int position);
    }

    public void setOnCardClickListner(OnCardClickListner onCardClickListner) {
        this.onCardClickListner = onCardClickListner;
    }

}
