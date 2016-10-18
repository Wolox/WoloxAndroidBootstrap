package ar.com.wolox.android.service;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import ar.com.wolox.android.Configuration;
import ar.com.wolox.android.service.interceptor.SecuredRequestInterceptor;
import ar.com.wolox.android.service.serializer.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {

    private static Retrofit sRetrofit;
    private static Retrofit sApiaryRetrofit;
    private static Map<Class, Object> sServices;

    public static void init() {
        sServices = new HashMap<>();
        Gson gson = GsonBuilder.getBasicGsonBuilder().create();

        HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor();
        loggerInterceptor.setLevel(HttpL oggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SecuredRequestInterceptor())
                .addInterceptor(loggerInterceptor).build();

        sRetrofit = new Retrofit.Builder()
                .baseUrl(Configuration.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        sApiaryRetrofit = new Retrofit.Builder()
                .baseUrl(Configuration.APIARY_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public static <T> T getApiaryService(Class<T> clazz) {
        T service = (T) sServices.get(clazz);
        if (service != null) return service;
        service = sApiaryRetrofit.create(clazz);
        sServices.put(clazz, service);
        return service;
    }

    public static <T> T getService(Class<T> clazz) {
        T service = (T) sServices.get(clazz);
        if (service != null) return service;
        service = sRetrofit.create(clazz);
        sServices.put(clazz, service);
        return service;
    }

    /*Define all services here, like this:
     *
     * public static UserService user() {
     *  return getService(UserService.class);
     * }
     *
     */
}
