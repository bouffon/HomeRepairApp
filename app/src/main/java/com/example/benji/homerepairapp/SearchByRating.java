package com.example.benji.homerepairapp;

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

public class SearchByRating extends Fragment implements AdapterView.OnItemSelectedListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.activity_search_by_rating, container, false);

        ArrayList<Integer> ratings = new ArrayList<Integer>();

        for (int i = 1; i < 6; i++){
            ratings.add(i);
        }

        Spinner spinner = (Spinner) v.findViewById(R.id.ratingSpinner);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(),R.layout.support_simple_spinner_dropdown_item, ratings );
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
