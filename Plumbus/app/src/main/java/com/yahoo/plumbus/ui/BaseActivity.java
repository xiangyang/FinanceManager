package com.yahoo.plumbus.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yahoo.plumbus.R;

/**
 * Created by yxiang on 11/23/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, getInitialFragment()).commit();
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionbar(mToolbar);
    }

    private int getContentLayoutId() {
        return R.layout.activity_base;
    }

    protected abstract Fragment getInitialFragment();

    public Toolbar getToolbar() {
        return mToolbar;
    }

    abstract protected void setUpActionbar(Toolbar toolbar);



}
