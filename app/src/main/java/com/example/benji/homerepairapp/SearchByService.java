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

public class SearchByService extends AppCompatActivity {

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_service);

        ListView listView = (ListView) findViewById(R.id.servicesOffered);   //listView for all services
        DBHandler db = new DBHandler(this);

        ArrayList<Service> serviceList = new ArrayList<Service>(); //ArrayList to store Service objects
        Cursor data = db.getServiceContents();

        //check to see if there are no services
        if(data.getCount() == 0) {
            Toast.makeText(this, "There are no services to search", Toast.LENGTH_LONG).show();
        }

        //populate listView and add listeners to each item in the list
        else{
            while(data.moveToNext()){

                serviceList.add(new Service(data.getString(1), data.getDouble(2)));
                ServiceAdapter sAdapter = new ServiceAdapter(this, serviceList);
                listView.setAdapter(sAdapter);
            }

            //add listeners
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, final View view, int position, long id) {
                    Service s = (Service) adapterView.getItemAtPosition(position);    //service at selected position

                    //create an intent for the selected service so it can be edited
                    Intent launchServiceList = new Intent(getApplicationContext(), ScheduleService.class);
                    launchServiceList.putExtra("serviceName", s.getServiceName());
                    launchServiceList.putExtra("searchType","service");
                    startActivity(launchServiceList);
                }
            });
        }
    }
}
