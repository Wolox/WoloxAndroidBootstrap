package ar.com.wolox.android.example.di;

import ar.com.wolox.android.example.ui.random.ExampleActivity;
import ar.com.wolox.android.example.ui.random.ExampleFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InjectorsModule {

    @ContributesAndroidInjector
    abstract ExampleActivity exampleActivity();

    @ContributesAndroidInjector
    abstract ExampleFragment exampleFragment();

}
