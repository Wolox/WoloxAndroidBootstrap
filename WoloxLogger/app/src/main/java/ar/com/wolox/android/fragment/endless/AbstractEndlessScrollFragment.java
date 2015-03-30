package ar.com.wolox.android.fragment.endless;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import ar.com.wolox.android.R;
import ar.com.wolox.android.callback.WoloxCallback;
import ar.com.wolox.android.fragment.WoloxFragment;
import ar.com.wolox.android.listener.EndlessScrollListener;
import ar.com.wolox.android.listener.SmoothScrollable;
import ar.com.wolox.android.service.provider.Provider;
import retrofit.RetrofitError;
import retrofit.client.Response;

abstract class AbstractEndlessScrollFragment<T> extends WoloxFragment
        implements SwipeRefreshLayout.OnRefreshListener, SmoothScrollable {

    public static final int ELEMENTS_PER_PAGE = 10;

    protected static final int SCROLL_TO_TOP_LIMIT = 15;

    protected enum Status { REFRESHING, LOADING, LOADED, ERROR }

    protected Status mStatus;
    protected List<T> mList = new ArrayList<>();
    protected Deque<Integer> mLoadQueue = new LinkedList<>();
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected View mLoadingView;
    protected View mNoResultsView;
    protected Button mRetry;
    protected LinearLayout mErrorView;
    protected BaseAdapter mAdapter;
    protected ViewGroup mReloadViewGroup;
    protected Provider mProvider;
    protected EndlessScrollListener mEndlessScrollListener;

    private int mStartOffset = 0;

    protected abstract BaseAdapter loadAdapter();

    protected abstract Provider loadProvider();

    protected abstract AdapterView.OnItemClickListener getItemClickListener();

    protected abstract void showList();

    protected abstract void hideList();

    protected abstract void addFooter();

    protected abstract void removeFooter();

    protected abstract void setOnItemClickListener(AdapterView.OnItemClickListener listener);

    protected abstract boolean hasHeader();

    protected abstract void setEndlessScrollListenerToCollection(EndlessScrollListener listener);

    protected abstract void setAdapter(BaseAdapter adapter);

    public void setStartOffset(int startOffset) {
        this.mStartOffset = startOffset;
    }

    @Override
    public void onRefresh() {
        reload();
    }

    public void reload() {
        if (mStatus == Status.REFRESHING
                || mStatus == Status.LOADING
                || mList == null
                || mAdapter == null) {
            return;
        }
        mStatus = Status.REFRESHING;
        mEndlessScrollListener.reset();
        clearQueue();
        mList.clear();
        mAdapter.notifyDataSetChanged();
        loadMore(1);
    }

    @Override
    protected void setUi(View view) {
        mReloadViewGroup = (ViewGroup) view.findViewById(R.id.reload_view_group);
        mNoResultsView = view.findViewById(R.id.no_results);
        mNoResultsView.setVisibility(View.GONE);
        mErrorView = (LinearLayout) view.findViewById(R.id.error_wrapper);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_view);
        mLoadingView = View.inflate(getActivity(), R.layout.loading, null);
        mRetry = (Button) view.findViewById(R.id.retry);
    }

    @Override
    protected void setListeners() {
        setRefreshListener(this);
    }

    @Override
    protected void populate() {
        if (mList == null) mList = new ArrayList<>();
        loadMore(1);
    }

    @Override
    protected void init() {
        mAdapter = loadAdapter();
        mProvider = loadProvider();
    }

    protected int getEndlessScrollAmount() {
        return ELEMENTS_PER_PAGE;
    }

    protected void retry() {
        onRefresh();
    }

    private void setRefreshListener(SwipeRefreshLayout.OnRefreshListener target) {
        mSwipeRefreshLayout.setOnRefreshListener(target);
        mEndlessScrollListener = new EndlessScrollListener(getEndlessScrollAmount()) {
            @Override
            public void onScrollMore(int currentPage) {
                loadMore(currentPage);
            }
        };
        mReloadViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        });
        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry();
            }
        });
        setEndlessScrollListenerToCollection(mEndlessScrollListener);
    }

    private void setStatusLoading() {
        if (mStatus == Status.LOADING) return;
        if (mStatus != Status.REFRESHING) {
            addFooter();
        }
        mStatus = Status.LOADING;
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        mNoResultsView.setVisibility(View.GONE);
        showList();
        setAdapter(mAdapter);
    }

    private void setStatusLoaded() {
        if (mStatus == Status.LOADING) removeFooter();
        mSwipeRefreshLayout.setRefreshing(false);
        mErrorView.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        if (mList.isEmpty()) {
            mReloadViewGroup.setVisibility(View.VISIBLE);
            mNoResultsView.setVisibility(View.VISIBLE);
        } else {
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
            mReloadViewGroup.setVisibility(View.GONE);
            mNoResultsView.setVisibility(View.GONE);
            showList();
        }
        mStatus = Status.LOADED;
    }

    private void setStatusError() {
        if (mStatus == Status.LOADING) removeFooter();
        mSwipeRefreshLayout.setRefreshing(false);
        mNoResultsView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mStatus = Status.ERROR;
    }

    private void clearQueue() {
        mLoadQueue.clear();
    }

    private void loadMore(final int currentPage) {
        if (mStatus == Status.LOADING) {
            if (currentPage > 1) mLoadQueue.offer(currentPage);
            return;
        }
        setStatusLoading();
        mProvider.provide(currentPage, ELEMENTS_PER_PAGE,
                new WoloxCallback<List<T>>() {
                    @Override
                    public void success(List<T> list, Response response) {
                        if (currentPage == 1) {
                            mList.clear();
                        }
                        if (getActivity() != null &&  list != null && !list.isEmpty()) {
                            mList.addAll(list);
                            setOnItemClickListener(getItemClickListener());
                            mAdapter.notifyDataSetChanged();
                        }
                        setStatusLoaded();
                        if (!mLoadQueue.isEmpty()) loadMore(mLoadQueue.poll());
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        super.failure(retrofitError);
                        setStatusError();
                    }
                }
        );
    }
}
