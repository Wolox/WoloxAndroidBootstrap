package ar.com.wolox.android.example.ui.viewpager.fragment

import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.random.RandomFragment
import ar.com.wolox.android.example.ui.viewpager.request.RequestFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_viewpager.fragment_viewpager_pager
import javax.inject.Inject

class ViewPagerFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    @Inject internal lateinit var mPage1Fragment: RandomFragment
    @Inject internal lateinit var mPage2Fragment: RequestFragment
    @Inject internal lateinit var mFragmentPagerAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int = R.layout.fragment_viewpager

    override fun init() {
        mFragmentPagerAdapter.addFragments(
                Pair<Fragment, String>(mPage1Fragment, "Page 1"),
                Pair<Fragment, String>(mPage2Fragment, "Page 2"))
        fragment_viewpager_pager.adapter = mFragmentPagerAdapter
    }
}
