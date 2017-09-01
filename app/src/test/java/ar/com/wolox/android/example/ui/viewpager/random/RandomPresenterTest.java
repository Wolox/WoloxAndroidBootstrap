package ar.com.wolox.android.example.ui.viewpager.random;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.util.Log;

import ar.com.wolox.android.example.ui.example.ExamplePresenter;
import ar.com.wolox.android.example.utils.UserSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class RandomPresenterTest {

    private IRandomView mPage1View;
    private RandomPresenter mRandomPresenter;
    private UserSession mUserSession;

    @Before
    public void createInstances() {
        // Mock calls to android.util.Log
        PowerMockito.mockStatic(Log.class);

        mPage1View = mock(IRandomView.class);
        mUserSession = mock(UserSession.class);
        mRandomPresenter = new RandomPresenter(mUserSession);
    }

    @Test
    public void updatesViewOnAttach() {
        when(mUserSession.getUsername()).thenReturn("TestUser");
        mRandomPresenter.attachView(mPage1View);
        verify(mPage1View, times(1)).setUsername("TestUser");
    }

    @Test
    public void randomNumberIsValid() {
        mRandomPresenter.attachView(mPage1View);
        int randomNumberGenerated = mRandomPresenter.generateRandomNumber();

        assertThat(randomNumberGenerated).isGreaterThanOrEqualTo(ExamplePresenter.NUMBER_MIN);
        assertThat(randomNumberGenerated).isLessThanOrEqualTo(ExamplePresenter.NUMBER_MAX);
    }

    @Test
    public void randomNumberUpdatesView() {
        mRandomPresenter.attachView(mPage1View);
        int randomNumberGenerated = mRandomPresenter.generateRandomNumber();
        verify(mPage1View, times(1)).onRandomNumberUpdate(randomNumberGenerated);
    }

}
