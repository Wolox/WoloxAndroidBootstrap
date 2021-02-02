package ar.com.wolox.android.example.ui.viewpager.random

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentRandomBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class RandomFragment @Inject constructor() : WolmoFragment<FragmentRandomBinding, RandomPresenter>(), RandomView {

    override fun layout() = R.layout.fragment_random

    override fun init() {
    }

    override fun setListeners() {
        binding.randomizeButton.setOnClickListener { presenter.onRandomizeButtonClicked() }
    }

    override fun setRandom(someNumber: Int) {
        binding.message.text = getString(R.string.example_message, someNumber)
    }
}
