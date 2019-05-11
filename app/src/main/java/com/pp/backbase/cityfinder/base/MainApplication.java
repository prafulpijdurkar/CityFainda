package com.pp.backbase.cityfinder.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Praful Pijdurkar on 5/7/19.
 * This is the main Application, Base object of the application.
 */
public class MainApplication extends Application {
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
     }

}
