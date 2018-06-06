package ar.com.wolox.android.example.ui.viewpager

import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity

import javax.inject.Inject

import dagger.Lazy

class ViewpagerActivity @Inject constructor() : WolmoActivity() {

    // Lazy example, a lazy injection does not build the dependencies until #get() is called
    @Inject internal lateinit var mLazyViewpagerFragment: Lazy<ViewPagerFragment>

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.activity_base_content, mLazyViewpagerFragment.get())
    }
}
