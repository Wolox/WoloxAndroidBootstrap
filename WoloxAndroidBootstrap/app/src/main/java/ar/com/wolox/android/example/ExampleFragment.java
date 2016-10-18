package ar.com.wolox.android.example;

import android.os.Bundle;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.fragment.WoloxFragment;
import butterknife.Bind;
import butterknife.OnClick;

public class ExampleFragment extends WoloxFragment<ExamplePresenter> implements ExampleView {

    // Views (using Butterknife)
    @Bind(R.id.fragment_example_message_text_view) protected TextView mMessageTextView;

    // Fragments default constructors shouldn't be overridden, always prefer this method instead
    public static ExampleFragment newInstance() {

        Bundle args = new Bundle();

        ExampleFragment fragment = new ExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_example;
    }

    @Override
    protected ExamplePresenter createPresenter() {
        return new ExamplePresenter(this, getActivity());
    }

    @Override
    protected void init() {
        getPresenter().generateRandomNumber();
    }

    @OnClick(R.id.fragment_example_randomize_button) // Using Butterknife to set up an OnClickListener
    protected void onRandomizeButtonClick() {
        getPresenter().generateRandomNumber(); // The click behaviour is handled inside the presenter
    }

    @Override
    public void onRandomNumberUpdate(int someNumber) {
        // Do some frontend logic here
        StringBuilder sb = new StringBuilder();
        sb.append("The presenter generated the number");
        sb.append(" ");
        sb.append(someNumber);
        sb.append(" ");
        sb.append("and this view is displaying it");

        mMessageTextView.setText(sb.toString());

        // Note: The View doesn't care about how the data (in this case a number) was retrieved,
        // it let's the presenter deal with that and only cares about the UI
    }
}
