package ar.com.wolox.android.example.ui.viewpager

import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragment

import ar.com.wolox.android.example.ui.viewpager.random.RandomFragment
import ar.com.wolox.android.example.ui.viewpager.request.RequestFragment

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [(ViewPagerActivitySubcomponent::class)])
abstract class ViewPagerActivityModule {

    @Binds
    @IntoMap
    @ClassKey(ViewPagerActivity::class)
    internal abstract fun bindViewPagerActivityFactory(
        builder: ViewPagerActivitySubcomponent.Builder
    ): AndroidInjector.Factory<*>

    @ContributesAndroidInjector
    internal abstract fun viewpagerFragment(): ViewPagerFragment

    @ContributesAndroidInjector
    internal abstract fun randomFragment(): RandomFragment

    @ContributesAndroidInjector
    internal abstract fun requestFragment(): RequestFragment
}
