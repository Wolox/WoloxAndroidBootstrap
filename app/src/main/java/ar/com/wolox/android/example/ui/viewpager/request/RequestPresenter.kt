package ar.com.wolox.android.example.ui.viewpager.request

import ar.com.wolox.android.example.model.Post
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.PostRepository
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class RequestPresenter @Inject constructor(
    private val postRepository: PostRepository
) : CoroutineBasePresenter<RequestView>() {

    fun onSearchRequested(postId: Int?) = launch {
        if (postId == null) {
            view?.showInvalidInput()
            return@launch
        }

        networkRequest(postRepository.getPostById(postId)) {
            onResponseSuccessful { response -> showPost(response!!) }
            onResponseFailed { _, _ -> view?.showError() }
            onCallFailure { view?.showError() }
        }
    }

    private fun showPost(post: Post) = view?.run {
        setTitle(post.title)
        setBody(post.body)
    }
}
