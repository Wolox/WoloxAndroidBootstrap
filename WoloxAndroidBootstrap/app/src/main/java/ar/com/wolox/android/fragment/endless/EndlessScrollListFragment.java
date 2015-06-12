package ar.com.wolox.android.fragment.endless;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.listener.EndlessScrollListener;

public abstract class EndlessScrollListFragment<T> extends AbstractEndlessScrollFragment<T> {

    protected ListView mListView;

    @Override
    protected void setUi(View view) {
        super.setUi(view);
        mListView = (ListView) view.findViewById(R.id.list_view);
    }

    @Override
    protected void setEndlessScrollListenerToCollection(EndlessScrollListener listener) {
        mListView.setOnScrollListener(listener);
        addFooter();
    }

    @Override
    protected void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListView.setOnItemClickListener(listener);
    }

    @Override
    protected void setAdapter(BaseAdapter adapter) {
        mAdapter = adapter;
        if (mListView.getAdapter() == null) mListView.setAdapter(mAdapter);
    }

    @Override
    protected void showList() {
        mListView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void hideList() {
        mListView.setVisibility(View.GONE);
    }

    @Override
    protected void addFooter() {
        mListView.addFooterView(mLoadingView);
    }

    @Override
    protected void removeFooter() {
        mListView.removeFooterView(mLoadingView);
    }

    @Override
    protected boolean hasHeader() {
        return mListView.getHeaderViewsCount() > 0;
    }

    @Override
    public void smoothScrollToTop() {
        if (mListView.getFirstVisiblePosition() > SCROLL_TO_TOP_LIMIT) {
            mListView.setSelection(SCROLL_TO_TOP_LIMIT);
        }
        mListView.smoothScrollToPosition(0);
    }
}
