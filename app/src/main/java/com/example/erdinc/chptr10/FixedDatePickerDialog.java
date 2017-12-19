package com.example.erdinc.chptr10;


import android.app.DatePickerDialog;
import android.content.Context;

public class FixedDatePickerDialog extends DatePickerDialog {

    public FixedDatePickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    @Override
    protected void onStop() {

    }
}
