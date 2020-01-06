package ar.com.wolox.android.example.ui.viewpager.request

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_request.*
import javax.inject.Inject

class RequestFragment @Inject constructor() : WolmoFragment<RequestPresenter>(), RequestView {

    @Inject internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_request

    override fun init() {
    }

    override fun setListeners() {
        vSearchButton.setOnClickListener { presenter.onSearchRequested(vIdInput.text.toString().toIntOrNull()) }
    }

    override fun setTitle(title: String) {
        vPageTitle.text = title
    }

    override fun setBody(body: String) {
        vPageBody.text = body
    }

    override fun showInvalidInput() = toastFactory.show(R.string.fragment_request_invalid_input)

    override fun showError() = toastFactory.show(R.string.unknown_error)
}
