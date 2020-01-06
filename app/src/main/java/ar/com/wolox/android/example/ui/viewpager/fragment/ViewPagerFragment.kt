package ar.com.wolox.android.example.ui.viewpager.fragment

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.viewpager.widget.ViewPager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.random.RandomFragment
import ar.com.wolox.android.example.ui.viewpager.request.RequestFragment
import ar.com.wolox.android.example.utils.Extras.ViewPager.FAVOURITE_COLOR_KEY
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import dagger.Lazy
import kotlinx.android.synthetic.main.fragment_viewpager.*
import javax.inject.Inject

class ViewPagerFragment private constructor() : WolmoFragment<ViewPagerPresenter>(), ViewPagerView {

    // Lazy example, a lazy injection does not build the dependencies until #get() is called
    @Inject
    internal lateinit var randomFragment: Lazy<RandomFragment>

    @Inject
    internal lateinit var requestFragment: RequestFragment

    override fun layout() = R.layout.fragment_viewpager

    override fun handleArguments(arguments: Bundle?) = arguments?.containsKey(FAVOURITE_COLOR_KEY)

    override fun init() {
        vViewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                randomFragment.get() to "Page 1",
                requestFragment to "Page 2")
        }
        presenter.onInit(requireArgument(FAVOURITE_COLOR_KEY))
    }

    override fun setListeners() {
        vViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                presenter.onSelectedTab(position)
            }
        })
        presenter.onSelectedTab(0)
    }

    override fun showUserAndFavouriteColor(username: String, favouriteColor: String) {
        vViewPagerTitle.text = resources.getString(R.string.view_pager_title, username, favouriteColor)
    }

    override fun setToolbarTitle(title: ViewPagerToolbarTitle) {
        val titleRes = when (title) {
            ViewPagerToolbarTitle.RANDOM -> R.string.random_toolbar_title
            ViewPagerToolbarTitle.REQUEST -> R.string.request_toolbar_title
        }
        vToolbar.setTitle(titleRes)
    }

    companion object {

        fun newInstance(favouriteColor: String) = ViewPagerFragment().apply {
            arguments = bundleOf(FAVOURITE_COLOR_KEY to favouriteColor)
        }
    }
}

enum class ViewPagerToolbarTitle { RANDOM, REQUEST }
