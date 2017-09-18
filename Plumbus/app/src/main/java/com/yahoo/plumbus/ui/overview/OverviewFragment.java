package com.yahoo.plumbus.ui.overview;

import com.yahoo.plumbus.R;
import com.yahoo.plumbus.ui.BaseFragment;

/**
 * Created by yxiang on 11/23/16.
 */

public class OverviewFragment extends BaseFragment {

    public static OverviewFragment newInstance() {
        OverviewFragment f = new OverviewFragment();
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_overview;
    }
}
