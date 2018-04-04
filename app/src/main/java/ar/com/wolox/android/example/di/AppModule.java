package ar.com.wolox.android.example.di;

import ar.com.wolox.android.example.ui.example.ExampleActivity;
import ar.com.wolox.android.example.ui.example.ExampleFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {

    @ContributesAndroidInjector
    abstract ExampleActivity exampleActivity();

    @ContributesAndroidInjector
    abstract ExampleFragment exampleFragment();
}
