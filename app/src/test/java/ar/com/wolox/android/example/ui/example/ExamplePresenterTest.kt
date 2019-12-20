package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.example.utils.UserSession
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class ExamplePresenterTest {

    private lateinit var exampleView: ExampleView
    private lateinit var examplePresenter: ExamplePresenter
    private lateinit var userSession: UserSession

    @Before
    fun createInstances() {
        exampleView = mock(ExampleView::class.java)
        userSession = mock(UserSession::class.java)
        examplePresenter = ExamplePresenter(userSession).apply {
            attachView(exampleView)
        }
    }

    @Test
    fun `when login button is clicked then user session should be updated`() {
        examplePresenter.onLoginButtonClicked("Test", "H")
        verify<UserSession>(userSession, times(1)).username = "Test"
    }

    @Test
    fun `when login button is clicked then view should go to viewpager`() {
        examplePresenter.onLoginButtonClicked("Test", "azul")
        verify<ExampleView>(exampleView, times(1)).goToViewPager("azul")
    }
}
