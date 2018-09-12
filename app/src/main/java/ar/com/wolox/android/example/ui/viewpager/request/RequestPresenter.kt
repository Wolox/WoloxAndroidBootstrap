package ar.com.wolox.android.example.ui.viewpager.request

import ar.com.wolox.android.example.network.PostService
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices

import javax.inject.Inject

class RequestPresenter @Inject constructor(
        private val mRetrofitServices: RetrofitServices
) : BasePresenter<IRequestView>() {

    override fun onViewAttached() {
        mRetrofitServices.getService(PostService::class.java).getPostById(POST_ID).enqueue(
                networkCallback {
                    onResponseSuccessful { response ->
                        runIfViewAttached { view ->
                            view.setNewsTitle(response?.title ?: "")
                            view.setNewsBody(response?.body ?: "")
                        }
                    }

                    onResponseFailed { _, _ -> runIfViewAttached(Runnable { view.showError() }) }

                    onCallFailure { runIfViewAttached(Runnable { view.showError() }) }
                }
        )
    }

    companion object {
        private const val POST_ID = 1
    }
}
