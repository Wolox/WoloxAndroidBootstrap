package ar.com.wolox.android.example.ui.example

import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.ViewpagerActivity
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.android.example.utils.onTextChanged
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_example.*

class ExampleFragment : WolmoFragment<ExamplePresenter>(), IExampleView {

    override fun layout(): Int = R.layout.fragment_example

    override fun init() {
        vLoginButton.isEnabled = false
    }

    override fun setListeners() {
        vUsernameInput.onTextChanged { vLoginButton.isEnabled = it.isNotBlank() }
        vLoginButton.onClickListener {
            presenter.storeUsername(vUsernameInput.text.toString())
        }
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, ViewpagerActivity::class.java)
        startActivity(intent)
    }
}
