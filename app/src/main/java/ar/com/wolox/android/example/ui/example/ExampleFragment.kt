package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.android.example.utils.onTextChanged
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_example.*

class ExampleFragment private constructor() : WolmoFragment<ExampleView, ExamplePresenter>(), ExampleView {

    override fun layout() = R.layout.fragment_example

    override fun init() {
    }

    override fun setListeners() {
        vUsernameInput.onTextChanged { presenter .onUsernameInputChanged(it.toString()) }
        vLoginButton.onClickListener { presenter.onLoginButtonClicked(vUsernameInput.text.toString()) }
    }

    override fun toggleLoginButtonEnable(isEnable: Boolean) {
        vLoginButton.isEnabled = isEnable
    }

    override fun goToViewPager() = ViewPagerActivity.start(requireContext())

    companion object {
        fun newInstance() = ExampleFragment()
    }
}
