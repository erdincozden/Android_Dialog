package com.example.erdinc.chptr10;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment
        implements OnTimeSetListener {

    private OnDialogDoneListener dialogClient;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String timeStr = String.format("%02d", hourOfDay) + ":"
                + String.format("%02d", minute);
        dialogClient.onDialogDone(getTag(), false, timeStr);
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