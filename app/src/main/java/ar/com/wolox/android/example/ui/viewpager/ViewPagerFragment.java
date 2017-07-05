package ar.com.wolox.android.example.ui.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.viewpager.page1.Page1Fragment;
import ar.com.wolox.android.example.ui.viewpager.page2.Page2Fragment;
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.BindView;

public class ViewPagerFragment extends WolmoFragment<BasePresenter> {

    @Inject Page1Fragment mPage1Fragment;
    @Inject Page2Fragment mPage2Fragment;

    @BindView(R.id.fragment_viewpager_pager) ViewPager mViewPager;
    @BindView(R.id.fragment_viewpager_title_strip) PagerTitleStrip mPagerTitleStrip;

    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Inject
    public ViewPagerFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_viewpager;
    }

    @Override
    public void init() {
        mFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getFragmentManager(),
            new Pair<Fragment, String>(mPage1Fragment, "Page 1"),
            new Pair<Fragment, String>(mPage2Fragment, "Page 2"));

        mViewPager.setAdapter(mFragmentPagerAdapter);
    }
}
