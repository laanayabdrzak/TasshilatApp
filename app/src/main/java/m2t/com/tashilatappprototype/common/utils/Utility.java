package m2t.com.tashilatappprototype.common.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import m2t.com.tashilatappprototype.R;

/**
 * Created by laanaya on 12/21/17.
 */

public class Utility {

    public static void replaceFragement(Fragment fragment, FragmentActivity activity) {
        if (fragment != null && !fragment.isVisible()) {
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            ft.replace(R.id.frame_container, fragment);
            //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(fragment.getClass().getName());
            ft.commit();
        } else Log.e("Fragements", "Error in creating fragment");

    }

    public static void replaceFragementFromContext(Fragment fragment, Context context) {
        if (fragment != null && !fragment.isVisible()) {
            FragmentTransaction ft = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            ft.replace(R.id.frame_container, fragment);
            //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(fragment.getClass().getName());
            ft.commit();
        } else Log.e("Fragements", "Error in creating fragment");
    }
}
