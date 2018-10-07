package m2t.com.tashilatappprototype.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import m2t.com.tashilatappprototype.ui.accountsPayment.AccountPaymentFragment;
import m2t.com.tashilatappprototype.ui.history.HistoryFragment;
import m2t.com.tashilatappprototype.ui.solde.SoldeFragment;

/**
 * Created by laanaya on 12/20/17.
 */

public class ComptesTabsAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;
    private String tabTitles[] = new String[] {
            "Balances",
            "Gérer mes comptes",
            "Syntheses des comptes"};


    public ComptesTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return SoldeFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 1 - This will show FirstFragment different title
                return AccountPaymentFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 4 - This will show SecondFragment
                return HistoryFragment.newInstance(2, "Page # 3");
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
