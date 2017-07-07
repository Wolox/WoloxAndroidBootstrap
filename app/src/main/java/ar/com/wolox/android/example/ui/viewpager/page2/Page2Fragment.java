package ar.com.wolox.android.example.ui.viewpager.page2;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import ar.com.wolox.wolmo.core.util.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class Page2Fragment extends WolmoFragment<Page2Presenter> implements IPage2View {

    @BindView(R.id.fragment_page2_toolbar) Toolbar mToolbar;
    @BindView(R.id.fragment_page2_title) TextView mTitle;
    @BindView(R.id.fragment_page2_body) TextView mBody;

    @Inject ToastUtils mToastUtils;

    @Inject
    public Page2Fragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_page2;
    }

    @Override
    public void init() {
        mToolbar.setTitle(getString(R.string.page_2_toolbar_title));
    }

    @Override
    public void setNewsTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setNewsBody(String body) {
        mBody.setText(body);
    }

    @Override
    public void showError() {
        mToastUtils.show(R.string.unknown_error);
    }
}
