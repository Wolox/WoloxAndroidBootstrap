package ar.com.wolox.android.example;

import ar.com.wolox.wolmo.core.service.WoloxRetrofitServices;

public class ExampleRetrofitServices extends WoloxRetrofitServices {
    @Override
    public String getApiEndpoint() {
        return Configuration.API_ENDPOINT;
    }

    @Override
    public String getApiaryEndpoint() {
        return Configuration.APIARY_ENDPOINT;
    }
}
