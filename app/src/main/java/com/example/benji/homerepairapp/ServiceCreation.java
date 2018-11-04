package com.example.benji.homerepairapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static java.lang.Double.parseDouble;

public class ServiceCreation extends AppCompatActivity {

    EditText sNameBox, hRateBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_creation);
        sNameBox = findViewById(R.id.serviceName);
        hRateBox = findViewById(R.id.hourlyRate);
    }
    //TODO add field validation for service name and rate.
    public void newService(View view){
        ServiceDBHandler db = new ServiceDBHandler(this);
        String serviceName = sNameBox.getText().toString();
        Double rate = parseDouble(hRateBox.getText().toString());

        if (db.findService(serviceName) == null){
            Service service = new Service(serviceName,rate);
            db.addService(service);
            Intent intent = new Intent(this, AdminServiceManager.class);
            startActivity(intent);
        }


    }
}
