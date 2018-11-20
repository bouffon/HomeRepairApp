package com.example.benji.homerepairapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SPAddService extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.activity_spadd_service, container, false);

        ListView listView = (ListView) v.findViewById(R.id.addSPServicesList);   //listView for all services
        DBHandler db = new DBHandler(getActivity());

        ArrayList<Service> serviceList = new ArrayList<>(); //ArrayList to store Service objects
        Cursor data = db.getDBContents();

        //check to see if there are no services
        if(data.getCount() == 0) {
            Toast.makeText(getActivity(), "There are no services", Toast.LENGTH_LONG).show();
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
                    addServiceOffered(s);

                    //create an intent for the selected service so it can be edited
                    Intent launchServiceEditor = new Intent(getActivity().getApplicationContext(), ServiceProviderNav.class);
                    startActivity(launchServiceEditor);
                }
            });
        }

        return v;
    }

    //TODO FINISH THIS METHOD
    private void addServiceOffered(Service s){

    }
}
