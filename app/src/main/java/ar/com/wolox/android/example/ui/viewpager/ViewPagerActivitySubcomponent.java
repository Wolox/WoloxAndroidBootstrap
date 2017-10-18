package ar.com.wolox.android.example.ui.viewpager;

import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragmentModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {ViewPagerFragmentModule.class})
public interface ViewPagerActivitySubcomponent extends AndroidInjector<ViewpagerActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ViewpagerActivity> {
    }
}
