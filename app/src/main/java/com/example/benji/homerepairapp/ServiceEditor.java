package com.example.benji.homerepairapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceEditor extends AppCompatActivity {

    TextView message;
    EditText changeRateBox;
    String oldRate, service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_editor);
        message = findViewById(R.id.editPrompt);
        changeRateBox = findViewById(R.id.changeRateInput);

        //get Intent
        Intent receivedIntent = getIntent();

        service = receivedIntent.getStringExtra("service");
        oldRate = Double.toString(receivedIntent.getDoubleExtra("hourly rate", 0));

        message.setText("Change hourly rate for service " + service);   //prompt user to change rate
        changeRateBox.setText(oldRate); //display old rate initially
    }

    private boolean isEmptyRate(){
        String hRate = changeRateBox.getText().toString();

        if(hRate.isEmpty()){
            changeRateBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    public void saveHourlyRate(View view){
        ServiceDBHandler db = new ServiceDBHandler(this);

        String hRate = changeRateBox.getText().toString();


        if(!isEmptyRate()){
            return;
        }
        
        db.updateRate(oldRate,service,hRate);
        Intent serviceManager = new Intent(this, AdminServiceManager.class);
        startActivity(serviceManager);
    }
}
