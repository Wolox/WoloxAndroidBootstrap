package ar.com.wolox.android.callback;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class WoloxCallback<T> implements Callback<T> {

    private static final String AUTH_ERROR =
            "java.io.IOException: No authentication challenges found";

    @Override
    public void failure(RetrofitError retrofitError) {
        if (retrofitError == null) return;
        if (authError(retrofitError)) {
//            AccessUtils.refreshSessionToken();
        }
    }

    protected boolean authError(RetrofitError retrofitError) {
        if (retrofitError == null) return false;
        return messageIsAuthError(retrofitError) || is401(retrofitError.getResponse());
    }

    private boolean is401(Response response) {
        return response != null && response.getStatus() == 401;
    }

    private boolean messageIsAuthError(RetrofitError retrofitError) {
        return retrofitError.getMessage() != null
                && (retrofitError.getMessage().contains(AUTH_ERROR)
                        || AUTH_ERROR.contains(retrofitError.getMessage()));
    }
}

