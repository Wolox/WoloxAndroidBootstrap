package ar.com.wolox.android.example;


import ar.com.wolox.android.R;
import ar.com.wolox.android.activity.WoloxActivity;

public class ExampleActivity extends WoloxActivity {

    @Override
    protected int layout() {
        return R.layout.activity_base;
    }

    @Override
    protected void init() {
        replaceFragment(R.id.activity_base_content, ExampleFragment.newInstance());
    }
}
