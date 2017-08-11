package m2t.com.tashilatappprototype.Common.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;

import m2t.com.tashilatappprototype.R;

/**
 * Created by laanaya on 8/4/17.
 */

public class Utils {

    public static void replaceFragement(Fragment fragment, Activity activity) {
        if (fragment != null && !fragment.isVisible()) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(null).commit();
        } else Log.e("Fragements", "Error in creating fragment");
    }


    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
