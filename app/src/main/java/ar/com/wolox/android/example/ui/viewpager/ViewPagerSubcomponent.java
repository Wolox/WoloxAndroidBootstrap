package ar.com.wolox.android.example.ui.viewpager;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface ViewPagerSubcomponent extends AndroidInjector<ViewpagerActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ViewpagerActivity> {}
}
