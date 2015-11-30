package ar.com.wolox.android.callback;

import retrofit.client.Response;

public class EmptyCallback extends WoloxCallback<Void> {

    @Override
    public void success(Void aVoid, Response response) {
        //Do nothing...
    }
}
