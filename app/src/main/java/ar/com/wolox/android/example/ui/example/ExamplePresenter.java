package ar.com.wolox.android.example.ui.example;

import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

import javax.inject.Inject;

public class ExamplePresenter extends BasePresenter<IExampleView> {

    // Constants
    public static final String TAG = "ExamplePresenter";
    public static final int NUMBER_MAX = 1000;
    public static final int NUMBER_MIN = 200;

    // Variables
    private UserSession mUserSession;

    // Constructor
    @Inject
    public ExamplePresenter(UserSession userSession) {
        mUserSession = userSession;
    }

    public void storeUsername(String text) {
        mUserSession.setUsername(text);
        getView().onUsernameSaved();
    }
}
