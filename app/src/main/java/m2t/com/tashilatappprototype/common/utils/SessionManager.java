package m2t.com.tashilatappprototype.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

import m2t.com.tashilatappprototype.ui.accueil.AccueilActivity;

/**
 * Created by laanaya on 10/30/17.
 */

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "TasshilatPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_CODE_ES = "codeES";
    public static final String KEY_PHPSESSID = "phpssessID";
    public static final String KEY_TOKEN_CSFR = "tokenCSFR";
    public static final String KEY_COOKIE = "cookie";
    public static final String KEY_ACC_TYPE = "accType";
    public static final String KEY_SOLDE = "solde";


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String phpSessionID, String codeES, String tokenCSFR, String tokenReq, String accType, String solde){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing data in pref
        editor.putString(KEY_CODE_ES, codeES);
        editor.putString(KEY_PHPSESSID, phpSessionID);
        editor.putString(KEY_TOKEN_CSFR, tokenCSFR);
        editor.putString(KEY_COOKIE, tokenReq);
        editor.putString(KEY_ACC_TYPE, accType);
        editor.putString(KEY_SOLDE, solde);


        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, AccueilActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }
    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        // user codeES
        user.put(KEY_CODE_ES, pref.getString(KEY_CODE_ES, null));
        // user session ID
        user.put(KEY_PHPSESSID, pref.getString(KEY_PHPSESSID, null));

        // user tokenCSFR id
        user.put(KEY_TOKEN_CSFR, pref.getString(KEY_TOKEN_CSFR, null));

        user.put(KEY_COOKIE, pref.getString(KEY_COOKIE, null));

        user.put(KEY_SOLDE, pref.getString(KEY_SOLDE, null));

        user.put(KEY_ACC_TYPE, pref.getString(KEY_ACC_TYPE, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        setLogin(false);

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, AccueilActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(IS_LOGIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
