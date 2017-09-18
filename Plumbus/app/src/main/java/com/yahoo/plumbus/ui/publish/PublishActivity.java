package com.yahoo.plumbus.ui.publish;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.yahoo.plumbus.ui.BaseActivity;

/**
 * Created by yxiang on 11/23/16.
 */

public class PublishActivity extends BaseActivity {

    @Override
    protected Fragment getInitialFragment() {
        return null;
    }

    @Override
    protected void setUpActionbar(Toolbar toolbar) {
        toolbar.setTitle("Create new entry");
    }
}
