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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SPServices extends Fragment {

    DBHandler db;
    String service;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.activity_spservices, container, false);

        ListView listView = (ListView) v.findViewById(R.id.servicesOffered);   //listView for all services
        DBHandler db = new DBHandler(getActivity());

        ArrayList<Service> serviceList = new ArrayList<>(); //ArrayList to store Service objects
        Cursor data = db.getDBContents();

        //check to see if there are no services
        if(data.getCount() == 0) {
            Toast.makeText(getActivity(), "You do not currently offer services", Toast.LENGTH_LONG).show();
        }

        //populate listView and add listeners to each item in the list
        else{
            while(data.moveToNext()){

                serviceList.add(new Service(data.getString(1), data.getDouble(2)));
                ServiceAdapter sAdapter = new ServiceAdapter(getActivity(), serviceList);
                listView.setAdapter(sAdapter);
            }

            //add listeners
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, final View view, int position, long id) {
                    Service s = (Service) adapterView.getItemAtPosition(position);    //service at selected position
                    service = s.getServiceName();

                    //create an intent for the selected service so it can be edited
                    Intent launchServiceEditor = new Intent(getActivity().getApplicationContext(), ServiceEditor.class);
                    launchServiceEditor.putExtra("service",s.getServiceName());
                    launchServiceEditor.putExtra("hourly rate", s.getRate());
                    startActivity(launchServiceEditor);
                }
            });
        }

        return v;
    }

    public void deleteServiceOffered(){
        DBHandler db = new DBHandler(getActivity());

        db.deleteService(service);

        Intent serviceManager = new Intent(getActivity(), ServiceProviderNav.class);
        startActivity(serviceManager);
    }

    public void deletePrompt (View view){

        AlertDialog.Builder delMsg = new AlertDialog.Builder(getActivity());
        delMsg.setMessage("Are you sure you no longer want to offer this service?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int choice){
                        deleteServiceOffered();
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
