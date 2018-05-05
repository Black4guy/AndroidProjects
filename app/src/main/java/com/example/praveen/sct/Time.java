package com.example.praveen.sct;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Time extends AppCompatActivity {

    Button btnTimePicker, btnTimePicker1, btnSubmit;
    TextView txtTime, txtTime1;
    private int  mHour, mMinute;
    String saved_inTime, saved_outTime, inTime, outTime;
    private DbHandler dbHandler;
    String strHrsToShow;
    private Calendar datetime1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Time");

        btnTimePicker = (Button) findViewById(R.id.btn_from);
        btnTimePicker1 = (Button) findViewById(R.id.btn_To);
        btnSubmit = (Button) findViewById(R.id.btn_submit);


        txtTime = (TextView) findViewById(R.id.in_time);
        txtTime1 = (TextView) findViewById(R.id.out_time);
    }
    @Override
    public void onResume() {
        super.onResume();


        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                final TimePickerDialog timePickerDialog=new TimePickerDialog(Time.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar datetime = Calendar.getInstance();
                        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        datetime.set(Calendar.MINUTE, minute);

                         strHrsToShow = (datetime.get(Calendar.HOUR_OF_DAY) == 0) ?"12":Integer.toString( datetime.get(Calendar.HOUR_OF_DAY) );

                        saved_inTime=strHrsToShow+":"+datetime.get(Calendar.MINUTE);
                        inTime=saved_inTime;
                        txtTime.setText(inTime);


                    }
                },mHour, mMinute, true);


                timePickerDialog.show();


            }
        });
        btnTimePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                final TimePickerDialog timePickerDialog=new TimePickerDialog(Time.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                         datetime1 = Calendar.getInstance();
                        datetime1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        datetime1.set(Calendar.MINUTE, minute);


                        String strHrsToShow1 = (datetime1.get(Calendar.HOUR_OF_DAY) == 0) ?"12":Integer.toString( datetime1.get(Calendar.HOUR_OF_DAY) );
                        saved_outTime=strHrsToShow1+":"+datetime1.get(Calendar.MINUTE);
                        outTime=saved_outTime;

                        txtTime1.setText( outTime );


                    }
                },mHour, mMinute, true);


                timePickerDialog.show();


            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saved_inTime==null){
                    Toast.makeText(Time.this, "Please select TimeIn", Toast.LENGTH_SHORT).show();


                } else if(saved_outTime==null) {
                    Toast.makeText(Time.this, "Please select TimeOut", Toast.LENGTH_SHORT).show();
                }
                   else if(saved_inTime==null&&saved_outTime==null){
                    Toast.makeText(Time.this, "Please select Time", Toast.LENGTH_SHORT).show();
                }

                      else{
                        if(strHrsToShow.equals("24")){
                            saved_inTime="00"+":"+datetime1.get(Calendar.MINUTE);

                        }
    SharedPreferences sharedpreferences =Time.this.getSharedPreferences("timing", Context.MODE_PRIVATE);

    SharedPreferences.Editor editor = sharedpreferences.edit();
    editor.putString("timein",saved_inTime);
    editor.putString("timeout",saved_outTime);
    editor.apply();
    timeSchedule();

    Intent i = new Intent(Time.this, Prefrences.class);
    startActivity(i);
    finish();
}

            }
        });

    }
    public void timeSchedule(){

        String[] hourMin = saved_inTime.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        mins=hoursInMins+mins;


        String[] hourMins = saved_outTime.split(":");
        int hours = Integer.parseInt(hourMins[0]);
        int minss = Integer.parseInt(hourMins[1]);
        int hoursInMinss = hours * 60;
        minss=hoursInMinss+minss;

        int time=minss-mins;


        int cafe=30;
        int theater=220;
        int shopping=90;
        int monument=300;
        int food=30;
        int mall=90;
    }
}
