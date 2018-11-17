package com.example.benji.homerepairapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;


public class ServiceProviderInformation extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    EditText cNameBox;
    EditText sDescriptionBox;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_info);
        cNameBox = findViewById(R.id.companyName);
        sDescriptionBox = findViewById(R.id.description);



        Button monday = (Button) findViewById(R.id.monday);
        Button tuesday = (Button) findViewById(R.id.tuesday);
        Button wednesday = (Button) findViewById(R.id.wednesday);
        Button thursday = (Button) findViewById(R.id.thursday);
        Button friday = (Button) findViewById(R.id.friday);
        Button saturday = (Button) findViewById(R.id.saturday);
        Button sunday = (Button) findViewById(R.id.sunday);

        monday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        tuesday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        wednesday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        thursday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        friday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        saturday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        sunday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


    }

    private boolean isEmptyCName(){
        String cName = cNameBox.getText().toString();

        if(cName.isEmpty()){
            cNameBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    private boolean isEmptysDescription(){
        String sDescription = sDescriptionBox.getText().toString();

        if(sDescription.isEmpty()){
            sDescriptionBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("Hour: " + hourOfDay + "Minute: " + minute);
    }
}