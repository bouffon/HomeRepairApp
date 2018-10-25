package com.example.benji.homerepairapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminHomepage extends AppCompatActivity {

    TextView status;
    User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);
        status = findViewById(R.id.adminWelcome);
        Intent i = getIntent();
        activeUser = (User)i.getSerializableExtra("ActiveUser");
        status.setText("Welcome " + activeUser.getUsername() + " you are logged in as " + activeUser.getClass());
    }

    public void userList (View view){
        Intent intent = new Intent(this, AdminUserList.class);
        startActivity(intent);
    }
}