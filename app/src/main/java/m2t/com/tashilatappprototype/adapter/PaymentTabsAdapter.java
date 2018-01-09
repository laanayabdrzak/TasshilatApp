package m2t.com.tashilatappprototype.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import m2t.com.tashilatappprototype.ui.dashboard.EvolutionSoldeFragment;
import m2t.com.tashilatappprototype.ui.dashboard.FavorisDashboardFragment;
import m2t.com.tashilatappprototype.ui.dashboard.OperationFragment;

/**
 * Created by laanaya on 12/20/17.
 */

public class PaymentTabsAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;
    private String tabTitles[] = new String[] { "Operations", "Comptes", "Favoris" };


    public PaymentTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return OperationFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return EvolutionSoldeFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 1 - This will show SecondFragment
                return FavorisDashboardFragment.newInstance(2, "Page # 3");
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
