package ar.com.wolox.android.example.ui.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.viewpager.ViewpagerActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class ExampleFragment extends WolmoFragment<ExamplePresenter> implements IExampleView {

    // Views (using Butterknife)
    @BindView(R.id.fragment_example_username) TextView mUsername;
    @BindView(R.id.fragment_example_login) Button mGoToViewpager;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnTextChanged(R.id.fragment_example_username)
    protected void onUsernameChanged(CharSequence value) {
        mGoToViewpager.setEnabled(!TextUtils.isEmpty(value));
    }

    @OnClick(R.id.fragment_example_login)
    public void onGoToViewpage() {
        getPresenter().storeUsername(mUsername.getText().toString());
    }

    public void onUsernameSaved() {
        Intent intent = new Intent(getActivity(), ViewpagerActivity.class);
        startActivity(intent);
    }
}
