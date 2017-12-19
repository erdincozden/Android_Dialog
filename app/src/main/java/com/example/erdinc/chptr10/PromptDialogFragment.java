package com.example.erdinc.chptr10;


import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PromptDialogFragment  extends DialogFragment implements View.OnClickListener{


    public static PromptDialogFragment newInstance(String prompt){
        PromptDialogFragment promptDialogFragment=new PromptDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putString("prompt",prompt);
        promptDialogFragment.setArguments(bundle);
        return promptDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        OnDialogDoneListener onDialogDoneListener=(OnDialogDoneListener)context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(true);
        int style=DialogFragment.STYLE_NORMAL,theme=0;
        setStyle(style,theme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.prompt_dialog,container,false);
        TextView textView=(TextView)view.findViewById(R.id.promptmessage);
        textView.setText(getArguments().getString("prompt"));

        Button dismissButton=(Button)view.findViewById(R.id.btn_dismiss);
        dismissButton.setOnClickListener(this);

        Button saveButton=(Button)view.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(this);

        Button helpButton=(Button)view.findViewById(R.id.btn_help);
        helpButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        OnDialogDoneListener onDialogDoneListener=(OnDialogDoneListener)getActivity();
        if(view.getId()==R.id.btn_save){
            TextView textView=(TextView)view.findViewById(R.id.inputtext);
            onDialogDoneListener.onDialogDone(this.getTag(),false,textView.getText());
            dismiss();
            return;
        }
        if(view.getId()==R.id.btn_dismiss){
            onDialogDoneListener.onDialogDone(this.getTag(),true,null);
            dismiss();
            return;
        }
        if(view.getId()==R.id.btn_help){
            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
            fragmentTransaction.remove(this);
            fragmentTransaction.addToBackStack(null);
            HelpDialogFragment helpDialogFragment=HelpDialogFragment.newInstance(R.string.help1);
        }
    }


    @Override
    public void onDismiss(DialogInterface di) {
        Log.v(MainActivity.LOGTAG, "in onDismiss() of PDF");
        super.onDismiss(di);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.v(MainActivity.LOGTAG, "in onCancel() of PDF");
        super.onCancel(dialog);
    }


}













