package com.example.benji.homerepairapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SPAddService extends Fragment {

    DBHandler db;
    String SPUsername, SPPassword;
    String service;
    ArrayList<Service> serviceList;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

        v = inflater.inflate(R.layout.activity_spadd_service, container, false);

        Bundle bundle = getArguments();
        String [] spLoginArray = bundle.getStringArray("sp");
        SPUsername = spLoginArray[0];
        SPPassword = spLoginArray[1];

        ListView listView = (ListView) v.findViewById(R.id.addSPServicesList);   //listView for all services
        db = new DBHandler(getActivity());

        serviceList = new ArrayList<>(); //ArrayList to store Service objects
        Cursor data = db.getServiceContents();

        //check to see if there are no services
        if(data.getCount() == 0) {
            Toast.makeText(getActivity(), "There are no services to be offered", Toast.LENGTH_LONG).show();
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
                    ServiceProvider sp = (ServiceProvider) db.findUser(SPUsername,SPPassword);
                    boolean foundService = false;   //if service provider already offers the service

                    for (int i = 0; i < sp.getServices().length; i++){
                        if (sp.getServices()[i].equals(service)){
                            Toast.makeText(getActivity(), "You already offer this service!", Toast.LENGTH_LONG).show();
                            foundService = true;
                            return;
                        }
                    }
                    addPrompt(v);

                    //create an intent for the selected service so it can be edited
                }
            });
        }

        return v;
    }

    public void addSPService() {

        db.addSPService(SPUsername, SPPassword, service);

        Intent serviceManager = new Intent(getActivity(), ServiceProviderNav.class);
        serviceManager.putExtra("sp",db.findUser(SPUsername,SPPassword));
        startActivity(serviceManager);
    }


    public void addPrompt (View view){

        AlertDialog.Builder delMsg = new AlertDialog.Builder(getActivity());
        delMsg.setMessage("Are you sure you want to offer this service?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int choice){
                        addSPService();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int choice){
                        dialog.cancel();
                    }
                });
        AlertDialog delConfirm = delMsg.create();
        delConfirm.setTitle("Add a service");
        delConfirm.show();
    }
}

