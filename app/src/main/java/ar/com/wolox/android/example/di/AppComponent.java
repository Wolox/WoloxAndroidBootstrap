package ar.com.wolox.android.example.di;

import android.app.Application;

import ar.com.wolox.android.example.BootstrapApplication;
import ar.com.wolox.android.example.ui.viewpager.ViewPagerModule;
import ar.com.wolox.wolmo.core.di.modules.ContextModule;
import ar.com.wolox.wolmo.networking.di.NetworkingComponent;
import ar.com.wolox.wolmo.networking.di.modules.NetworkingModule;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(dependencies = {NetworkingComponent.class},
           modules = { AndroidSupportInjectionModule.class, ContextModule.class,
               InjectorsModule.class, ViewPagerModule.class})
public interface AppComponent extends AndroidInjector<BootstrapApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BootstrapApplication> {

        @BindsInstance
        public abstract Builder application(Application application);

        @BindsInstance
        public abstract Builder sharedPreferencesName(String sharedPrefName);

        public abstract Builder networkingComponent(NetworkingComponent networkingComponent);

    }
}
