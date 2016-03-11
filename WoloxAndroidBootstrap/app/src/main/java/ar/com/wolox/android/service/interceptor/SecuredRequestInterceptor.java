package ar.com.wolox.android.service.interceptor;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SecuredRequestInterceptor extends ApiRestInterceptor {

    public static final String SESSION_TOKEN_HEADER = "Authorization";

    public void addHeaders(Request.Builder requestBuilder) {
        String token = "holis"; // AccessUtils.getSessionToken()
        if (token == null) return;
        requestBuilder.addHeader(SESSION_TOKEN_HEADER, token);
    }
}