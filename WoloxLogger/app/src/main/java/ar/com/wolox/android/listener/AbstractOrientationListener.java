package ar.com.wolox.android.listener;

import android.widget.AbsListView;

public abstract class AbstractOrientationListener implements AbsListView.OnScrollListener,
        OnScrollOrientationListener {

    private int mLastFirstVisibleItem = 0;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //nothing to do here.
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        int oldVisibleItem = mLastFirstVisibleItem;
        mLastFirstVisibleItem = firstVisibleItem;
        if (oldVisibleItem < firstVisibleItem) {
            onScrollDown();
        }
        if (oldVisibleItem > firstVisibleItem) {
            onScrollUp();
        }
    }

    public int getLastFirstVisibleItem() {
        return mLastFirstVisibleItem;
    }

    public abstract void onScrollDown();

    public abstract void onScrollUp();
}