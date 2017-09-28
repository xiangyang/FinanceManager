package com.yahoo.plumbus.ui.cost;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yahoo.plumbus.R;
import com.yahoo.plumbus.ui.BaseFragment;
import com.yahoo.plumbus.utils.TextWatcherFactory;

import java.text.NumberFormat;

/**
 *
 * Created by yangxiang on 1/21/17.
 */

public class CostFragment extends BaseFragment {

    private TextView mDescriptionTV;
    private TextView mAmountTV;
    private SeekBar mSeekbar;
    private TextView mSeekbarPercentage;
    private TextView mRatioEstimate;

    private int mDisabledColor;
    private int mEnabledColor;

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

        mDescriptionTV = layout.findViewById(R.id.entry_description);
        mAmountTV = layout.findViewById(R.id.entry_amount);
        mSeekbar = layout.findViewById(R.id.ratio_seekbar);
        mSeekbarPercentage = layout.findViewById(R.id.ratio_text);
        mRatioEstimate = layout.findViewById(R.id.amount_divide);

        mSeekbar.setMax(100);
        mSeekbar.setProgress(50);
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSeekbarPercentage.setText("%" + progress);
                updateRatioAmount();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        layout.findViewById(R.id.amount_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAmountDialog();
            }
        });
        mDescriptionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDescriptionDialog();
            }
        });

        mDisabledColor = getResources().getColor(R.color.disabled);
        mEnabledColor = getResources().getColor(R.color.secondary_text_light);

        return layout;
    }

    private void createDescriptionDialog() {
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("add description");
        String defaultText = input.getResources().getString(R.string.new_entry_desc);
        if (!mDescriptionTV.getText().toString().equals(defaultText)) {
            input.setText(mDescriptionTV.getText().toString());
        }
        createDialog(mDescriptionTV, input, defaultText);
    }

    private void createAmountDialog() {
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        TextWatcherFactory.attachCurrentyWatcher(input);
        String defaultText = input.getResources().getString(R.string.new_entry_price);
        if (!mAmountTV.getText().toString().equals(defaultText)) {
            input.setText(mAmountTV.getText().toString());
        } else {
            input.setText("0");
        }
        createDialog(mAmountTV, input, defaultText).setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                updateRatioAmount();
            }
        });

    }

    private void updateRatioAmount() {
        String text = mAmountTV.getText().toString().replaceAll("[$,.]", "");
        if (text.isEmpty()) {
            return;
        }
        double value = Integer.valueOf(text) * ((float) mSeekbar.getProgress() / 100);
        text = NumberFormat.getCurrencyInstance().format((value / 100));
        text = "(" + text + ")";
        mRatioEstimate.setText(text);
    }

    private AlertDialog createDialog(final TextView textView, final EditText editText, final String defaultString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        editText.setPadding(50, 50, 50, 0);
        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setSelection(editText.getText().length());
        builder.setView(editText);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editText.getText().toString();
                if (text.isEmpty()) {
                    text = defaultString;
                    textView.setTextColor(mDisabledColor);
                } else {
                    textView.setTextColor(mEnabledColor);
                }
                textView.setText(text);
                InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.show();
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        return dialog;
    }




}
