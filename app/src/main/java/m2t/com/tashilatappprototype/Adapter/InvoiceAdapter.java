package m2t.com.tashilatappprototype.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import m2t.com.tashilatappprototype.Common.POJO.Favourite;
import m2t.com.tashilatappprototype.R;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

	private Context context;

	public static class ViewHolder extends RecyclerView.ViewHolder {

		ViewHolder(View itemView) {
			super(itemView);
		}

	}

	List<?> items;
	OnCardClickListner onCardClickListner;

	public InvoiceAdapter(Context ctx, List<Favourite> items) {
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
