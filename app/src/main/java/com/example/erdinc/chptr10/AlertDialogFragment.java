package com.example.erdinc.chptr10;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public static AlertDialogFragment newInstance(String message){
        AlertDialogFragment alertDialogFragment=new AlertDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putString("alert-message",message);
        alertDialogFragment.setArguments(bundle);
        return alertDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        OnDialogDoneListener test=(OnDialogDoneListener)context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);
        int style=DialogFragment.STYLE_NORMAL,theme=0;
        setStyle(style,theme);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                .setTitle("Alert!!").setPositiveButton("OK",this)
                .setNegativeButton("Cancel",this)
                .setMessage(this.getArguments().getString("alert-message"));
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        OnDialogDoneListener onDialogDoneListener=(OnDialogDoneListener)getActivity();
        boolean cancelled=false;
        if(i==AlertDialog.BUTTON_NEGATIVE){
            cancelled=true;
        }
        onDialogDoneListener.onDialogDone(getTag(),cancelled,"Alert dismissed");
    }
}
