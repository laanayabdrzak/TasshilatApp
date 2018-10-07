package m2t.com.tashilatappprototype.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.Invoice;

public class LastOperationAdapter extends RecyclerView.Adapter<LastOperationAdapter.ViewHolder> {

    private Context context;
    private List<Invoice> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ref, from, date;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            ref = itemView.findViewById(R.id.ref);
            from = itemView.findViewById(R.id.from);
            date = itemView.findViewById(R.id.date);
            icon = itemView.findViewById(R.id.icon);

        }
    }

    public LastOperationAdapter(Context ctx, List<Invoice> items) {
        this.items = items;
        context = ctx;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.last_operation_row, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.ref.setText(items.get(position).getRef());
        holder.from.setText(items.get(position).getStatus());
        holder.date.setText(items.get(position).getDate());
        holder.icon.setImageResource(items.get(position).getIcon());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
