package com.example.benji.homerepairapp;

import android.app.TimePickerDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

public class SearchByHours extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    TextView day1View, day2View;
    String timeSelected = "";

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

        Spinner spinner = getActivity().findViewById(R.id.daySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, days );
        spinner.setAdapter(adapter);

        day1View = (TextView) getActivity().findViewById(R.id.day1Time);
        day2View = (TextView) getActivity().findViewById(R.id.day2Time);

        day1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "day1Time";
            }
        });


        day2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(day2View.getText().equals("__ : __")){
                    return;
                }

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "day2Time";


            }
        });

        return v;
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

}
