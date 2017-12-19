package com.example.erdinc.chptr10;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HelpDialogFragment extends DialogFragment implements View.OnClickListener{

    public static HelpDialogFragment newInstance(int helpResId){
        HelpDialogFragment helpDialogFragment=new HelpDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("help_resource",helpResId);
        helpDialogFragment.setArguments(bundle);
        return helpDialogFragment;
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
        View v = inflater.inflate(
                R.layout.help_dialog, container, false);

        TextView tv = (TextView)v.findViewById(
                R.id.helpmessage);
        tv.setText(getActivity().getResources()
                .getText(getArguments().getInt("help_resource")));

        Button closeBtn = (Button)v.findViewById(
                R.id.btn_close);
        closeBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {

    }
}
