package ar.com.wolox.android.example.ui.viewpager

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface ViewPagerActivitySubcomponent : AndroidInjector<ViewpagerActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ViewpagerActivity>()
}
