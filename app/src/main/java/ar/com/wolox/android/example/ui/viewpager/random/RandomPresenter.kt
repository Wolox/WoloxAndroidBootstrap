package ar.com.wolox.android.example.ui.viewpager.random

import android.util.Log
import ar.com.wolox.android.example.model.ExampleModel
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.util.Logger
import ar.com.wolox.wolmo.core.util.ToastFactory
import java.util.Random
import javax.inject.Inject

class RandomPresenter @Inject constructor(private val toastFactory: ToastFactory) : BasePresenter<RandomView>() {

    @Inject
    lateinit var logger: Logger

    override fun onViewAttached() {
        toastFactory.show("We don't show toasts from presenters, this should be done on the fragment.")
        logger.d("View Attached")
    }

    fun onRandomizeButtonClicked() {
        // Do some backend logic here, in this case generate just some random number
        // between a given range and update our model
        val model = ExampleModel(Random().nextInt(NUMBER_MAX - NUMBER_MIN + 1) + NUMBER_MIN)
        Log.i(TAG, "A new random number has been generated: ${model.someNumber}")

        // Notify the view so it can update the UI however it wants to
        view?.setRandom(model.someNumber)
    }

    companion object {
        private const val NUMBER_MIN = 1
        private const val NUMBER_MAX = 35000
        private const val TAG = "RandomPresenter"
    }
}
