package ar.com.wolox.android.service.interceptor;

public class SecuredRequestInterceptor extends ApiRestInterceptor {

    public static final String SESSION_TOKEN_HEADER = "session_token";

    public void intercept(RequestFacade request) {
        super.intercept(request);
        String token = "holis"; // AccessUtils.getAccessToken();
        if (token == null) return;
        request.addHeader(SESSION_TOKEN_HEADER, token);
    }
}
