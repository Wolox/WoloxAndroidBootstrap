package ar.com.wolox.android.example.ui.viewpager.request

import ar.com.wolox.android.example.model.Post
import ar.com.wolox.android.example.network.repository.PostRepository
import ar.com.wolox.wolmo.core.tests.CoroutineTestRule
import ar.com.wolox.wolmo.core.tests.WolmoPresenterTest
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class RequestPresenterTest : WolmoPresenterTest<RequestView, RequestPresenter>() {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(runOnAllTests = true)

    @Mock
    lateinit var postRepository: PostRepository

    override fun getPresenterInstance() = RequestPresenter(postRepository)

    @Test
    fun `given a null id when request search then show invalid input`() = runBlocking {

        // GIVEN
        val id: Int? = null

        // WHEN
        presenter.onSearchRequested(id).join()

        // THEN
        verify(view, times(1)).showInvalidInput()
    }

    @Test
    fun `given a wrong id when request search then show error`() = runBlocking {

        // GIVEN
        val id = 0
        whenever(postRepository.getPostById(id)).doThrow(mock(HttpException::class.java))

        // WHEN
        presenter.onSearchRequested(id).join()

        // THEN
        verify(view, times(1)).showError()
    }

    @Test
    fun `given a correct id when request search then show post`() = runBlocking {

        // GIVEN
        val id = 1
        val title = "Title"
        val body = "body body body body body body body body"
        val post = Post(title, body)
        whenever(postRepository.getPostById(id)).doReturn(post)

        // WHEN
        presenter.onSearchRequested(id).join()

        // THEN
        verify(view, times(1)).setTitle(title)
        verify(view, times(1)).setBody(body)
    }
}