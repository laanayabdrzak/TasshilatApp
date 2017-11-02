package m2t.com.tashilatappprototype.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import m2t.com.tashilatappprototype.common.pojo.ContactUs;
import m2t.com.tashilatappprototype.R;

/**
 * Created by laanaya on 2/14/17.
 */

public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsAdapter.ViewHolder> {

    private ArrayList<ContactUs> dataSet;

    public ContactUsAdapter(ArrayList<ContactUs> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_contact_us, parent, false);

        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView addressContactus = holder.addressContactus;
        TextView faxContactus = holder.faxContactus;
        TextView mailContactus = holder.mailContactus;
        TextView phoneContactus = holder.phoneContactus;
        TextView siteContactus = holder.siteContactus;
        TextView statusContactus = holder.statusContactus;
        TextView webSiteContactus = holder.webSiteContactus;


        addressContactus.setText("address: "+ dataSet.get(position).getAddressContactus());
        faxContactus.setText("fax: " + dataSet.get(position).getFaxContactus());
        mailContactus.setText("mail: " + dataSet.get(position).getMailContactus());
        phoneContactus.setText("phone: " + dataSet.get(position).getPhoneContactus());
        siteContactus.setText("site: " + dataSet.get(position).getSiteContactus());
        statusContactus.setText("status: " + dataSet.get(position).getStatusContactus());
        webSiteContactus.setText("website: " + dataSet.get(position).getWebSiteContactus());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView addressContactus;
        TextView faxContactus;
        TextView mailContactus;
        TextView phoneContactus;
        TextView siteContactus;
        TextView statusContactus;
        TextView webSiteContactus;

        public ViewHolder(View itemView) {
            super(itemView);
            this.addressContactus = (TextView) itemView.findViewById(R.id.textView_address);
            this.faxContactus = (TextView) itemView.findViewById(R.id.textView_fax);
            this.mailContactus = (TextView) itemView.findViewById(R.id.textView_mail);
            this.phoneContactus = (TextView) itemView.findViewById(R.id.textView_phone);
            this.siteContactus = (TextView) itemView.findViewById(R.id.textView_site);
            this.statusContactus = (TextView) itemView.findViewById(R.id.textView_status);
            this.webSiteContactus = (TextView) itemView.findViewById(R.id.textView_website);
        }
    }
}
