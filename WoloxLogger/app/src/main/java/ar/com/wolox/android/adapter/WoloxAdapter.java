package ar.com.wolox.android.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class WoloxAdapter<T, V extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<V> {

    private List<T> mDataset;

    public WoloxAdapter() {
        mDataset = new ArrayList<>();
    }

    public T get(int position) {
        return mDataset.get(position);
    }

    @Override
    public final int getItemCount() {
        return mDataset.size();
    }

    public final void insert(T elem, int position) {
        mDataset.add(position, elem);
        notifyItemInserted(position);
    }

    public final void appendTop(T elem) {
        insert(elem, 0);
    }

    public final void appendTopAll(Collection<T> elems) {
        mDataset.addAll(0, elems);
        notifyItemRangeInserted(0, elems.size());
    }

    public final void appendBottom(T elem) {
        insert(elem, mDataset.size());
    }

    public final void appendBottomAll(Collection<T> elems) {
        int startIndex = mDataset.size();
        mDataset.addAll(startIndex, elems);
        notifyItemRangeInserted(startIndex, elems.size());
    }

    public final void remove(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public final void clear() {
        int size = mDataset.size();
        mDataset.clear();
        notifyItemRangeRemoved(0, size);
    }
}
