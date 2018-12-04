package com.example.benji.homerepairapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

import static java.util.Calendar.*;
import java.util.ArrayList;

public class SearchByHours extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    TextView day1View, day2View;
    String timeSelected = "";
    String dayOfWeek;
    private User homeOwner;

    int dayStart, dayEnd;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_hours);

        Intent i = getIntent();
        homeOwner = (User) i.getSerializableExtra("hO");

        ArrayList<String> days = new ArrayList<String>();

        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        Spinner spinner = (Spinner) findViewById(R.id.daySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, days );
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        dayOfWeek = (spinner.getSelectedItem().toString());

        View searchHours = findViewById(R.id.searchFromHours);

        day1View = (TextView) findViewById(R.id.day1Time);
        day2View = (TextView) findViewById(R.id.day2Time);

        day1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                timeSelected = "day1Time";
            }
        });


        day2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(day1View.getText().equals("__ : __")){
                    Toast.makeText(getApplicationContext(), "Please enter a start and end time", Toast.LENGTH_LONG).show();
                    return;
                }

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                timeSelected = "day2Time";


            }
        });

        searchHours.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(day1View.getText().toString().equals("__ : __") | day2View.getText().toString().equals("__ : __")){
                    Toast.makeText(getApplicationContext(), "Please enter a start and end time", Toast.LENGTH_LONG).show();
                }
                else{
                    searchServiceByHours(v);
                }
            }
        });
    }

    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

        String extraMinZero = "", extraHourZero = "";

        if (minute < 10) {
            extraMinZero = "0";
        }

        if (hourOfDay < 10) {
            extraHourZero = "0";
        }

        switch (timeSelected) {
            case "day1Time":
                if (day2View.getText().toString().equals("__ : __")) {
                    day1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                    dayStart = hourOfDay * 60 + minute;
                }

                else if (((hourOfDay * 60 + minute) < dayEnd) && day2View.getText().toString() != "__ : __") {
                    day1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                    dayStart = hourOfDay * 60 + minute;
                }
                else{
                    Toast.makeText(getApplicationContext(), "Start time must be before end time!", Toast.LENGTH_LONG).show();
                }
                break;
            case "day2Time":
                if ((hourOfDay * 60 + minute) > dayStart) {
                    day2View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                    dayEnd = hourOfDay * 60 + minute;
                }
                else{
                    Toast.makeText(getApplicationContext(), "End time must be greater than start time!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    protected String convertTimeToString(int hourOfDay, int minute, String extraMinZero, String extraHourZero){

        return extraHourZero + Integer.toString(hourOfDay) + " : " + extraMinZero + Integer.toString(minute);
    }

    public void searchServiceByHours(View view){
        Intent launchServiceList = new Intent(this, ScheduleService.class);
        launchServiceList.putExtra("startTime", day1View.getText().toString());
        launchServiceList.putExtra("endTime", day2View.getText().toString());
        launchServiceList.putExtra("day", dayOfWeek);
        launchServiceList.putExtra("hO", homeOwner);
        launchServiceList.putExtra("searchType", "hours");
        startActivity(launchServiceList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
