package com.example.erdinc.chptr10;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnDialogDoneListener {


    public static final String LOGTAG = "DialogFragDemo";
    public static final String ALERT_DIALOG_TAG = "ALERT_DIALOG_TAG";
    public static final String HELP_DIALOG_TAG = "HELP_DIALOG_TAG";
    public static final String PROMT_DIALOG_TAG = "PROMPT_DIALOG_TAG";
    public static final String EMBED_DIALOG_TAG = "EMBEDDED_DIALOG_TAG";
    public static final String TIME_DIALOG_TAG = "TIME_DIALOG_TAG";
    public static final String DATE_DIALOG_TAG = "DATE_DIALOG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_show_alert_dialog:
                this.testAlertDialog();
                break;
            case R.id.menu_show_prompt_dialog:
                this.testPromptDialog();
                break;
            case R.id.menu_show_help_dialog:
                this.testHelpDialog();
                break;
            case R.id.menu_show_embedded_dialog:
                this.testEmbeddedDialog();
                break;
            case R.id.menu_show_time_dialog:
                this.testTimePickerDialog();
                break;
            case R.id.menu_show_date_dialog:
                this.testDatePickerDialog();
                break;
        }
        return true;
    }

    private void testHelpDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        HelpDialogFragment helpDialogFragment = HelpDialogFragment.newInstance(R.string.help_text);
        helpDialogFragment.show(fragmentTransaction, HELP_DIALOG_TAG);
    }

    private void testEmbeddedDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        PromptDialogFragment promptDialogFragment = PromptDialogFragment.newInstance("Enter Some");
        fragmentTransaction.add(R.id.embeddedDialog, promptDialogFragment, EMBED_DIALOG_TAG);
        fragmentTransaction.commit();
    }

    private void testPromptDialog() {
        FragmentTransaction ft =
                getFragmentManager().beginTransaction();

        PromptDialogFragment pdf =
                PromptDialogFragment.newInstance(
                        "Enter Something");

        pdf.show(ft, PROMT_DIALOG_TAG);
    }

    private void testAlertDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        AlertDialogFragment alertDialogFragment = AlertDialogFragment.newInstance("Alert Message");
        alertDialogFragment.show(fragmentTransaction, ALERT_DIALOG_TAG);
    }

    private void testTimePickerDialog()
    {
        FragmentTransaction ft =
                getFragmentManager().beginTransaction();

        TimePickerFragment tpf = new TimePickerFragment();

        tpf.show(ft, TIME_DIALOG_TAG);
    }

    private void testDatePickerDialog()
    {
        FragmentTransaction ft =
                getFragmentManager().beginTransaction();

        DatePickerFragment dpf = new DatePickerFragment();

        dpf.show(ft, DATE_DIALOG_TAG);
    }


    @Override
    public void onDialogDone(String tag, boolean cancelled, CharSequence message) {
        String s = tag + " respond with " + message;
        if (cancelled) {
            s = tag + "was cancelled by the user";
        }
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        Log.v(LOGTAG, s);
    }
}
