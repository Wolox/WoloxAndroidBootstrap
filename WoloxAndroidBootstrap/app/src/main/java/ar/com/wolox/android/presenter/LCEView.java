package ar.com.wolox.android.presenter;

/**
 * Interface to extend from the views that have a loading view along with an error one as well as
 * content that loads asynchronously.
 */
public interface LCEView<T> extends LEView {

    /**
     * Show the content view.
     *
     * <b>The content view must have the id = R.id.contentView</b>
     */
    void showContent();

    /**
     * The data that should be displayed with {@link #showContent()}
     */
    void setData(T data);

}
