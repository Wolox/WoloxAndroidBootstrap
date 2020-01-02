package ar.com.wolox.android.example.ui.example

interface ExampleView {

    fun goToViewPager(favouriteColor: String)

    fun openBrowser(url: String)

    fun toggleLoginButtonEnable(isEnable: Boolean)

    fun openPhone(woloxPhone: String): Any
}
