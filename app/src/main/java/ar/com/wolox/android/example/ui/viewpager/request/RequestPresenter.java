package ar.com.wolox.android.example.ui.viewpager.request;

import ar.com.wolox.android.example.model.Post;
import ar.com.wolox.android.example.network.PostService;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;

import javax.inject.Inject;

import okhttp3.ResponseBody;

public class RequestPresenter extends BasePresenter<IRequestView> {

    private static final int POST_ID = 1;

    @Inject RetrofitServices mRetrofitServices;

    @Inject
    RequestPresenter() {}

    @Override
    public void onViewAttached() {
        mRetrofitServices.getService(PostService.class).getPostById(POST_ID).enqueue(new NetworkCallback<Post>() {
            @Override
            public void onResponseSuccessful(final Post response) {
                runIfViewAttached(new Consumer<IRequestView>() {
                    @Override
                    public void accept(IRequestView view) {
                        view.setNewsTitle(response.getTitle());
                        view.setNewsBody(response.getBody());
                    }
                });
            }

            @Override
            public void onResponseFailed(ResponseBody responseBody, int code) {
                runIfViewAttached(new Runnable() {
                    @Override
                    public void run() {
                        getView().showError();
                    }
                });
            }

            @Override
            public void onCallFailure(Throwable t) {
                runIfViewAttached(new Runnable() {
                    @Override
                    public void run() {
                        getView().showError();
                    }
                });
            }
        });
    }
}
