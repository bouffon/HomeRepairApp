package com.example.benji.homerepairapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SearchByRating extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    double selectedRating;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_rating);

        ArrayList<Integer> ratings = new ArrayList<Integer>();

        for (int i = 1; i < 6; i++){
            ratings.add(i);
        }

        Spinner spinner = (Spinner) findViewById(R.id.ratingSpinner);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, ratings );
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        selectedRating = Double.parseDouble(spinner.getSelectedItem().toString());


        View searchHours = findViewById(R.id.searchFromRating);
        searchHours.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                searchServiceByRating(v);
            }
        });
    }

    public void searchServiceByRating(View view){
        Intent launchServiceList = new Intent(this, ScheduleService.class);
        launchServiceList.putExtra("rating", selectedRating);
        launchServiceList.putExtra("searchType", "rating");
        startActivity(launchServiceList);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
