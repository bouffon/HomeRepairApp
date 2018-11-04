package com.example.benji.homerepairapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
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
        if(data.getCount() == 0) {
            Toast.makeText(this, "There are no services", Toast.LENGTH_LONG).show();
        } else{
                while(data.moveToNext()){
                    serviceList.add(data.getString(1));
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, serviceList);
                    listView.setAdapter(listAdapter);
                }
            }
    }

    public void createService(View view){
        Intent intent = new Intent(this, ServiceCreation.class);
        startActivity(intent);
    }

    //TODO add list implementation and functionality to the two buttons.

}
