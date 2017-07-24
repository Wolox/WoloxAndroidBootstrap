package ar.com.wolox.android.example.ui.example;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.com.wolox.android.example.utils.UserUtils;

import org.junit.Before;
import org.junit.Test;

public class ExamplePresenterTest {

    private IExampleView mExampleView;
    private ExamplePresenter mExamplePresenter;
    private UserUtils mUserUtils;

    @Before
    public void createInstances() {
        mExampleView = mock(IExampleView.class);
        mUserUtils = mock(UserUtils.class);
        mExamplePresenter = new ExamplePresenter(mUserUtils);
    }

    @Test
    public void usernameIsStored() {
        mExamplePresenter.attachView(mExampleView);
        mExamplePresenter.storeUsername("Test");
        verify(mUserUtils, times(1)).setUsername("Test");
    }

    @Test
    public void storeUsernameUpdatesView() {
        mExamplePresenter.attachView(mExampleView);
        mExamplePresenter.storeUsername("Test");
        verify(mExampleView, times(1)).onUsernameSaved();
    }

}
