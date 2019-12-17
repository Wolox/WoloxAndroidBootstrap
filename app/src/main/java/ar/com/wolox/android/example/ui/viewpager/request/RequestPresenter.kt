package ar.com.wolox.android.example.ui.viewpager.request

import ar.com.wolox.android.example.network.PostService
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices

import javax.inject.Inject

class RequestPresenter @Inject constructor(
    private val retrofitServices: RetrofitServices
) : BasePresenter<IRequestView>() {

    override fun onViewAttached() {
        retrofitServices.getService(PostService::class.java).getPostById(POST_ID).enqueue(
            networkCallback {
                onResponseSuccessful { response ->
                    view?.run {
                        setNewsTitle(response?.title ?: "")
                        setNewsBody(response?.body ?: "")
                    }
                }

                onResponseFailed { _, _ -> view?.showError() }

                onCallFailure { view?.showError() }
            }
        )
    }

    companion object {
        private const val POST_ID = 1
    }
}
