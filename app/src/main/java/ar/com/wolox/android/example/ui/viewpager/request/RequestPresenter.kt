package ar.com.wolox.android.example.ui.viewpager.request

import ar.com.wolox.android.example.model.Post
import ar.com.wolox.android.example.network.PostService
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices

import javax.inject.Inject

class RequestPresenter @Inject constructor(
    private val retrofitServices: RetrofitServices
) : BasePresenter<RequestView>() {

    override fun onViewAttached() {
        retrofitServices.getService(PostService::class.java).getPostById(POST_ID).enqueue(
            networkCallback {
                onResponseSuccessful { response ->
                    response?.let {
                        showPost(it)
                    } ?: run {
                        showError()
                    }
                }

                onResponseFailed { _, _ -> showError() }

                onCallFailure { showError() }
            }
        )
    }

    private fun showPost(post: Post) = view?.run {
        setNewsTitle(post.title)
        setNewsBody(post.body)
    }

    private fun showError() = view?.showError()

    companion object {
        private const val POST_ID = 1
    }
}
