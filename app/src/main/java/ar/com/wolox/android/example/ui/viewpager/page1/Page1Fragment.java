package ar.com.wolox.android.example.ui.viewpager.page1;

import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class Page1Fragment extends WolmoFragment<Page1Presenter> implements IPage1View {

    @BindView(R.id.fragment_page1_title) TextView mUsername;

    @Inject
    public Page1Fragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_page1;
    }

    @Override
    public void init() {}

    @Override
    public void setUsername(String username) {
        mUsername.setText(username);
    }
}
