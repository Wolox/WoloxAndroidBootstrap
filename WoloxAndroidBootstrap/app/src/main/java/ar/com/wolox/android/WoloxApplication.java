package ar.com.wolox.android;
import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import org.joda.time.LocalDate;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import ar.com.wolox.android.service.interceptor.SecuredRequestInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class WoloxApplication extends Application {

    private static WoloxApplication sApplication;
    private static Interceptor sSecureRequestInterceptor;

    static {
        buildRestServices();
    }

    public static void buildRestServices() {
        sSecureRequestInterceptor = new SecuredRequestInterceptor();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LocalDate.class, new DateDeserializer())
                .create();
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(sSecureRequestInterceptor);
        Retrofit apiaryAdapter = new Retrofit.Builder()
                .baseUrl(Configuration.APIARY_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit apiAdapter = new Retrofit.Builder()
                .baseUrl(Configuration.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public static class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2)
                throws JsonParseException {
            String originalDate = element.toString();
            char[] dateChar = originalDate.toCharArray();
            char[] dateCharConverted = Arrays.copyOfRange(dateChar, 1, dateChar.length - 1);
            String date = new String(dateCharConverted);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                return formatter.parse(date);
            } catch (ParseException e3) {
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                try {
                    originalDate = originalDate.substring(1, originalDate.length() - 2);
                    return formatter.parse(originalDate);
                } catch (ParseException e4) {
                    throw new JsonParseException(e4);
                }
            }
        }
    }

    public static WoloxApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
