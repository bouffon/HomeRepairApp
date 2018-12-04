package com.example.benji.homerepairapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RateProvider extends AppCompatActivity {

    DBHandler db;
    ServiceProvider sp;
    User hO;
    List<ServiceProvider> serviceProviders;
    ArrayList<ServiceProvider> ratedServiceProviders;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_provider);

        db = new DBHandler(this);
        serviceProviders = db.getAllSP();   //get all service providers from DB
        ratedServiceProviders = new ArrayList<ServiceProvider>();    //initialize list of rated service providers

        hO = (User) getIntent().getSerializableExtra("hO");

        ListView listView = (ListView) findViewById(R.id.serviceProviders);

        List<ServiceProvider> serviceProviders = db.getAllSP();

        if (serviceProviders == null) {
            Toast.makeText(this, "There are no service providers", Toast.LENGTH_LONG).show();
        }

        //interate through all service providers, and store if the homeowner has booked them
        for (int i = 0; i < serviceProviders.size(); i++) {

            if (db.isRateable(serviceProviders.get(i).getUsername(), serviceProviders.get(i).getPassword(), hO.getUsername(), hO.getPassword())) {
                ratedServiceProviders.add(serviceProviders.get(i));
            }
        }

        //populate listView if there are rateable service providers
        if (ratedServiceProviders.size() != 0) {
            ServiceProviderAdapter spAdapter = new ServiceProviderAdapter(this, ratedServiceProviders);
            listView.setAdapter(spAdapter);
        }
        else{
            Toast.makeText(this, "You must receive a service to rate a service provider!", Toast.LENGTH_LONG).show();
        }

        //add listeners
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int position, long id) {

                sp = (ServiceProvider) adapterView.getItemAtPosition(position);    //service provider at selected position
                ratePrompt(findViewById(android.R.id.content));
            }
        });
    }


    public void ratePrompt (View view){

        AlertDialog.Builder delMsg = new AlertDialog.Builder(this);
        delMsg.setMessage("Are you sure you want to rate this service provider?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int choice){
                        switchToSetRating();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int choice){
                        dialog.cancel();
                    }
                });
        AlertDialog delConfirm = delMsg.create();
        delConfirm.setTitle("Rate a Service Provider");
        delConfirm.show();
    }

    private void switchToSetRating(){
        Intent i = new Intent(this, SetProviderRating.class);
        i.putExtra("sp",sp);
        i.putExtra("hO",hO);
        startActivity(i);
    }
}
