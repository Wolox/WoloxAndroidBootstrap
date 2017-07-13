package ar.com.wolox.android.example;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import ar.com.wolox.android.example.ui.ExamplePresenter;
import ar.com.wolox.android.example.ui.ExampleView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.mockito.Mockito.*;

public class ExamplePresenterTest {

    ExampleView mExampleView;
    Context mContext;
    ExamplePresenter mExamplePresenter;

    @Before
    public void createInstances() {
        mExampleView = mock(ExampleView.class);
        mContext = mock(Context.class);
        mExamplePresenter = new ExamplePresenter(mExampleView);
    }

    @Test
    public void randomNumberIsValid() {

        int randomNumberGenerated = mExamplePresenter.generateRandomNumber();

        assertThat(randomNumberGenerated, greaterThanOrEqualTo(ExamplePresenter.NUMBER_MIN));
        assertThat(randomNumberGenerated, lessThanOrEqualTo(ExamplePresenter.NUMBER_MAX));
    }

    @Test
    public void randomNumberUpdatesView() {
        int randomNumberGenerated = mExamplePresenter.generateRandomNumber();
        verify(mExampleView, times(1)).onRandomNumberUpdate(randomNumberGenerated);
    }

}
