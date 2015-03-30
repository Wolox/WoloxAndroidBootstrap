package ar.com.wolox.android.service.interceptor;

import retrofit.RequestInterceptor;

public class ApiRestInterceptor implements RequestInterceptor {

    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String ACCEPT_HEADER = "Accept";

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader(USER_AGENT_HEADER, "Android");
        request.addHeader(ACCEPT_HEADER, "application/json");
    }
}
