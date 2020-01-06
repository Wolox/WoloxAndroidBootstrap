package ar.com.wolox.android.example.ui.viewpager.request

interface RequestView {

    fun setTitle(title: String)

    fun setBody(body: String)

    fun showError()

    fun showInvalidInput()
}
