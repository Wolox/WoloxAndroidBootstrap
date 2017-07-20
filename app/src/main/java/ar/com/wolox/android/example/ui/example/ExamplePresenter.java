package ar.com.wolox.android.example.ui.example;

import ar.com.wolox.android.example.model.ExampleModel;
import ar.com.wolox.android.example.utils.Extras;
import ar.com.wolox.android.example.utils.UserUtils;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.core.util.StorageUtils;

import javax.inject.Inject;

public class ExamplePresenter extends BasePresenter<IExampleView> {

    // Constants
    public static final String TAG = "ExamplePresenter";
    public static final int NUMBER_MAX = 1000;
    public static final int NUMBER_MIN = 200;

    // Variables
    private UserUtils mUserUtils;
    private ExampleModel mExampleModel = new ExampleModel();

    // Constructor
    @Inject
    public ExamplePresenter(UserUtils userUtils) {
        mUserUtils = userUtils;
    }

    public void storeUsername(String text) {
        mUserUtils.setUsername(text);
        getView().onUsernameSaved();
    }
}
