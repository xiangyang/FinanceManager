package com.yahoo.plumbus.ui.overview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.yahoo.plumbus.ui.BaseActivity;

/**
 * Created by yxiang on 11/23/16.
 */

public class OverviewActivity extends BaseActivity {

    public static void openActivity(Context context) {
        Intent i = new Intent(context, OverviewActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected Fragment getInitialFragment() {
        return OverviewFragment.newInstance();
    }

    @Override
    protected void setUpActionbar(Toolbar toolbar) {
        toolbar.setTitle("Plumbus Overview");
    }

}
