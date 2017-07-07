package ar.com.wolox.android.example.ui.random;

import android.content.SharedPreferences;
import android.util.Log;

import ar.com.wolox.android.example.model.ExampleModel;
import ar.com.wolox.android.example.utils.Extras;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.core.util.StorageUtils;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;

import java.util.Random;

import javax.inject.Inject;

public class ExamplePresenter extends BasePresenter<ExampleView> {

    // Constants
    public static final String TAG = "ExamplePresenter";
    public static final int NUMBER_MAX = 1000;
    public static final int NUMBER_MIN = 200;

    // Variables
    private StorageUtils mStorageUtils;
    private ExampleModel mExampleModel = new ExampleModel();

    // Constructor
    @Inject
    public ExamplePresenter(StorageUtils storageUtils) {
        mStorageUtils = storageUtils;
    }

    public void storeUsername(String text) {
        mStorageUtils.storeInSharedPreferences(Extras.UserLogin.USERNAME, text);
        getView().onUsernameSaved();
    }
}
