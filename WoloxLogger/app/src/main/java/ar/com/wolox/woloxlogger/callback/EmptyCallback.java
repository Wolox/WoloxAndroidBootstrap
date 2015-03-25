package ar.com.wolox.woloxlogger.callback;

import ar.com.wolox.woloxlogger.callback.WoloxCallback;
import retrofit.client.Response;

public class EmptyCallback extends WoloxCallback<Void> {

    @Override
    public void success(Void aVoid, Response response) {
        //Do nothing...
    }
}
