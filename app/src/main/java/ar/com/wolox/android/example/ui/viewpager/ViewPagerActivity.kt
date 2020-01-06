package ar.com.wolox.android.example.ui.viewpager

import android.content.Context
import android.os.Bundle
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragment
import ar.com.wolox.android.example.utils.Extras.ViewPager.FAVOURITE_COLOR_KEY
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpTo
import javax.inject.Inject

class ViewPagerActivity @Inject constructor() : WolmoActivity() {

    override fun layout() = R.layout.activity_base

    override fun handleArguments(arguments: Bundle?) = arguments?.containsKey(FAVOURITE_COLOR_KEY)

    override fun init() {
        replaceFragment(R.id.vActivityBaseContent, ViewPagerFragment.newInstance(requireArgument(FAVOURITE_COLOR_KEY)))
    }

    companion object {

        fun start(context: Context, favouriteColor: String) = context.jumpTo(
            ViewPagerActivity::class.java,
            FAVOURITE_COLOR_KEY to favouriteColor)
    }
}
