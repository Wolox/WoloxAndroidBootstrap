package ar.com.wolox.android.example.ui.viewpager.request;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.support.annotation.NonNull;

import ar.com.wolox.android.example.model.Post;
import ar.com.wolox.android.example.network.PostService;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class RequestPresenterTest {

    private IRequestView mPage2View;
    private RequestPresenter mRequestPresenter;
    private RetrofitServices mRetrofitServices;

    private Call<Post> mPostCall;
    private Post mPost;

    @Before
    @SuppressWarnings("unchecked")
    public void createInstances() {
        mPage2View = mock(IRequestView.class);
        mRetrofitServices = mock(RetrofitServices.class);
        mPostCall = mock(Call.class);
        mPost = mock(Post.class);

        when(mPost.getTitle()).thenReturn("Title");
        when(mPost.getBody()).thenReturn("Body");

        when(mRetrofitServices.getService(any(Class.class))).thenReturn((PostService) id -> mPostCall);

        mRequestPresenter = new RequestPresenter(mRetrofitServices);
    }

    /**
     * NOTE: This is only an example of more complex mocks using Mockito.
     * You shouldn't mock inner workings of the classes. You can use "MockWebServer":
     * https://github.com/square/okhttp/tree/master/mockwebserver
     */
    @Test
    @SuppressWarnings("unchecked")
    public void callViewOnSuccess() {
        doAnswer((Answer<Object>) invocation -> {
            ((Callback<Post>) invocation.getArguments()[0])
                .onResponse(mPostCall, Response.success(mPost));
            return null;
        }).when(mPostCall).enqueue(any(Callback.class));

        // Verify view updates
        mRequestPresenter.attachView(mPage2View);
        verify(mPage2View, times(1)).setNewsTitle(matches("Title"));
        verify(mPage2View, times(1)).setNewsBody(matches("Body"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void dontCallViewIfNotAttached() {
        doAnswer(invocation -> {
            ((Callback<Post>) invocation.getArguments()[0])
                .onResponse(mPostCall, Response.success(mPost));
            return null;
        }).when(mPostCall).enqueue(any(Callback.class));

        mRequestPresenter.onViewAttached();
        verify(mPage2View, times(0)).setNewsTitle(anyString());
        verify(mPage2View, times(0)).setNewsBody(anyString());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void callViewOnFailure() {
        doAnswer((Answer<Object>) invocation -> {
            ((Callback<Post>) invocation.getArguments()[0])
                .onFailure(mPostCall, new Throwable("Error"));
            return null;
        }).when(mPostCall).enqueue(any(Callback.class));

        // Verify view updates
        mRequestPresenter.attachView(mPage2View);
        verify(mPage2View, times(1)).showError();
    }

}
