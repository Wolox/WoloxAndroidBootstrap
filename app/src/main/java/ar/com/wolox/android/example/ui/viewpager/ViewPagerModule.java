package ar.com.wolox.android.example.ui.viewpager;

import android.app.Activity;

import ar.com.wolox.android.example.ui.viewpager.random.RandomFragment;
import ar.com.wolox.android.example.ui.viewpager.request.RequestFragment;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = ViewPagerSubcomponent.class)
public abstract class ViewPagerModule {

    @Binds
    @IntoMap
    @ActivityKey(ViewpagerActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> binfViewPagerActivityFactory(ViewPagerSubcomponent.Builder builder);

    @ContributesAndroidInjector
    abstract ViewPagerFragment viewpagerFragment();

    @ContributesAndroidInjector
    abstract RandomFragment randomFragment();

    @ContributesAndroidInjector
    abstract RequestFragment requestFragment();

    @Provides
    static BasePresenter providesBasePresenter() {
        return new BasePresenter();
    }
}
