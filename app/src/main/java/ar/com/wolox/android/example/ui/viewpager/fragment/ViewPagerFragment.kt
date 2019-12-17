package ar.com.wolox.android.example.ui.viewpager.fragment

import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.random.RandomFragment
import ar.com.wolox.android.example.ui.viewpager.request.RequestFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_viewpager.*
import javax.inject.Inject

class ViewPagerFragment private constructor() : WolmoFragment<Any, BasePresenter<Any>>() {

    // Lazy example, a lazy injection does not build the dependencies until #get() is called
    @Inject
    internal lateinit var page1Fragment: Lazy<RandomFragment>

    @Inject
    internal lateinit var page2Fragment: RequestFragment

    override fun layout(): Int = R.layout.fragment_viewpager

    override fun init() {
        vViewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                page1Fragment.get() to "Page 1",
                page2Fragment to "Page 2")
        }
    }

    companion object {
        fun newInstance() = ViewPagerFragment()
    }
}
