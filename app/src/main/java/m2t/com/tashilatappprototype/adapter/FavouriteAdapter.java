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

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.Merchant;

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

	List<Merchant> items;
	OnCardClickListner onCardClickListner;

	public FavouriteAdapter(Context context, List<Merchant> items) {
		this.items = items;
		this.context = context;
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
	public void onBindViewHolder (ViewHolder holder, final int position) {
		holder.title.setText(items.get(position).getName());
		int id = context.getResources().getIdentifier("b" + items.get(position).getThumbnail(), "drawable", context.getPackageName());
		holder.icon.setImageResource(id);
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
