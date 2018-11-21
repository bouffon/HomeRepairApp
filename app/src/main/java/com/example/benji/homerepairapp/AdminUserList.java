package com.example.benji.homerepairapp;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminUserList extends Fragment {

    DBHandler dB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        View v = inflater.inflate(R.layout.activity_admin_user_list,container,false);

        ListView listView = (ListView) v.findViewById(R.id.listView);
        dB = new DBHandler(getActivity());

        ArrayList<String> userList = new ArrayList<>();
        Cursor data = dB.getDBContents();

        if(data.getCount() == 0){
            Toast.makeText(getActivity(), "There are no users in the list",Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                userList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, userList);
                listView.setAdapter(listAdapter);
            }
        }
        return v;
    }



}
