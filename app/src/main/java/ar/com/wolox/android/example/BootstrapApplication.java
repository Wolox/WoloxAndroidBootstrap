package ar.com.wolox.android.example;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.squareup.leakcanary.LeakCanary;

import ar.com.wolox.android.BuildConfig;
import ar.com.wolox.android.example.di.DaggerAppComponent;
import ar.com.wolox.wolmo.core.WolmoApplication;
import ar.com.wolox.wolmo.networking.di.DaggerNetworkingComponent;
import ar.com.wolox.wolmo.networking.di.NetworkingComponent;

import dagger.android.AndroidInjector;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

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
        return DaggerAppComponent.builder().networkingComponent(buildDaggerNetworkingComponent())
            .sharedPreferencesName(Configuration.SHARED_PREFERENCES_NAME).application(this)
            .create(this);
    }

    private NetworkingComponent buildDaggerNetworkingComponent() {
        NetworkingComponent.Builder builder =
            DaggerNetworkingComponent.builder().baseUrl(Configuration.EXAMPLE_CONFIGURAITON_KEY)
                .gsonNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        if (BuildConfig.DEBUG) {
            builder.okHttpInterceptors(
                buildHttpLoggingInterceptor(Level.BODY), new ChuckInterceptor(this));
        }

        return builder.build();
    }

    /**
     * Returns a {@link HttpLoggingInterceptor} with the level given by <b>level</b>.
     *
     * @param level - Logging level for the interceptor.
     * @return New instance of interceptor
     */
    private static HttpLoggingInterceptor buildHttpLoggingInterceptor(
          @NonNull HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor();
        loggerInterceptor.setLevel(level);
        return loggerInterceptor;
    }
}
