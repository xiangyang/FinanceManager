package com.yahoo.plumbus.ui.cost;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.yahoo.plumbus.R;
import com.yahoo.plumbus.ui.BaseActivity;

/**
 * Created by yangxiang on 1/21/17.
 */

public class CostActivity extends BaseActivity {

    @Override
    protected Fragment getInitialFragment() {
        return CostFragment.newInstance();
    }

    @Override
    protected void setUpActionbar(Toolbar toolbar) {
        toolbar.setTitle("Plumbus Money");
        toolbar.setTitleTextColor(getResources().getColor(R.color.rick_light));
    }
}
