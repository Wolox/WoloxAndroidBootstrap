package ar.com.wolox.android.example.di.module

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import dagger.Module
import dagger.Provides

@Module
class MiscModule {

    @Provides
    internal fun provideBasePresenter(): BasePresenter<Any> {
        return BasePresenter()
    }
}
