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

public class RateProvider extends Fragment {

    DBHandler dB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        View v = inflater.inflate(R.layout.activity_rate_provider,container,false);

        ListView listView = (ListView) v.findViewById(R.id.serviceProviders);
        dB = new DBHandler(getActivity());

        List<ServiceProvider> serviceProviders = dB.getAllSP();

        if(serviceProviders == null){
            Toast.makeText(getActivity(), "There are no service providers",Toast.LENGTH_LONG).show();
        }
        else{
            List<String> serviceProviderList = new ArrayList<String>();

            for(int i = 0; i <serviceProviders.size();i++){
                serviceProviderList.add(serviceProviders.get(i).getfName() + " " + serviceProviders.get(i).getlName());
            }
            ListAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, serviceProviderList);
            listView.setAdapter(listAdapter);
        }
        return v;
    }
}
