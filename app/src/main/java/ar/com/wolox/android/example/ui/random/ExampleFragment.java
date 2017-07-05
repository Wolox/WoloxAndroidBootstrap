package ar.com.wolox.android.example.ui.random;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.viewpager.ViewpagerActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class ExampleFragment extends WolmoFragment<ExamplePresenter> implements ExampleView {

    // Views (using Butterknife)
    @BindView(R.id.fragment_example_message_text_view) TextView mMessageTextView;
    @BindView(R.id.fragment_example_username) TextView mUsername;
    @BindView(R.id.fragment_example_go_to_viewpager_button) Button mGoToViewpager;

    // Resources
    @BindString(R.string.example_message) String mMessage;

    @Inject
    public ExampleFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_example;
    }

    @Override
    public void init() {
        mGoToViewpager.setEnabled(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().generateRandomNumber();
    }

    @OnClick(R.id.fragment_example_randomize_button) // Using Butterknife to set up an OnClickListener
    protected void onRandomizeButtonClick() {
        getPresenter().generateRandomNumber(); // The click behaviour is handled inside the presenter
    }

    @OnTextChanged(R.id.fragment_example_username)
    protected void onUsernameChanged(CharSequence value) {
        mGoToViewpager.setEnabled(!TextUtils.isEmpty(value));
    }

    @OnClick(R.id.fragment_example_go_to_viewpager_button)
    public void onGoToViewpage() {
        Intent intent = new Intent(getActivity(), ViewpagerActivity.class);
        intent.putExtra("username", mUsername.getText());
        startActivity(intent);
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
