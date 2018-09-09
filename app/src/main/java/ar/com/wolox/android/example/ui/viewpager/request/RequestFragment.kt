package ar.com.wolox.android.example.ui.viewpager.request

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_request.*
import javax.inject.Inject

class RequestFragment @Inject constructor() : WolmoFragment<RequestPresenter>(), IRequestView {

    @Inject internal lateinit var mToastFactory: ToastFactory

    override fun layout(): Int = R.layout.fragment_request

    override fun init() {
        vToolbar.title = getString(R.string.page_2_toolbar_title)
    }

    override fun setNewsTitle(title: String) {
        vPageTitle.text = title
    }

    override fun setNewsBody(body: String) {
        vPageBody.text = body
    }

    override fun showError() {
        mToastFactory.show(R.string.unknown_error)
    }
}
