package ar.com.wolox.android.callback;

import android.text.TextUtils;

import java.io.IOException;

import ar.com.wolox.android.util.RetrofitErrorsUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class WoloxCallback<T> implements Callback<T> {

    private static final String authError =
            "java.io.IOException: No authentication challenges found";

    /** Successful HTTP response */
    public abstract void onSuccess(T response);

    /** Successful HTTP response but has an error body */
    public abstract void onCallFailed(ResponseBody responseBody, int code);

    /** Invoked when a network or unexpected exception occurred during the HTTP request. */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onCallFailure(t);
    }

    public abstract void onCallFailure(Throwable t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) onSuccess(response.body());
        else failure(response.errorBody(), response.code());
    }

    public void failure(ResponseBody responseBody, int code) {
        if (isAuthError(responseBody, code)) {
            onCallFailed(responseBody, code);
            //Handle authentication error
        } else onCallFailed(responseBody, code);
    }

    protected boolean isAuthError(ResponseBody responseBody, int code) {
        return responseBody != null
                && (messageIsAuthError(responseBody)
                || code == RetrofitErrorsUtils.ERROR_UNAUTHORIZED);
    }

    private boolean messageIsAuthError(ResponseBody responseBody) {
        try {
            String responseString = responseBody.string();
            return !TextUtils.isEmpty(responseString)
                    && (responseString.contains(authError)
                    || authError.contains(responseString));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return false;
    }

}