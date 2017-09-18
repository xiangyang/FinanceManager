package com.yahoo.plumbus.ui.cost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.yahoo.plumbus.R;
import com.yahoo.plumbus.ui.BaseFragment;

/**
 * Created by yangxiang on 1/21/17.
 */

public class CostFragment extends BaseFragment{

    private static final String USER1 = "Pie";
    private static final String USER2 = "Mellie";

    private TextView mUserTV;
    private SwitchCompat mSwitch;
    private EditText mDescriptionET;
    private EditText mNameET;
    private EditText mAmountET;

    public static CostFragment newInstance() {
        CostFragment f = new CostFragment();
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cost;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container, savedInstanceState);
        mSwitch = (SwitchCompat) layout.findViewById(R.id.user_switch);
        mUserTV = (TextView) layout.findViewById(R.id.user);
        mDescriptionET = (EditText) layout.findViewById(R.id.entry_description);
        mNameET = (EditText) layout.findViewById(R.id.entry_name);
        mAmountET = (EditText) layout.findViewById(R.id.entry_amount);



        mUserTV.setText(mSwitch.isChecked()? USER2 : USER1);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mUserTV.setText(mSwitch.isChecked()? USER2 : USER1);
            }
        });
        return layout;
    }




}
