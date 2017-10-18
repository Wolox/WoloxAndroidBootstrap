package ar.com.wolox.android.example.ui.viewpager.fragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface ViewPagerFragmentSubComponent extends AndroidInjector<ViewPagerFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ViewPagerFragment> {
    }
}
