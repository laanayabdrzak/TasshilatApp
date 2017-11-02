package m2t.com.tashilatappprototype.common.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.Operator;
import m2t.com.tashilatappprototype.common.pojo.OperatorWS;

/**
 * Created by laanaya on 8/4/17.
 */

public class Utils {

    public static String TAG = Utils.class.getSimpleName();

    public static void replaceFragement(Fragment fragment, Activity activity) {
        if (fragment != null && !fragment.isVisible()) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(fragment.getClass().getName()).commit();
        } else Log.e("Fragements", "Error in creating fragment");
    }

    public static void replaceFragementFromContext(Fragment fragment, Context context) {
        if (fragment != null && !fragment.isVisible()) {
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(fragment.getClass().getName()).commit();
        } else Log.e("Fragements", "Error in creating fragment");
    }


    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * load data from arrayWS to ArrayDB
     *
     */
    public static List<Operator> fromWSToDB(List<OperatorWS> operWS) {

        List<Operator> operDB = new ArrayList<>();

        for (int i = 0; i < operWS.size(); i++) {
            Operator opr = new Operator();

            opr.setID_OPER(operWS.get(i).getID_OPER());
            opr.setName(operWS.get(i).getName());
            opr.setDescription(operWS.get(i).getDescription());
            if (operWS.get(i).getFavorite())
                opr.setFavorite(1);
            else opr.setFavorite(0);
            opr.setCategorie_id(operWS.get(i).getCategorie().getItem().getID());
            opr.setCategorie_name(operWS.get(i).getCategorie().getItem().getName());

            Log.d(TAG,"item " + i + "==> "+ opr.getName() + " id "+ opr.getID_OPER());
            operDB.add(opr);
        }

        return operDB;
    }
}
