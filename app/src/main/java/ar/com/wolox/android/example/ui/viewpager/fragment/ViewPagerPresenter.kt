package ar.com.wolox.android.example.ui.viewpager.fragment

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ViewPagerPresenter @Inject constructor(private val userSession: UserSession) : BasePresenter<ViewPagerView>() {

    fun onInit(favouriteColor: String) = userSession.username?.let {
        view?.showUserAndFavouriteColor(it, favouriteColor)
    }

    fun onSelectedTab(position: Int) = view?.setToolbarTitle(ViewPagerToolbarTitle.values()[position])
}
