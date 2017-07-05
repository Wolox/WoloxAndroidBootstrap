package ar.com.wolox.android.example.ui.viewpager.page2;

import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class Page2Fragment extends WolmoFragment<Page2Presenter> implements IPage2View {

    @BindView(R.id.fragment_page2_title) TextView mTitle;
    @BindView(R.id.fragment_page2_body) TextView mBody;

    @Inject
    public Page2Fragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_page2;
    }

    @Override
    public void init() {
    }

    @Override
    public void setNewsTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setNewsBody(String body) {
        mBody.setText(body);
    }
}
