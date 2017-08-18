package m2t.com.tashilatappprototype.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import m2t.com.tashilatappprototype.Common.POJO.Favourite;
import m2t.com.tashilatappprototype.R;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

	private Context context;

	public static class ViewHolder extends RecyclerView.ViewHolder {

		TextView title;
		ImageView icon;
		CardView cardview;

		ViewHolder(View itemView) {
			super(itemView);
			icon = (ImageView) itemView.findViewById(R.id.icon);
			title = (TextView) itemView.findViewById(R.id.title);
			cardview = (CardView) itemView.findViewById(R.id.card_view);
		}

	}

	List<Favourite> items;
	OnCardClickListner onCardClickListner;

	public FavouriteAdapter(Context ctx, List<Favourite> items) {
		this.items = items;
		context = ctx;
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_fav_collection, viewGroup, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		holder.title.setText(items.get(position).title);
		holder.icon.setImageResource(items.get(position).icon);
		holder.cardview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onCardClickListner.OnCardClicked(view, position);
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
