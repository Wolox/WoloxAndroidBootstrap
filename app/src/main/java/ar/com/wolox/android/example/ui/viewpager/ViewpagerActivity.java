package ar.com.wolox.android.example.ui.viewpager;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.viewpager.fragment.ViewPagerFragment;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

import javax.inject.Inject;

import dagger.Lazy;

public class ViewpagerActivity extends WolmoActivity {

    // Lazy example, a lazy injection does not build the dependencies until #get() is called
    @Inject Lazy<ViewPagerFragment> mLazyViewpagerFragment;

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_base_content, mLazyViewpagerFragment.get());
    }
}
