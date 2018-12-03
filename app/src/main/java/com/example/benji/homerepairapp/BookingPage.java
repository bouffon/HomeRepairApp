package com.example.benji.homerepairapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookingPage extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    TextView day1View, day2View, mo1View, mo2View, tu1View, tu2View, we1View, we2View, th1View, th2View, fr1View, fr2View, sa1View, sa2View, su1View, su2View;
    TextView serviceProviderView;
    String timeSelected = "";
    String dayOfWeek;
    String hUser, hPassW, sUser, sPassW;

    int dayStart, dayEnd;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);

        Intent i = getIntent();
        hUser = ((Homeowner) i.getSerializableExtra("hO")).getUsername();
        hPassW = ((Homeowner) i.getSerializableExtra("hO")).getPassword();
        sUser = ((ServiceProvider) i.getSerializableExtra("sp")).getUsername();
        sPassW = ((ServiceProvider) i.getSerializableExtra("sp")).getPassword();
        String[] times = ((ServiceProvider) i.getSerializableExtra("sp")).getTimes();


        serviceProviderView = (TextView) findViewById(R.id.ServiceTimes);
        mo1View = (TextView) findViewById(R.id.mo1);
        mo2View = (TextView) findViewById(R.id.mo2);
        tu1View = (TextView) findViewById(R.id.tu1);
        tu2View = (TextView) findViewById(R.id.tu2);
        we1View = (TextView) findViewById(R.id.we1);
        we2View = (TextView) findViewById(R.id.we2);
        th1View = (TextView) findViewById(R.id.th1);
        th2View = (TextView) findViewById(R.id.th2);
        fr1View = (TextView) findViewById(R.id.fr1);
        fr2View = (TextView) findViewById(R.id.fr2);
        sa1View = (TextView) findViewById(R.id.sa1);
        sa2View = (TextView) findViewById(R.id.sa2);
        su1View = (TextView) findViewById(R.id.su1);
        su2View = (TextView) findViewById(R.id.su2);

        serviceProviderView.setText(sUser + "'s Weekly Schedule");
        mo1View.setText(times[0]);
        mo2View.setText(times[1]);
        tu1View.setText(times[2]);
        tu2View.setText(times[3]);
        we1View.setText(times[4]);
        we2View.setText(times[5]);
        th1View.setText(times[6]);
        th2View.setText(times[7]);
        fr1View.setText(times[8]);
        fr2View.setText(times[9]);
        sa1View.setText(times[10]);
        sa2View.setText(times[11]);
        su1View.setText(times[12]);
        su2View.setText(times[13]);


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

        View booking = findViewById(R.id.book);

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
                    return;
                }

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                timeSelected = "day2Time";


            }
        });

        booking.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(day1View.getText().toString().equals("__ : __") | day2View.getText().toString().equals("__ : __")){
                    Toast.makeText(getApplicationContext(), "Please enter a start and end time", Toast.LENGTH_LONG).show();
                }
                else{
                    book(v);
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

    public void book(View view){
        DBHandler db = new DBHandler(this);
        Intent launchServiceList = new Intent(this, HomeOwnerPage.class);
        launchServiceList.putExtra("startTime", day1View.getText().toString());
        launchServiceList.putExtra("endTime", day2View.getText().toString());
        launchServiceList.putExtra("day", dayOfWeek);
        db.createNewBooking(sUser, sPassW, hUser, hPassW ,dayOfWeek, day1View.getText().toString(), day2View.getText().toString());
        startActivity(launchServiceList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
