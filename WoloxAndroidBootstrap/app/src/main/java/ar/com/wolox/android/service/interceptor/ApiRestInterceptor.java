package ar.com.wolox.android.service.interceptor;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ApiRestInterceptor implements Interceptor {

    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String ACCEPT_HEADER = "Accept";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader(USER_AGENT_HEADER, "Android")
                .addHeader(ACCEPT_HEADER, "application/json")
                .build();
        return chain.proceed(request);
    }
}
