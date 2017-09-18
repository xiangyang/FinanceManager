package com.yahoo.plumbus.ui.derp;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.yahoo.plumbus.ui.BaseActivity;

/**
 * Created by yxiang on 11/27/16.
 */

public class DerpActivity extends BaseActivity {

    @Override
    protected Fragment getInitialFragment() {
        return DerpFragment.newInstance();
    }

    @Override
    protected void setUpActionbar(Toolbar toolbar) {
        toolbar.setTitle("DERRRRRRRP!!!!!");
    }
}
