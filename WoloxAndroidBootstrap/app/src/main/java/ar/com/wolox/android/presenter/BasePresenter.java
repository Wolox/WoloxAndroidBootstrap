package ar.com.wolox.android.presenter;

import android.content.Context;

/**
 * Abstract presenter that provides the view to the specific presenters.
 */
public class BasePresenter<T> {

    private final T mViewInstance;
    private final Context mContext;

    public BasePresenter(T viewInstance, Context context) {
        this.mViewInstance = viewInstance;
        this.mContext = context;
    }

    protected T getView() {
        return mViewInstance;
    }

    protected Context getContext() {
        return mContext;
    }

}
