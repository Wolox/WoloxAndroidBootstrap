package ar.com.wolox.android.example.ui;

import android.os.Bundle;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class ExampleFragment extends WolmoFragment<ExamplePresenter> implements ExampleView {

    // Views (using Butterknife)
    @BindView(R.id.fragment_example_message_text_view) protected TextView mMessageTextView;

    // Resources
    @BindString(R.string.example_message) String mMessage;

    // Fragments default constructors shouldn't be overridden, always prefer this method instead
    public static ExampleFragment newInstance() {

        Bundle args = new Bundle();

        ExampleFragment fragment = new ExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int layout() {
        return R.layout.fragment_example;
    }

    @Override
    public ExamplePresenter createPresenter() {
        return new ExamplePresenter(this);
    }

    @Override
    public void init() {
        getPresenter().generateRandomNumber();
    }

    @OnClick(R.id.fragment_example_randomize_button) // Using Butterknife to set up an OnClickListener
    protected void onRandomizeButtonClick() {
        getPresenter().generateRandomNumber(); // The click behaviour is handled inside the presenter
    }

    @Override
    public void onRandomNumberUpdate(int someNumber) {
        // Do some frontend logic here
        String msg = String.format(mMessage, someNumber);
        mMessageTextView.setText(msg);

        // Note: The View doesn't care about how the data (in this case a number) was retrieved,
        // it let's the presenter deal with that and only cares about the UI
    }
}
