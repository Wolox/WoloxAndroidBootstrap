package ar.com.wolox.android.example.ui.viewpager.fragment;

import android.support.v4.util.Pair;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.viewpager.random.RandomFragment;
import ar.com.wolox.android.example.ui.viewpager.request.RequestFragment;
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class ViewPagerFragment extends WolmoFragment {

    @BindView(R.id.fragment_viewpager_pager) ViewPager mViewPager;
    @BindView(R.id.fragment_viewpager_title_strip) PagerTitleStrip mPagerTitleStrip;

    @Inject RandomFragment mPage1Fragment;
    @Inject RequestFragment mPage2Fragment;
    @Inject SimpleFragmentPagerAdapter mFragmentPagerAdapter;

    @Inject
    public ViewPagerFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_viewpager;
    }

    @Override
    public void init() {
        mFragmentPagerAdapter.addFragments(new Pair<>(mPage1Fragment, "Page 1"),
                new Pair<>(mPage2Fragment, "Page 2"));

        mViewPager.setAdapter(mFragmentPagerAdapter);
    }
}
