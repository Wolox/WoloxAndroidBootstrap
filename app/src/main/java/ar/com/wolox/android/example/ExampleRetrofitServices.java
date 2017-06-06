package ar.com.wolox.android.example;

import android.support.annotation.NonNull;

import com.readystatesoftware.chuck.ChuckInterceptor;

import ar.com.wolox.wolmo.networking.BuildConfig;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ExampleRetrofitServices extends RetrofitServices {

    @NonNull
    @Override
    public String getApiEndpoint() {
        return Configuration.API_ENDPOINT;
    }

    @Override
    protected void initClient(@NonNull OkHttpClient.Builder builder) {

        // Only add logs in debug versions (not production ones)
        if (BuildConfig.DEBUG) {

            // Add Logcat HTTP requests logging
            HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor();
            loggerInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggerInterceptor);

            // Add Chuck interceptor to log HTTP request in a notification
            builder.addInterceptor(new ChuckInterceptor(BootstrapApplication.getInstance()));
        }
    }
}
