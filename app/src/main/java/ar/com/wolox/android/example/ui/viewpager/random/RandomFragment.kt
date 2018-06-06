package ar.com.wolox.android.example.ui.viewpager.random

import ar.com.wolox.android.R
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_random.fragment_page1_message_text_view
import kotlinx.android.synthetic.main.fragment_random.fragment_page1_randomize_button
import kotlinx.android.synthetic.main.fragment_random.fragment_page1_title
import kotlinx.android.synthetic.main.fragment_random.fragment_page1_toolbar
import javax.inject.Inject

class RandomFragment @Inject constructor() : WolmoFragment<RandomPresenter>(), IRandomView {

    override fun layout(): Int = R.layout.fragment_random

    override fun init() {
        fragment_page1_toolbar.title = getString(R.string.page_1_toolbar_title)
    }

    override fun setListeners() {
        fragment_page1_randomize_button.onClickListener { presenter.generateRandomNumber() }
    }

    override fun setUsername(username: String) {
        fragment_page1_title.text = getString(R.string.page_1_title, username)
    }

    override fun onRandomNumberUpdate(someNumber: Int) {
        fragment_page1_message_text_view.text = getString(R.string.example_message, someNumber)
    }
}
