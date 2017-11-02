package m2t.com.tashilatappprototype;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by laanaya on 10/27/17.
 */

public class TasshilatApplication extends Application {

    public static TasshilatApplication INSTANCE;


    public static TasshilatApplication get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


}
