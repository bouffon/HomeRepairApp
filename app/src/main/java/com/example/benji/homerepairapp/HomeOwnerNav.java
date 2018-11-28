package com.example.benji.homerepairapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeOwnerNav extends AppCompatActivity {

    private TextView mTextMessage;
    private Homeowner hm;
    private Bundle args = new Bundle();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.hnav_searchByService:
                    selectedFragment = new SearchByService(); //create an instance of SPServices to create the list of services currently offered
                    //selectedFragment.setArguments(args);
                    break;

                case R.id.hnav_searchByRating:
                    selectedFragment = new SearchByRating();
                    //selectedFragment.setArguments(args);
                    break;

                case R.id.hnav_searchByHours:
                    selectedFragment = new SearchByHours(); //since edit hours doesn't work lol
                    //selectedFragment.setArguments(args);
                    break;

                case R.id.hnav_rate:
                    selectedFragment = new RateProvider(); //since edit hours doesn't work lol
                    //selectedFragment.setArguments(args);
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();  //runs program functionality in fragment container

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent i = getIntent();
        hm = (Homeowner) i.getSerializableExtra("hm");

        //put Service provider's username and password into the bundle
        /*String [] hmLogin = {hm.getUsername(),hm.getPassword()};
        args.putStringArray("hm", hmLogin);*/

        Fragment defaultFragment =  new SearchByService();
        //defaultFragment.setArguments(args);

        //automatically go to the SPServices fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.container, defaultFragment).commit();

    }
}
