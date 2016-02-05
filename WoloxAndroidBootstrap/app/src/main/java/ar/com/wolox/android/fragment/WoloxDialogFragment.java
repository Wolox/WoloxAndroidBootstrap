package ar.com.wolox.android.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ar.com.wolox.android.presenter.BasePresenter;

public abstract class WoloxDialogFragment<T extends BasePresenter> extends DialogFragment {

    protected T presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(layout(), container, false);
        presenter = createPresenter();
        setUi(v);
        init();
        populate();
        setListeners();
        return v;
    }

    /**
     * Returns the layout id for the inflater so the view can be populated
     */
    protected abstract int layout();

    /**
     * Loads the view elements for the fragment
     */
    protected abstract void setUi(View v);

    /**
     * Initializes any variables that the fragment needs
     */
    protected abstract void init();

    /**
     * Populates the view elements of the fragment
     */
    protected abstract void populate();

    /**
     * Sets the listeners for the views of the fragment
     */
    protected abstract void setListeners();

    /**
     * Create the presenter for this fragment
     */
    protected abstract T createPresenter();

    protected void showToast(int resId) {
        Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
