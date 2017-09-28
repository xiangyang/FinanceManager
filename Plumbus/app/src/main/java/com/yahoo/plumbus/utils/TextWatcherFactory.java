package com.yahoo.plumbus.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;

/**
 * Created by yangxiang on 9/25/17.
 */

public final class TextWatcherFactory {

    private TextWatcherFactory() {}

    public static void attachCurrentyWatcher(EditText editText) {
        editText.addTextChangedListener(new NumberTextWatcher(editText));
    }


    private static class NumberTextWatcher implements TextWatcher {

        private String current = "";
        private final EditText et;


        public NumberTextWatcher(EditText editText) {
            et = editText;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().equals(current)) {
                int selectionStart = et.getSelectionStart();
                et.removeTextChangedListener(this);

                String cleanString = s.toString().replaceAll("[$,.]", "");

                double parsed = Double.parseDouble(cleanString);
                String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));
                current = formatted;
                et.setText(formatted);
                selectionStart += (formatted.length() - s.length());
                et.setSelection(Math.min(selectionStart, formatted.length()));

                et.addTextChangedListener(this);
            }
        }
    }
}
