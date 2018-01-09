package m2t.com.tashilatappprototype.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.Facture;

/**
 * Created by laanaya on 11/23/17.
 */

public class FactureAdapter extends RecyclerView.Adapter<FactureAdapter.ViewHolder> {

    private List<Facture> factureList;
    private Context context;

    public FactureAdapter(Context context, List<Facture> factureList) {
        this.factureList = factureList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_facture_list, parent, false);
        FactureAdapter.ViewHolder vh = new FactureAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ref.setText(factureList.get(position).getAdresse());
        holder.amount.setText(factureList.get(position).getMntFraisHt());
        holder.status.setText(factureList.get(position).getDateLimite());
        holder.dateEcheance.setText(factureList.get(position).getEcheance());
    }

    @Override
    public int getItemCount() {
        return factureList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView ref, dateEcheance, amount, status;


        ViewHolder(View itemView) {
            super(itemView);
            ref = (TextView) itemView.findViewById(R.id.ref);
            dateEcheance = (TextView) itemView.findViewById(R.id.date_echeance);
            amount = (TextView) itemView.findViewById(R.id.amount);
            status = (TextView) itemView.findViewById(R.id.status);
        }

    }
}
