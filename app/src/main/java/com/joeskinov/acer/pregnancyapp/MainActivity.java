package com.joeskinov.acer.pregnancyapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static EditText DateEdit;
    private String string;
    private String string2;
    private long time;
    private long time2;
    private int cycle;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button validateButton = (Button)findViewById(R.id.validateButton);

        validateButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText lastCycle = (EditText) findViewById(R.id.lastCycle);
                        EditText mycycle = (EditText) findViewById(R.id.currentDate);

                        string = mycycle.getText().toString();
                        string2 = lastCycle.getText().toString();


                        if(string.isEmpty())
                            string = "25/06/2016";

                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date startDate = formatter.parse(string);
                            time = 86400000;
                            time = startDate.getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if(string2.isEmpty())
                            cycle = 28;
                        else
                            cycle = Integer.parseInt(string2);

                        final Calendar c = Calendar.getInstance();
                        Date now = c.getTime();
                        time2 = now.getTime();
                        TextView resultView = (TextView) findViewById(R.id.resultView);
                        time2 = time2 - time;
                        result = (int) (time2/(24*60*60*1000));
                        String strResult = "Great You are " + Integer.toString(result-cycle)+" Days pregnant ";
                        if(result>cycle){
                            resultView.setText(strResult);
                            startMySecondActivity("Pregnancy Tips", strResult);
                        }
                        else {
                            if(time2<0){
                                resultView.setText("Please enter a date before today");
                            }
                            else {
                                resultView.setText("You are not pregnant");
                            }
                        }

                    }
                }
        );

        DateEdit = (EditText) findViewById(R.id.currentDate);
        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyDatePickerDialog(v);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = getIntent().getStringExtra("name");
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

    private void startMySecondActivity(String  myString, String string){
        string += "Some Tips For You";
        Intent startIntent = new Intent(this, ResultActivity.class);
        startIntent.putExtra("name", myString);
        startIntent.putExtra("message", string);
        startActivity(startIntent);
    }
    public void showMyDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            String dateStr;
            if(month<10) {
                if(day<10)
                    dateStr = "0" + day + "/0" + (month + 1) + "/" + year;
                else
                    dateStr = day + "/0" + (month + 1) + "/" + year;
            }
            else {
                if(day<10)
                    dateStr = "0" + day + "/" + (month + 1) + "/" + year;
                else
                    dateStr = day + "/" + (month + 1) + "/" + year;
            }
            DateEdit.setText(dateStr);
        }
    }
}
