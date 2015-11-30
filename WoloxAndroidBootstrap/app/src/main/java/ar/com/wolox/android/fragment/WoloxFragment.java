package ar.com.wolox.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public abstract class WoloxFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(layout(), container, false);
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

    protected void showToast(int resId) {
        Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    protected void replaceFragment(int resId, Fragment f) {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(resId, f)
                .commit();
    }

    protected void hideKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }
}
