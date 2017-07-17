package ar.com.wolox.android.example;

import com.readystatesoftware.chuck.ChuckInterceptor;
import com.squareup.leakcanary.LeakCanary;

import ar.com.wolox.android.example.di.DaggerAppComponent;
import ar.com.wolox.wolmo.core.WolmoApplication;
import ar.com.wolox.wolmo.networking.di.DaggerNetworkingComponent;
import ar.com.wolox.wolmo.networking.di.NetworkingComponent;

import dagger.android.AndroidInjector;

public class BootstrapApplication extends WolmoApplication {

    @Override
    public void onInit() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    @Override
    protected AndroidInjector<BootstrapApplication> applicationInjector() {
        return DaggerAppComponent.builder()
            .networkingComponent(buildDaggerNetworkingComponent())
            .sharedPreferencesName(Configuration.SHARED_PREFERENCES_NAME)
            .application(this)
            .create(this);
    }

    private NetworkingComponent buildDaggerNetworkingComponent() {
        return DaggerNetworkingComponent.builder()
            .baseUrl(Configuration.EXAMPLE_CONFIGURAITON_KEY)
            .okHttpInterceptors(new ChuckInterceptor(this))
            .build();
    }
}
