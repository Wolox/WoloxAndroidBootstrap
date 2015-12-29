package ar.com.wolox.android.example;


import android.content.Context;
import android.util.Log;

import java.util.Random;

import ar.com.wolox.android.presenter.BasePresenter;

public class ExamplePresenter extends BasePresenter<ExampleView> {

    // Constants
    public static final String TAG = "ExamplePresenter";

    // Variables
    private ExampleModel mExampleModel = new ExampleModel();

    // Constructor
    public ExamplePresenter(ExampleView viewInstance, Context context) {
        super(viewInstance, context);
    }

    public void generateRandomNumber() {

        // Do some backend logic here, in this case generate just some random number and update our model
        mExampleModel.someNumber = new Random().nextInt();
        Log.i(TAG, "A new random number has been generated: " + mExampleModel.someNumber);

        // Notify the view so it can update the UI however it wants to
        getView().onRandomNumberUpdate(mExampleModel.someNumber);

        // Note: Remember that the presenter doesn't know and doesn't care about what the View
        // does with the new value of the random number, it only cares about the backend
    }

}
