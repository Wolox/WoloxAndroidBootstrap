package ar.com.wolox.android.example.di

import ar.com.wolox.android.example.ui.example.ExampleActivity
import ar.com.wolox.android.example.ui.example.ExampleFragment
import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragment
import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragmentModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    internal abstract fun exampleActivity(): ExampleActivity

    @ContributesAndroidInjector
    internal abstract fun exampleFragment(): ExampleFragment
}
