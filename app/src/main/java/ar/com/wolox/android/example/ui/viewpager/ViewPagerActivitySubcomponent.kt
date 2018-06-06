package ar.com.wolox.android.example.ui.viewpager

import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragmentModule

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [(ViewPagerFragmentModule::class)])
interface ViewPagerActivitySubcomponent : AndroidInjector<ViewpagerActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ViewpagerActivity>()
}
