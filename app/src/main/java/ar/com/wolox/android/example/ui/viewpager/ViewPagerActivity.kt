package ar.com.wolox.android.example.ui.viewpager

import android.content.Context
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpTo
import javax.inject.Inject

class ViewPagerActivity @Inject constructor() : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, ViewPagerFragment.newInstance())
    }

    companion object {
        fun start(context: Context) = context.jumpTo(ViewPagerActivity::class.java)
    }
}
