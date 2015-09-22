package ar.com.wolox.android.fragment.endless;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.Collection;

import ar.com.wolox.android.R;
import ar.com.wolox.android.adapter.WoloxAdapter;
import ar.com.wolox.android.fragment.WoloxFragment;
import ar.com.wolox.android.service.provider.Provider;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public abstract class EndlessListFragment<T, VH extends RecyclerView.ViewHolder> extends WoloxFragment implements OnMoreListener {

    public static final String TAG = "ENDLESS_LIST_FRAGMENT";

    protected SuperRecyclerView mRecycler;
    protected WoloxAdapter<T,VH> mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected Provider<Collection<T>> mProvider;
    protected EndlessListFragment.OnItemClickListener<T> onItemClickListener;

    private static final int FIRST_PAGE = 1;
    private static final int PER_PAGE = 5;
    private int mCurrentPage;

    @Override
    protected void setUi(View v) {
        mRecycler = (SuperRecyclerView) v.findViewById(R.id.list);
    }

    protected void init() {
        mCurrentPage = FIRST_PAGE;

        mLayoutManager = layoutManager();
        mRecycler.setLayoutManager(mLayoutManager);

        mAdapter = adapter();
        mRecycler.setAdapter(mAdapter);

        mRecycler.getSwipeToRefresh().setEnabled(true);

        mProvider = provider();
        mRecycler.setupMoreListener(this, 10);

        mRecycler.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.clear();
                mCurrentPage = FIRST_PAGE;
                loadMore();
            }
        });

        onItemClickListener = onItemClickListener();
        if (onItemClickListener != null) {
            ItemClickSupport.addTo(mRecycler.getRecyclerView()).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    onItemClickListener.onItemClick(mAdapter.get(position));
                }
            });
        }

        loadMore();
    }

    @Override
    public final void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
        loadMore();
    }

    private void loadMore() {
        Log.w(TAG, "loading page: " + mCurrentPage);
        mProvider.provide(mCurrentPage, PER_PAGE, new Callback<Collection<T>>() {
            @Override
            public void success(final Collection<T> collection, Response response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.appendBottomAll(collection);
                        mCurrentPage++;
                        mRecycler.setLoadingMore(false);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                mRecycler.setLoadingMore(false);
            }
        });
    }

    protected T get(int position) {
        return mAdapter.get(position);
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void populate() {
    }

    protected abstract RecyclerView.LayoutManager layoutManager();
    protected abstract WoloxAdapter<T,VH> adapter();
    protected abstract Provider<Collection<T>> provider();
    protected abstract EndlessListFragment.OnItemClickListener<T> onItemClickListener();

    public static interface OnItemClickListener<E> {
        public void onItemClick(E item);
    }
}
