package ar.com.wolox.android.example.ui.viewpager

import android.app.Activity
import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragment

import ar.com.wolox.android.example.ui.viewpager.random.RandomFragment
import ar.com.wolox.android.example.ui.viewpager.request.RequestFragment

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = [(ViewPagerActivitySubcomponent::class)])
abstract class ViewPagerActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(ViewpagerActivity::class)
    internal abstract fun bindViewPagerActivityFactory(
            builder: ViewPagerActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>

    @ContributesAndroidInjector
    internal abstract fun viewpagerFragment(): ViewPagerFragment

    @ContributesAndroidInjector
    internal abstract fun randomFragment(): RandomFragment

    @ContributesAndroidInjector
    internal abstract fun requestFragment(): RequestFragment
}
