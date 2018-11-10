package com.example.benji.homerepairapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class AdminNav extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId()) {

                case R.id.nav_users:
                    selectedFragment = new AdminUserList(); //create an instance of AdminUserList to create the list of users to display
                   // mTextMessage.setText("Users");
                    break;

                case R.id.nav_services:
                    selectedFragment = new AdminServiceManager();

                    //mTextMessage.setText("Services");
                    break;

                case R.id.nav_addService:
                    selectedFragment = new ServiceCreation();

                    //mTextMessage.setText("Add service");
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();  //runs program functionality in fragment container

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //runs user list fragment on startup
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AdminUserList()).commit();

    }

}
