package ar.com.wolox.android.example.ui.viewpager.fragment

import android.support.v4.app.FragmentManager

import ar.com.wolox.android.example.ui.viewpager.ViewpagerActivity

import dagger.Module
import dagger.Provides

@Module
class ViewPagerFragmentModule {

    @Provides
    internal fun providesFragmentManager(viewpagerActivity: ViewpagerActivity): FragmentManager {
        return viewpagerActivity.supportFragmentManager
    }
}
