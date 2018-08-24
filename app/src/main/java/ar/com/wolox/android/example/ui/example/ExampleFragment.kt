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
        fragment_example_login.isEnabled = false
    }

    override fun setListeners() {
        fragment_example_username.onTextChanged { fragment_example_login.isEnabled = it.isNotBlank() }
        fragment_example_login.onClickListener {
            presenter.storeUsername(fragment_example_username.text.toString())
        }
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, ViewpagerActivity::class.java)
        startActivity(intent)
    }
}
