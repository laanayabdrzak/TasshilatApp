package m2t.com.tashilatappprototype.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import m2t.com.tashilatappprototype.common.pojo.Invoice;
import m2t.com.tashilatappprototype.R;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ref, amount, status, date;
        ImageView icon;
        CardView cardview;

        ViewHolder(View itemView) {
            super(itemView);
            ref = (TextView) itemView.findViewById(R.id.ref);
            amount = (TextView) itemView.findViewById(R.id.amount);
            status = (TextView) itemView.findViewById(R.id.status);
            date = (TextView) itemView.findViewById(R.id.date);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            cardview = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    List<Invoice> items;
    OnCardClickListner onCardClickListner;

    public InvoiceAdapter(Context ctx, List<Invoice> items) {
        this.items = items;
        context = ctx;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_fav_list, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.ref.setText(items.get(position).getRef());
        holder.amount.setText(items.get(position).getAmount());
        holder.status.setText(items.get(position).getStatus());
        holder.date.setText(items.get(position).getDate());
        holder.icon.setImageResource(items.get(position).getIcon());
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
