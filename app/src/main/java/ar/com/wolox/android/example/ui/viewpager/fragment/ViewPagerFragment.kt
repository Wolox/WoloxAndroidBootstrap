package ar.com.wolox.android.example.ui.viewpager.fragment

import androidx.fragment.app.Fragment
import androidx.core.util.Pair
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.random.RandomFragment
import ar.com.wolox.android.example.ui.viewpager.request.RequestFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_viewpager.*
import javax.inject.Inject

class ViewPagerFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    @Inject internal lateinit var page1Fragment: RandomFragment
    @Inject internal lateinit var page2Fragment: RequestFragment
    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int = R.layout.fragment_viewpager

    override fun init() {
        fragmentPagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentPagerAdapter.addFragments(
                Pair<androidx.fragment.app.Fragment, String>(page1Fragment, "Page 1"),
                Pair<androidx.fragment.app.Fragment, String>(page2Fragment, "Page 2"))
        vViewPager.adapter = fragmentPagerAdapter
    }
}
