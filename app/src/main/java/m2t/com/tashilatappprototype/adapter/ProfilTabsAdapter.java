package m2t.com.tashilatappprototype.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import m2t.com.tashilatappprototype.ui.changePwd.ChangePwdFragment;
import m2t.com.tashilatappprototype.ui.userInfo.UserInfoFragment;

/**
 * Created by laanaya on 12/20/17.
 */

public class ProfilTabsAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    private String tabTitles[] = new String[] {
            "Donnees Perso",
            "Changer mot de passe",
    };


    public ProfilTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return UserInfoFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 1 - This will show FirstFragment different title
                return ChangePwdFragment.newInstance(1, "Page # 2");
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
