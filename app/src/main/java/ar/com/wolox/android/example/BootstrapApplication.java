package ar.com.wolox.android.example;

import com.squareup.leakcanary.LeakCanary;

import ar.com.wolox.wolmo.networking.retrofit.NetworkingApplication;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;

public class BootstrapApplication extends NetworkingApplication {

    @Override
    public RetrofitServices getRetrofitServices() {
        return new ExampleRetrofitServices();
    }

    @Override
    public void onInit() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
