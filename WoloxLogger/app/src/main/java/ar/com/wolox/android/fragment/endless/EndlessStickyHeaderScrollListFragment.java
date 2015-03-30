package ar.com.wolox.android.fragment.endless;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import ar.com.wolox.android.R;
import ar.com.wolox.android.listener.EndlessScrollListener;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public abstract class EndlessStickyHeaderScrollListFragment<T> extends
        AbstractEndlessScrollFragment<T> {

    protected StickyListHeadersListView mStickyHeaderListView;

    @Override
    protected void setUi(View view) {
        super.setUi(view);
        mStickyHeaderListView = (StickyListHeadersListView)
        view.findViewById(R.id.sticky_headers_list_view);
    }

    @Override
    protected void setEndlessScrollListenerToCollection(EndlessScrollListener listener) {
        mStickyHeaderListView.setOnScrollListener(listener);
        addFooter();
    }

    @Override
    protected void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mStickyHeaderListView.setOnItemClickListener(listener);
    }

    @Override
    protected void setAdapter(BaseAdapter adapter) {
        mAdapter = adapter;
        if (mStickyHeaderListView.getAdapter() == null)
            mStickyHeaderListView.setAdapter((StickyListHeadersAdapter) mAdapter);
    }

    @Override
    protected void showList() {
        mStickyHeaderListView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void hideList() {
        mStickyHeaderListView.setVisibility(View.GONE);
    }

    @Override
    protected void addFooter() {
        mStickyHeaderListView.addFooterView(mLoadingView);
    }

    @Override
    protected void removeFooter() {
        mStickyHeaderListView.removeFooterView(mLoadingView);
    }

    @Override
    protected boolean hasHeader() {
        return mStickyHeaderListView.getHeaderViewsCount() > 0;
    }

    @Override
    public void smoothScrollToTop() {
        if (mStickyHeaderListView.getFirstVisiblePosition() > SCROLL_TO_TOP_LIMIT) {
            mStickyHeaderListView.setSelection(SCROLL_TO_TOP_LIMIT);
        }
        mStickyHeaderListView.smoothScrollToPosition(0);
    }
}
