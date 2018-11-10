package com.example.benji.homerepairapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminServiceManager extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_service_manager);
        ListView listView = (ListView) findViewById(R.id.servicesList);
        ServiceDBHandler db = new ServiceDBHandler(this);

        ArrayList<String> serviceList = new ArrayList<>();
        Cursor data = db.getDBContents();

        //check to see is there are no services
        if(data.getCount() == 0) {
            Toast.makeText(this, "There are no services", Toast.LENGTH_LONG).show();
        }

        //populate listView and add listeners to each item in the list
        else{
                while(data.moveToNext()){   //populate
                    serviceList.add(("" + data.getString(1) + " (" + data.getDouble(2) + " $/hr )"));
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviceList);
                    listView.setAdapter(listAdapter);
                }

                //add listeners
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, final View view, int position, long id) {
                        String service = adapterView.getItemAtPosition(position).toString();    //service at clicked position
                        Intent launchServiceEditor = new Intent(getApplicationContext(), ServiceEditor.class);
                        launchServiceEditor.putExtra("serviceName",service );
                        startActivity(launchServiceEditor);
                    }
                });
            }
    }

    public void createService(View view){
        Intent intent = new Intent(this, ServiceCreation.class);
        startActivity(intent);
    }

    //TODO add list implementation and functionality to the delete and edit buttons.

}
