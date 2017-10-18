package ar.com.wolox.android.example.ui.viewpager.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ar.com.wolox.android.example.ui.viewpager.ViewpagerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = ViewPagerFragmentSubComponent.class)
public abstract class ViewPagerFragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(ViewPagerFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindViewPagerActivityFactory(
            ViewPagerFragmentSubComponent.Builder builder);

    @Provides
    static FragmentManager providesFragmentManager(ViewpagerActivity viewpagerActivity) {
        return viewpagerActivity.getSupportFragmentManager();
    }

}
