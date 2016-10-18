package ar.com.wolox.android.callback;

import okhttp3.ResponseBody;

public class EmptyCallback extends WoloxCallback<Void> {

    @Override
    public void onSuccess(Void response) {

    }

    @Override
    public void onCallFailed(ResponseBody responseBody, int code) {

    }

    @Override
    public void onCallFailure(Throwable t) {

    }
}
