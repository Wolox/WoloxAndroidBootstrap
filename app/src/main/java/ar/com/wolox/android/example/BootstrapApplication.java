package ar.com.wolox.android.example;

import ar.com.wolox.android.example.ExampleRetrofitServices;
import ar.com.wolox.wolmo.core.WoloxApplication;
import ar.com.wolox.wolmo.core.service.WoloxRetrofitServices;

public class BootstrapApplication extends WoloxApplication {
    @Override
    public void onInit() {
        // TODO
    }

    @Override
    public WoloxRetrofitServices getRetrofitServices() {
        return new ExampleRetrofitServices();
    }
}
