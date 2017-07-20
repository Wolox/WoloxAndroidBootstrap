package ar.com.wolox.android.example.ui.viewpager.random;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.com.wolox.android.example.ui.example.ExamplePresenter;
import ar.com.wolox.android.example.utils.Extras;
import ar.com.wolox.android.example.utils.UserUtils;
import ar.com.wolox.wolmo.core.util.StorageUtils;

import org.junit.Before;
import org.junit.Test;

public class RandomPresenterTest {

    private IRandomView mPage1View;
    private RandomPresenter mRandomPresenter;
    private UserUtils mUserUtils;

    @Before
    public void createInstances() {
        mPage1View = mock(IRandomView.class);
        mUserUtils = mock(UserUtils.class);
        mRandomPresenter = new RandomPresenter(mUserUtils);
    }

    @Test
    public void updatesViewOnAttach() {
        when(mUserUtils.getUsername()).thenReturn("TestUser");
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
