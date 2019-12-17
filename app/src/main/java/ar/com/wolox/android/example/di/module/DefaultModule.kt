package ar.com.wolox.android.example.di.module

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.SparseArray
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.core.fragment.WolmoFragmentHandler
import ar.com.wolox.wolmo.core.permission.PermissionListener
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.util.Logger
import ar.com.wolox.wolmo.core.util.ToastFactory
import dagger.Module
import dagger.Provides

@Module
class DefaultModule {

    @Provides
    @ApplicationScope
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(sharedPrefName: String, context: Context): SharedPreferences {
        return context.getSharedPreferences(sharedPrefName, Activity.MODE_PRIVATE)
    }

    @Provides
    fun providesPermissionManagerArray() = SparseArray<PermissionListener>()

    @Provides
    fun providesDefaultBasePresenter() = BasePresenter<Any>()

    /** Provides a default [WolmoFragmentHandler] with no presenter for fragments that don't need it. */
    @Provides
    fun providesDefaultWolmoFragmentHandler(toastFactory: ToastFactory, logger: Logger, basePresenter: BasePresenter<Any>): WolmoFragmentHandler<Any, BasePresenter<Any>> {
        return WolmoFragmentHandler(toastFactory, logger, basePresenter)
    }
}
