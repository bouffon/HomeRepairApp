package com.example.benji.homerepairapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

import static java.util.Calendar.*;
import java.util.ArrayList;

public class SearchByHours extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    TextView day1View, day2View;
    String timeSelected = "";
    String dayOfWeek;

    int dayStart, dayEnd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.activity_search_by_hours, container, false);

        ArrayList<String> days = new ArrayList<String>();

        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        Spinner spinner = (Spinner) v.findViewById(R.id.daySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, days );
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        day1View = (TextView) v.findViewById(R.id.day1Time);
        day2View = (TextView) v.findViewById(R.id.day2Time);

        day1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new SearchByHours();
                timePicker.show(getFragmentManager(), "time picker");
                timeSelected = "day1Time";
            }
        });


        day2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(day1View.getText().equals("__ : __")){
                    return;
                }

                DialogFragment timePicker = new SearchByHours();
                timePicker.show(getFragmentManager(), "time picker");
                timeSelected = "day2Time";


            }
        });

        View searchHours = v.findViewById(R.id.searchFromHours);
        searchHours.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(day1View.getText().toString().equals("__ : __") | day2View.getText().toString().equals("__ : __")){
                    Toast.makeText(getActivity(), "Please enter a start and end time ", Toast.LENGTH_LONG).show();
                }
                else{
                    searchServiceByHours(v);
                }
            }
        });

        return v;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker

        final Calendar calendar = getInstance();
        int hour = calendar.get(HOUR_OF_DAY);
        int minute = calendar.get(MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }


    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

        String extraMinZero = "", extraHourZero = "";

        if (minute < 10) {
            extraMinZero = "0";
        }

        if (hourOfDay < 10) {
            extraHourZero = "0";
        }

        Log.d("guyishot", timeSelected);
        switch (timeSelected) {
            case "day1Time":
                if (day2View.getText().toString().equals("__ : __")) {
                    day1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                    dayStart = hourOfDay * 60 + minute;
                }
                if (((hourOfDay * 60 + minute) < dayEnd) && day2View.getText().toString() != "__ : __") {
                    day1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                    dayStart = hourOfDay * 60 + minute;
                }
                break;
            case "day2Time":
                if ((hourOfDay * 60 + minute) > dayStart) {
                    day2View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                    dayEnd = hourOfDay * 60 + minute;
                }
                break;
        }
    }
    private String convertTimeToString(int hourOfDay, int minute, String extraMinZero, String extraHourZero){

        return extraHourZero + Integer.toString(hourOfDay) + " : " + extraMinZero + Integer.toString(minute);
    }

    public void searchServiceByHours(View view){
        Intent launchServiceList = new Intent(getActivity(), ScheduleService.class);
        launchServiceList.putExtra("dayStart", day1View.getText().toString());
        launchServiceList.putExtra("dayEnd", day2View.getText().toString());
        launchServiceList.putExtra("searchType", "hours");
        startActivity(launchServiceList);
    }

}
