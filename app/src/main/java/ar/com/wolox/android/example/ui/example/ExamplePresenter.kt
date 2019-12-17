package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ExamplePresenter @Inject constructor(private val userSession: UserSession) : BasePresenter<ExampleView>() {

    fun onLoginButtonClicked(text: String) {
        userSession.username = text
        view?.goToViewPager()
    }

    fun onUsernameInputChanged(text: String) {
        view?.toggleLoginButtonEnable(text.isNotBlank())
    }
}
