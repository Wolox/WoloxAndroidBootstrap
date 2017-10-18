package ar.com.wolox.android.example.ui.viewpager;

import android.app.Activity;

import ar.com.wolox.android.example.ui.viewpager.random.RandomFragment;
import ar.com.wolox.android.example.ui.viewpager.request.RequestFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = ViewPagerActivitySubcomponent.class)
public abstract class ViewPagerActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(ViewpagerActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindViewPagerActivityFactory(ViewPagerActivitySubcomponent.Builder builder);

    @ContributesAndroidInjector
    abstract RandomFragment randomFragment();

    @ContributesAndroidInjector
    abstract RequestFragment requestFragment();

}
