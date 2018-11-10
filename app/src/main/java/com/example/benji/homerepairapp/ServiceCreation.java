package com.example.benji.homerepairapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Double.parseDouble;

public class ServiceCreation extends Fragment {

    EditText sNameBox, hRateBox;
    TextView errorPrompt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.activity_service_creation, container, false);
        sNameBox = v.findViewById(R.id.serviceName);
        hRateBox = v.findViewById(R.id.hourlyRate);
        errorPrompt = v.findViewById(R.id.errorPrompt);

        //to allow a service creation with fragments
       View serviceCreate = v.findViewById(R.id.createServiceButton);
       serviceCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                newService(v);
            }
        });


        return v;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_creation);
        sNameBox = findViewById(R.id.serviceName);
        hRateBox = findViewById(R.id.hourlyRate);
        errorPrompt = findViewById(R.id.errorPrompt);
    }*/

    public void newService(View view){
        ServiceDBHandler db = new ServiceDBHandler(getActivity());
        String serviceName = sNameBox.getText().toString();
        String rate = hRateBox.getText().toString();

        if (!isEmptyRate() | !isEmptyServiceName() ){
            return;
        }
        if (db.findService(serviceName) == null){
            Service service = new Service(serviceName, parseDouble(rate));
            db.addService(service);
            Intent intent = new Intent(getActivity(), AdminNav.class);
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
