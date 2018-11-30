package com.example.benji.homerepairapp;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RateProvider extends AppCompatActivity {

    DBHandler dB;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_provider);

        ListView listView = (ListView) findViewById(R.id.serviceProviders);
        dB = new DBHandler(this);

        List<ServiceProvider> serviceProviders = dB.getAllSP();

        if(serviceProviders == null){
            Toast.makeText(this, "There are no service providers",Toast.LENGTH_LONG).show();
        }
        else{
            List<String> serviceProviderList = new ArrayList<String>();

            for(int i = 0; i <serviceProviders.size();i++){
                serviceProviderList.add(serviceProviders.get(i).getfName() + " " + serviceProviders.get(i).getlName());
            }
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviceProviderList);
            listView.setAdapter(listAdapter);
        }
    }
}
