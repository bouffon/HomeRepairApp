package com.example.benji.homerepairapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        //to allow a hourly rate change with fragments
        View saveRate = findViewById(R.id.save);
        saveRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveHourlyRate(v);
            }

        });

        //to allow a service deletion with fragments
        View deleteService = findViewById(R.id.delete);
        deleteService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deletePrompt(v);
            }
        });
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
        DBHandler db = new DBHandler(this);

        String hRate = changeRateBox.getText().toString();


        if(!isEmptyRate()){
            return;
        }

        db.updateRate(oldRate,service,hRate);

        //return to list of users
        Intent serviceManager = new Intent(this, AdminNav.class);
        startActivity(serviceManager);
    }

    public void deleteService(){
        DBHandler db = new DBHandler(this);

        db.deleteService(service);

        Intent serviceManager = new Intent(this, AdminNav.class);
        startActivity(serviceManager);
    }

    public void deletePrompt (View view){

        AlertDialog.Builder delMsg = new AlertDialog.Builder(this);
        delMsg.setMessage("Are you sure you want to delete this service?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int choice){
                        deleteService();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int choice){
                        dialog.cancel();
                    }
                });
        AlertDialog delConfirm = delMsg.create();
        delConfirm.setTitle("Delete a service");
        delConfirm.show();
    }
}
