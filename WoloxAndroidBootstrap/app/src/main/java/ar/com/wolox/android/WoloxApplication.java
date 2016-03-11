package ar.com.wolox.android;

import android.app.Application;

import ar.com.wolox.android.service.RetrofitServices;

public class WoloxApplication extends Application {

    private static WoloxApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        RetrofitServices.init();
    }

    public static WoloxApplication getInstance() {
        return sApplication;
    }

}
