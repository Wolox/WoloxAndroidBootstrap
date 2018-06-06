package ar.com.wolox.android.example.ui.viewpager.random

import android.util.Log
import ar.com.wolox.android.example.model.ExampleModel
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import java.util.Random
import javax.inject.Inject

class RandomPresenter @Inject constructor(
        private val mUserSession: UserSession
) : BasePresenter<IRandomView>() {

    override fun onViewAttached() {
        view.setUsername(mUserSession.username ?: "")
    }

    fun generateRandomNumber(): Int {
        // Do some backend logic here, in this case generate just some random number
        // between a given range and update our model
        val model = ExampleModel(Random().nextInt(NUMBER_MAX - NUMBER_MIN + 1) + NUMBER_MIN)
        Log.i(TAG, "A new random number has been generated: ${model.someNumber}")

        // Notify the view so it can update the UI however it wants to
        view.onRandomNumberUpdate(model.someNumber)

        // Note: Remember that the presenter doesn't know and doesn't care about what the View
        // does with the new value of the random number, it only cares about the backend
        return model.someNumber
    }

    companion object {
        private const val NUMBER_MIN = 1
        private const val NUMBER_MAX = 35000
        private const val TAG = "RandomPresenter"
    }
}
