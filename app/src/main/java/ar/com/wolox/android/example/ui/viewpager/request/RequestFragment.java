package ar.com.wolox.android.example.ui.viewpager.request;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import ar.com.wolox.wolmo.core.util.ToastFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class RequestFragment extends WolmoFragment<RequestPresenter> implements IRequestView {

    @BindView(R.id.fragment_page2_toolbar) Toolbar mToolbar;
    @BindView(R.id.fragment_page2_title) TextView mTitle;
    @BindView(R.id.fragment_page2_body) TextView mBody;

    @Inject ToastFactory mToastFactory;

    @Inject
    public RequestFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_request;
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
        mToastFactory.show(R.string.unknown_error);
    }
}
