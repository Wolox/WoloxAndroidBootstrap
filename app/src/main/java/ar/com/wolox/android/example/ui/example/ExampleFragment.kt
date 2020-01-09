package ar.com.wolox.android.example.ui.example

import androidx.core.widget.addTextChangedListener
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.openBrowser
import ar.com.wolox.wolmo.core.util.openDial
import kotlinx.android.synthetic.main.fragment_example.*

class ExampleFragment private constructor() : WolmoFragment<ExamplePresenter>(), ExampleView {

    override fun layout() = R.layout.fragment_example

    override fun init() {
    }

    override fun setListeners() {
        vUsernameInput.addTextChangedListener { presenter.onUsernameInputChanged(it.toString()) }
        vWoloxLink.setOnClickListener { presenter.onWoloxLinkClicked() }
        vWoloxPhone.setOnClickListener { presenter.onWoloxPhoneClicked() }
        vLoginButton.setOnClickListener {
            presenter.onLoginButtonClicked(vUsernameInput.text.toString(), vFavouriteColorInput.text.toString())
        }
    }

    override fun toggleLoginButtonEnable(isEnable: Boolean) {
        vLoginButton.isEnabled = isEnable
    }

    override fun goToViewPager(favouriteColor: String) = ViewPagerActivity.start(requireContext(), favouriteColor)

    override fun openBrowser(url: String) = requireContext().openBrowser(url)

    override fun openPhone(woloxPhone: String) = requireContext().openDial(woloxPhone)

    companion object {
        fun newInstance() = ExampleFragment()
    }
}
