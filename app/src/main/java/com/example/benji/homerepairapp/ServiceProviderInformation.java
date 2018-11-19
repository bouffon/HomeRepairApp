package com.example.benji.homerepairapp;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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



        TextView mo1View = (TextView) findViewById(R.id.mo1);
        TextView tu1View = (TextView) findViewById(R.id.tu1);
        TextView we1View = (TextView) findViewById(R.id.we1);
        TextView th1View = (TextView) findViewById(R.id.th1);
        TextView fr1View = (TextView) findViewById(R.id.fr1);
        TextView sa1View = (TextView) findViewById(R.id.sa1);
        TextView su1View = (TextView) findViewById(R.id.su1);

        TextView mo2View = (TextView) findViewById(R.id.mo2);
        TextView tu2View = (TextView) findViewById(R.id.tu2);
        TextView we2View = (TextView) findViewById(R.id.we2);
        TextView th2View = (TextView) findViewById(R.id.th2);
        TextView fr2View = (TextView) findViewById(R.id.fr2);
        TextView sa2View = (TextView) findViewById(R.id.sa2);
        TextView su2View = (TextView) findViewById(R.id.su2);


        // THIS IS FOR THE FIRST TIME
        mo1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        tu1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        we1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        th1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        fr1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        sa1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        su1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        // THIS IS FOR THE END TIME
        mo2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        tu2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        we2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        th2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        fr2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        sa2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        su2View.setOnClickListener(new View.OnClickListener(){
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