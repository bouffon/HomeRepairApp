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

public class SPServices extends Fragment {

    DBHandler db;
    String SPUsername, SPPassword;
    ServiceProvider sp;
    String service;

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

        v = inflater.inflate(R.layout.activity_spservices, container, false);

        Bundle bundle = getArguments();
        String [] spLoginArray = bundle.getStringArray("sp");
        SPUsername = spLoginArray[0];
        SPPassword = spLoginArray[1];

        ListView listView = (ListView) v.findViewById(R.id.servicesOffered);   //listView for all services
        DBHandler db = new DBHandler(getActivity());

        sp = (ServiceProvider) db.findUser(SPUsername, SPPassword);

        ArrayList<Service> servicesOffered = new ArrayList<>(); //ArrayList to store Service objects

        //check to see if there are no services
        if(sp.getServices() == null) {
            Toast.makeText(getActivity(), "You do not currently offer services", Toast.LENGTH_LONG).show();
        } else {
            for(int i = 0; i < sp.getServices().size(); i++){

                servicesOffered.add(sp.getServices().get(i));
                ServiceAdapter sAdapter = new ServiceAdapter(getActivity(), servicesOffered);
                listView.setAdapter(sAdapter);
            }

            //add listeners
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, final View view, int position, long id) {
                    Service s = (Service) adapterView.getItemAtPosition(position);    //service at selected position
                    service = s.getServiceName();
                    deletePrompt(v);    //run delete algorithm

                    //create an intent for the selected service so it can be edited
                    Intent launchServiceProviderNav = new Intent(getActivity().getApplicationContext(), ServiceProviderNav.class);
                    startActivity(launchServiceProviderNav);
                }
            });

        }

        return v;
    }

    public void deleteServiceOffered(){
        DBHandler db = new DBHandler(getActivity());

        db.deleteSPService(SPUsername, SPPassword,service);

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
