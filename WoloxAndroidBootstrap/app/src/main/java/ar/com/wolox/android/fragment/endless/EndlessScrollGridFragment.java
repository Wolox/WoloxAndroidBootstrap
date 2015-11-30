package ar.com.wolox.android.fragment.endless;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.listener.EndlessScrollListener;

public abstract class EndlessScrollGridFragment<T> extends AbstractEndlessScrollFragment<T> {

    protected GridView mGridView;

    @Override
    protected void setUi(View view) {
        super.setUi(view);
        mGridView = (GridView) view.findViewById(R.id.grid_view);
    }

    @Override
    protected void setEndlessScrollListenerToCollection(EndlessScrollListener listener) {
        mGridView.setOnScrollListener(listener);
    }

    @Override
    protected void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mGridView.setOnItemClickListener(listener);
    }

    @Override
    protected void setAdapter(BaseAdapter adapter) {
        mAdapter = adapter;
        if (mGridView.getAdapter() == null) mGridView.setAdapter(mAdapter);
    }

    @Override
    protected void showList() {
        mGridView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void hideList() {
        mGridView.setVisibility(View.GONE);
    }

    @Override
    protected void addFooter() {
    }

    @Override
    protected void removeFooter() {
    }

    @Override
    protected boolean hasHeader() {
        return false;
    }

    @Override
    public void smoothScrollToTop() {
        if (mGridView.getFirstVisiblePosition() > SCROLL_TO_TOP_LIMIT) {
            mGridView.setSelection(SCROLL_TO_TOP_LIMIT);
        }
        mGridView.smoothScrollToPosition(0);
    }
}
