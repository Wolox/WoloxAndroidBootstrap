package ar.com.wolox.android.service.interceptor;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SecuredRequestInterceptor extends ApiRestInterceptor {

    public static final String SESSION_TOKEN_HEADER = "session_token";

    public Response intercept(Chain chain) throws IOException {
        super.intercept(chain);
        String token = "holis"; // AccessUtils.getAccessToken();
        if (token == null) return chain.proceed(chain.request());
        Request request = chain.request().newBuilder()
                .header(SESSION_TOKEN_HEADER, token)
                .build();
        return chain.proceed(request);
    }
}
