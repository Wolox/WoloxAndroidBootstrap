package ar.com.wolox.android.example.ui.viewpager.request

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentRequestBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class RequestFragment @Inject constructor() : WolmoFragment<FragmentRequestBinding, RequestPresenter>(), RequestView {

    @Inject internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_request

    override fun init() {
    }

    override fun setListeners() {
        binding.searchButton.setOnClickListener { presenter.onSearchRequested(binding.idInput.text.toString().toIntOrNull()) }
    }

    override fun setTitle(title: String) {
        binding.pageTitle.text = title
    }

    override fun setBody(body: String) {
        binding.pageBody.text = body
    }

    override fun showInvalidInput() = toastFactory.show(R.string.fragment_request_invalid_input)

    override fun showError() = toastFactory.show(R.string.unknown_error)
}
