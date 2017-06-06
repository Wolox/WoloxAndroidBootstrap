package ar.com.wolox.android.example.ui;


import android.util.Log;

import java.util.Random;

import ar.com.wolox.android.example.model.ExampleModel;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

public class ExamplePresenter extends BasePresenter<ExampleView> {

    // Constants
    public static final String TAG = "ExamplePresenter";
    public static final int NUMBER_MAX = 1000;
    public static final int NUMBER_MIN = 200;

    // Variables
    private ExampleModel mExampleModel = new ExampleModel();

    // Constructor
    public ExamplePresenter(ExampleView viewInstance) {
        super(viewInstance);
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
