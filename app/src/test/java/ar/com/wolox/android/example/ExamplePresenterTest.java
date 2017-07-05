package ar.com.wolox.android.example;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ar.com.wolox.android.example.ui.random.ExamplePresenter;
import ar.com.wolox.android.example.ui.random.ExampleView;

import org.junit.Before;
import org.junit.Test;

public class ExamplePresenterTest {

    ExampleView mExampleView;
    ExamplePresenter mExamplePresenter;

    @Before
    public void createInstances() {
        mExampleView = mock(ExampleView.class);
        mExamplePresenter = new ExamplePresenter();
    }

    @Test
    public void randomNumberIsValid() {

        int randomNumberGenerated = mExamplePresenter.generateRandomNumber();

        assertThat(randomNumberGenerated).isGreaterThanOrEqualTo(ExamplePresenter.NUMBER_MIN);
        assertThat(randomNumberGenerated).isLessThanOrEqualTo(ExamplePresenter.NUMBER_MAX);
    }

    @Test
    public void randomNumberUpdatesView() {
        int randomNumberGenerated = mExamplePresenter.generateRandomNumber();
        verify(mExampleView, times(1)).onRandomNumberUpdate(randomNumberGenerated);
    }

}
