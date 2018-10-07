package m2t.com.tashilatappprototype.adapter.util;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import m2t.com.tashilatappprototype.ui.contactUs.ContactUsFragment;
import m2t.com.tashilatappprototype.ui.faq.FAQFragment;
import m2t.com.tashilatappprototype.ui.notifications.NotificationsFragment;

/**
 * Created by laanaya on 12/20/17.
 */

public class HelpTabsAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;
    private String tabTitles[] = new String[] {
            "Notifications",
            "Contactez-nous",
            "FAQ"};


    public HelpTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return NotificationsFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 1 - This will show FirstFragment different title
                return ContactUsFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 4 - This will show SecondFragment
                return FAQFragment.newInstance(2, "Page # 3");
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
