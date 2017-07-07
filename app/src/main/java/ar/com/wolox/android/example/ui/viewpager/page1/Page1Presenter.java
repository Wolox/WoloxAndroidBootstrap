package ar.com.wolox.android.example.ui.viewpager.page1;

import static ar.com.wolox.android.example.ui.random.ExamplePresenter.NUMBER_MAX;
import static ar.com.wolox.android.example.ui.random.ExamplePresenter.NUMBER_MIN;

import android.util.Log;

import ar.com.wolox.android.example.model.ExampleModel;
import ar.com.wolox.android.example.utils.Extras;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.core.util.StorageUtils;

import java.util.Random;

import javax.inject.Inject;

public class Page1Presenter extends BasePresenter<IPage1View> {

    public static final String TAG = "Page1Presenter";

    private StorageUtils mStorageUtils;
    private ExampleModel mExampleModel = new ExampleModel();


    @Inject
    Page1Presenter(StorageUtils storageUtils) {
        mStorageUtils = storageUtils;
    }

    @Override
    public void onViewAttached() {
        getView().setUsername(mStorageUtils.getStringFromSharedPreferences(Extras.UserLogin.USERNAME, "None"));
    }


    public int generateRandomNumber() {

        // Do some backend logic here, in this case generate just some random number
        // between a given range and update our model
        mExampleModel.someNumber = (new Random().nextInt((NUMBER_MAX - NUMBER_MIN) + 1)) + NUMBER_MIN;
        Log.i(TAG, "A new random number has been generated: " + mExampleModel.someNumber);

        // Notify the view so it can update the UI however it wants to
        getView().onRandomNumberUpdate(mExampleModel.someNumber);

        // Note: Remember that the presenter doesn't know and doesn't care about what the View
        // does with the new value of the random number, it only cares about the backend
        return mExampleModel.someNumber;
    }
}
