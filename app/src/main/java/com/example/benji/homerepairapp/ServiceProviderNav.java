package com.example.benji.homerepairapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class ServiceProviderNav extends AppCompatActivity {

    private TextView mTextMessage;
    private User sp;
    private Bundle args = new Bundle();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

/*            Intent i = getIntent();
            sp = (ServiceProvider) i.getSerializableExtra("sp");
            sp.getUsername();

            //put Service provider's username and password into the bundle
            String [] spLogin = {sp.getUsername(),sp.getPassword()};
            args.putStringArray("sp", spLogin);*/

            switch (item.getItemId()) {
                case R.id.snav_services:
                    selectedFragment = new SPServices(); //create an instance of SPServices to create the list of services currently offered
                    selectedFragment.setArguments(args);
                    break;

                case R.id.snav_addService:
                    selectedFragment = new SPAddService();
                    selectedFragment.setArguments(args);
                    break;

                case R.id.snav_hours:
                    selectedFragment = new EditSPHours();
                    selectedFragment.setArguments(args);
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();  //runs program functionality in fragment container

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent i = getIntent();
        sp = (ServiceProvider) i.getSerializableExtra("sp");

        sp.getUsername();

        //put Service provider's username and password into the bundle
        String [] spLogin = {sp.getUsername(),sp.getPassword()};
        args.putStringArray("sp", spLogin);

        Fragment defaultFragment =  new SPServices();
        defaultFragment.setArguments(args);

        //automatically go to the SPServices fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.container, defaultFragment).commit();

    }

}
