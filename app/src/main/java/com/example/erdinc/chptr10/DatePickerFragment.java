package com.example.erdinc.chptr10;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment
        implements OnDateSetListener {

    private OnDialogDoneListener dialogClient;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new FixedDatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d(MainActivity.LOGTAG, "in onDateSet()");
        String dateStr = String.format("%02d", month+1) + "/"
                + String.format("%02d", day) + "/"
                + String.format("%04d", year);
        dialogClient.onDialogDone(getTag(), false, dateStr);
    }

    @Override
    public void onAttach(Activity act) {
        try {
            dialogClient = (OnDialogDoneListener)act;
        }
        catch(ClassCastException cce) {
            // Here is where we fail gracefully.
            Log.e(MainActivity.LOGTAG, "Activity is not listening");
        }
        super.onAttach(act);
    }
}