package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter

import javax.inject.Inject

class ExamplePresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<IExampleView>() {

    fun storeUsername(text: String) {
        mUserSession.username = text
        view.onUsernameSaved()
    }
}
