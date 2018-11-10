package com.example.benji.homerepairapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Double.parseDouble;

public class ServiceCreation extends AppCompatActivity {

    EditText sNameBox, hRateBox;
    TextView errorPrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_creation);
        sNameBox = findViewById(R.id.serviceName);
        hRateBox = findViewById(R.id.hourlyRate);
        errorPrompt = findViewById(R.id.errorPrompt);
    }

    public void newService(View view){
        ServiceDBHandler db = new ServiceDBHandler(this);
        String serviceName = sNameBox.getText().toString();
        String rate = hRateBox.getText().toString();

        if (!isEmptyRate() | !isEmptyServiceName() ){
            return;
        }
        if (db.findService(serviceName) == null){
            Service service = new Service(serviceName, parseDouble(rate));
            db.addService(service);
            Intent intent = new Intent(this, AdminServiceManager.class);
            startActivity(intent);
        } else {
            errorPrompt.setText("THIS SERVICE ALREADY EXISTS");
        }
    }

    private boolean isEmptyServiceName(){
        String sName = sNameBox.getText().toString();

        if(sName.isEmpty()){
            sNameBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    private boolean isEmptyRate(){
        String hRate = hRateBox.getText().toString();

        if(hRate.isEmpty()){
            hRateBox.setError("Field cannot be empty!");
            return false;
        }
        return true;

    }
}
