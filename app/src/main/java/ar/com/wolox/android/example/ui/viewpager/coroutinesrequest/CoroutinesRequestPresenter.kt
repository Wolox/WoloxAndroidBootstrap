package ar.com.wolox.android.example.ui.viewpager.coroutinesrequest

import ar.com.wolox.android.example.model.Post
import ar.com.wolox.android.example.network.PostCoroutineService
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesRequestPresenter @Inject constructor(
    private val retrofitServices: RetrofitServices
) : CoroutineBasePresenter<CoroutinesRequestView>() {

    override fun onViewAttached() {
        launch {
            try {
                showPost(retrofitServices.getService(PostCoroutineService::class.java).getPostById(POST_ID))
            } catch (e: Exception) {
                showError()
            }
        }
    }

    private fun showPost(post: Post) = view?.run {
        setNewsTitle(post.title)
        setNewsBody(post.body)
    }

    private fun showError() = view?.showError()

    companion object {
        private const val POST_ID = 2
    }
}
