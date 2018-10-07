package m2t.com.tashilatappprototype.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import m2t.com.tashilatappprototype.ui.billsPayment.BillsPaymentFragment;
import m2t.com.tashilatappprototype.ui.history.HistoryFragment;
import m2t.com.tashilatappprototype.ui.impot.ImpotsTaxesFragment;
import m2t.com.tashilatappprototype.ui.recharge.RechargeFragment;
import m2t.com.tashilatappprototype.ui.ticketsPayment.TicketsFragment;
import m2t.com.tashilatappprototype.ui.transfert.TransfertFragment;

/**
 * Created by laanaya on 12/20/17.
 */

public class ServiceTabsAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 6;
    private String tabTitles[] = new String[] { "Impots & Taxes","Eau & Electricite", "Recharge", "Transfert",
                            "Transport", "Histo des transactions"};


    public ServiceTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return ImpotsTaxesFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 1 - This will show FirstFragment different title
                return BillsPaymentFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 1 - This will show FirstFragment different title
                return RechargeFragment.newInstance(2, "Page # 2");
            case 3: // Fragment # 2 - This will show SecondFragment
                return TransfertFragment.newInstance(3, "Page # 3");
            case 4: // Fragment # 3 - This will show SecondFragment
                return TicketsFragment.newInstance(4, "Page # 4");
            case 5: // Fragment # 4 - This will show SecondFragment
                return HistoryFragment.newInstance(5, "Page # 5");
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
