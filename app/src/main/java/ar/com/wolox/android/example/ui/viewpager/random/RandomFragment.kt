package ar.com.wolox.android.example.ui.viewpager.random

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_random.*
import javax.inject.Inject

class RandomFragment @Inject constructor() : WolmoFragment<RandomPresenter>(), RandomView {

    override fun layout() = R.layout.fragment_random

    override fun init() {
    }

    override fun setListeners() {
        vRandomizeButton.setOnClickListener { presenter.onRandomizeButtonClicked() }
    }

    override fun setRandom(someNumber: Int) {
        vMessage.text = getString(R.string.example_message, someNumber)
    }
}
