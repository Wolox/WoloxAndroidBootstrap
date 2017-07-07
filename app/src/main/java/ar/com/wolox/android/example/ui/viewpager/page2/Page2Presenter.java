package ar.com.wolox.android.example.ui.viewpager.page2;

import ar.com.wolox.android.example.model.Post;
import ar.com.wolox.android.example.network.PostService;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Page2Presenter extends BasePresenter<IPage2View> {

    @Inject RetrofitServices mRetrofitServices;

    @Inject
    Page2Presenter() {}

    @Override
    public void onViewAttached() {
        mRetrofitServices.getService(PostService.class).getPostById(1).enqueue(new NetworkCallback<Post>() {
            @Override
            public void onResponseSuccessful(final Post response) {
                runIfViewAttached(new Consumer<IPage2View>() {
                    @Override
                    public void accept(IPage2View view) {
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
