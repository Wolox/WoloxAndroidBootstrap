package ar.com.wolox.android.example.ui.example

import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

import ar.com.wolox.android.example.utils.UserSession

import org.junit.Before
import org.junit.Test

class ExamplePresenterTest {

    private lateinit var mExampleView: IExampleView
    private lateinit var mExamplePresenter: ExamplePresenter
    private lateinit var mUserSession: UserSession

    @Before
    fun createInstances() {
        mExampleView = mock(IExampleView::class.java)
        mUserSession = mock(UserSession::class.java)
        mExamplePresenter = ExamplePresenter(mUserSession)
    }

    @Test
    fun usernameIsStored() {
        mExamplePresenter.attachView(mExampleView)
        mExamplePresenter.storeUsername("Test")
        verify<UserSession>(mUserSession, times(1)).username = "Test"
    }

    @Test
    fun storeUsernameUpdatesView() {
        mExamplePresenter.attachView(mExampleView)
        mExamplePresenter.storeUsername("Test")
        verify<IExampleView>(mExampleView, times(1)).onUsernameSaved()
    }

}
