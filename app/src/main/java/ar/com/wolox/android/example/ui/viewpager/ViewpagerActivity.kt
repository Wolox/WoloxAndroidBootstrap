package ar.com.wolox.android.example.ui.viewpager

import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import dagger.Lazy
import javax.inject.Inject

class ViewpagerActivity @Inject constructor() : WolmoActivity() {

    // Lazy example, a lazy injection does not build the dependencies until #get() is called
    @Inject internal lateinit var lazyViewpagerFragment: Lazy<ViewPagerFragment>

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, lazyViewpagerFragment.get())
    }
}
