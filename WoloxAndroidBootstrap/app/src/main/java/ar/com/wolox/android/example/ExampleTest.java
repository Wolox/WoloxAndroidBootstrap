package ar.com.wolox.android.example;

import android.content.Context;

public class ExampleTest {

    @Mock ExampleView mExampleView;
    @Mock Context mContext;

    @Test
    public void randomNumberIsValid() {
        ExamplePresenter examplePresenter = new ExamplePresenter(mExampleView, mContext);

        int randomNumberGenerated = examplePresenter.generateRandomNumber();

        assertThat(randomNumberGenerated, greaterThanOrEqualTo(ExamplePresenter.NUMBER_MIN));
        assertThat(randomNumberGenerated, lessThanOrEqualTo(ExamplePresenter.NUMBER_MAX));
    }

}
