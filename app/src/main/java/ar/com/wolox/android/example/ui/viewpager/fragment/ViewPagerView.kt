package ar.com.wolox.android.example.ui.viewpager.fragment

interface ViewPagerView {

    fun showUserAndFavouriteColor(username: String, favouriteColor: String)

    fun setToolbarTitle(title: ViewPagerToolbarTitle)
}
