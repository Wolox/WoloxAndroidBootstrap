package ar.com.wolox.android.example.ui.example;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.com.wolox.android.example.utils.UserSession;

import org.junit.Before;
import org.junit.Test;

public class ExamplePresenterTest {

    private IExampleView mExampleView;
    private ExamplePresenter mExamplePresenter;
    private UserSession mUserSession;

    @Before
    public void createInstances() {
        mExampleView = mock(IExampleView.class);
        mUserSession = mock(UserSession.class);
        mExamplePresenter = new ExamplePresenter(mUserSession);
    }

    @Test
    public void usernameIsStored() {
        mExamplePresenter.attachView(mExampleView);
        mExamplePresenter.storeUsername("Test");
        verify(mUserSession, times(1)).setUsername("Test");
    }

    @Test
    public void storeUsernameUpdatesView() {
        mExamplePresenter.attachView(mExampleView);
        mExamplePresenter.storeUsername("Test");
        verify(mExampleView, times(1)).onUsernameSaved();
    }

}
