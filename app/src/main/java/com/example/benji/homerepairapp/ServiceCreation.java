package com.example.benji.homerepairapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ServiceCreation extends AppCompatActivity {


    EditText sNameBox, hRateBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_creation);
        sNameBox = findViewById(R.id.serviceName);
        hRateBox = findViewById(R.id.hourlyRate);
    }
}
