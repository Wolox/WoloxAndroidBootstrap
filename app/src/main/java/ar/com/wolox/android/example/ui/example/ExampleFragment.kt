package ar.com.wolox.android.example.ui.example

import androidx.core.widget.addTextChangedListener
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentExampleBinding
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.openBrowser
import ar.com.wolox.wolmo.core.util.openDial

class ExampleFragment private constructor() : WolmoFragment<FragmentExampleBinding, ExamplePresenter>(), ExampleView {

    override fun layout() = R.layout.fragment_example

    override fun init() {
    }

    override fun setListeners() {
        with(binding) {
            usernameInput.addTextChangedListener { presenter.onUsernameInputChanged(it.toString()) }
            woloxLink.setOnClickListener { presenter.onWoloxLinkClicked() }
            woloxPhone.setOnClickListener { presenter.onWoloxPhoneClicked() }
            loginButton.setOnClickListener {
                presenter.onLoginButtonClicked(usernameInput.text.toString(), favouriteColorInput.text.toString())
            }
        }
    }

    override fun toggleLoginButtonEnable(isEnable: Boolean) {
        binding.loginButton.isEnabled = isEnable
    }

    override fun goToViewPager(favouriteColor: String) = ViewPagerActivity.start(requireContext(), favouriteColor)

    override fun openBrowser(url: String) = requireContext().openBrowser(url)

    override fun openPhone(woloxPhone: String) = requireContext().openDial(woloxPhone)

    companion object {
        fun newInstance() = ExampleFragment()
    }
}
