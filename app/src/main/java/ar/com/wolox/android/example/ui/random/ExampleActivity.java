package ar.com.wolox.android.example.ui.random;


import ar.com.wolox.android.R;
import ar.com.wolox.wolmo.core.activity.WolmoActivity;

import javax.inject.Inject;

import dagger.Lazy;

public class ExampleActivity extends WolmoActivity {

    @Inject ExampleFragment mExampleFragment;

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_base_content, mExampleFragment);
    }
}
