package m2t.com.tashilatappprototype.common.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.EncaisseResponse;
import m2t.com.tashilatappprototype.common.pojo.Facture;
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
     * Get Date from string
     */
    public static String getDatefromString(String d) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.FRENCH);
        try {
            Date date = format.parse(d);
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
            return dt1.format(date).toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return d;
        }

    }

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static String mntWithFees(String mnt) {
        return String.valueOf(Integer.valueOf(mnt) + 1.50);
    }

    public static String montantTTC(String mntHT, String fraisTTC) {
        return String.valueOf(Integer.valueOf(mntHT) + Integer.valueOf(fraisTTC));
    }

    /**
     * Converting Price
     */
    public static String convertPrice(String price) {
        if (price.equals(0)) return String.valueOf(0);
        else return String.valueOf(Integer.valueOf(price)/100);
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

            Log.d(TAG,"item " + i + "==> "+ opr.getName() + " id "+ opr.getID_OPER() + "Categorie " + opr.getCategorie_name());
            operDB.add(opr);
        }

        return operDB;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static void showDialogBill(String title, WebView msg, final MsgDialog feesDialog, Activity activity) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(msg);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    feesDialog.yesCase();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                feesDialog.noCase();
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void AlerDialogFacture(String title, final MsgDialog feesDialog, Activity activity,
                                         String oper, Facture facture, EncaisseResponse encaisseResponse) throws ParseException {
        String strData = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <td>Operateur</td>\n" +
                "    <td>:"+ oper +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>No recu</td>\n" +
                "    <td>:"+ encaisseResponse.getRecuNum() +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>No transaction</td>\n" +
                "    <td>:"+ encaisseResponse.getFacturierTrxNum() +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Nom Client</td>\n" +
                "    <td>:"+ facture.getNomClient() +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>No Contrat</td>\n" +
                "    <td>:"+ facture.getNumContrat() +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>No Tournee</td>\n" +
                "    <td>:"+ facture.getTournee() +"</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<table style=\"width:100%\">\n" +
                "\t<tr>\n" +
                "    \t<td>Designation</td>\n" +
                "    \t<td>Montants(Dhs)</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<p>==========================================================</p>\n" +
                "<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <td>F 645</td>\n" +
                "    <td>:ONEE</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Penalite</td>\n" +
                "    <td>:"+ Utils.convertPrice(facture.getMntPenalite()) +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Mois facturation</td>\n" +
                "    <td>:" + Utils.getDatefromString(facture.getDate()) +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Date lim paiement</td>\n" +
                "    <td>:"+ Utils.getDatefromString(facture.getDateLimite()) +"</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<p>==========================================================</p>\n" +
                "<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <td>Total TTC Transaction</td>\n" +
                "    <td>:"+ Utils.convertPrice(facture.getMntTTC()) +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Dont frais</td>\n" +
                "    <td>:"+ Utils.convertPrice(facture.getMntFrais())+"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Dont timbre</td>\n" +
                "    <td>:"+ Utils.convertPrice(facture.getMntTimbre()) +"</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Paye par</td>\n" +
                "    <td>:Espece</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<h4 align=\"center\"> TasshilatChaabiCash vous remercie</h4>\n" +
                "</body>\n" +
                "</html>";
        WebView webView = new WebView(activity);
        webView.loadData(strData, "text/html", "utf-8");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(webView);
        alertDialogBuilder.setPositiveButton("Sauvegarder", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    feesDialog.yesCase();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
