package com.example.benji.homerepairapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminUserList extends AppCompatActivity {

    DBHandler dB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_list);

        ListView listView = (ListView) findViewById(R.id.listView);
        dB = new DBHandler(this);

        ArrayList<String> userList = new ArrayList<>();
        Cursor data = dB.getDBContents();

        if(data.getCount() == 0){
            Toast.makeText(this, "There are no users in the list",Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                userList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
                listView.setAdapter(listAdapter);
            }
        }
    }


}
