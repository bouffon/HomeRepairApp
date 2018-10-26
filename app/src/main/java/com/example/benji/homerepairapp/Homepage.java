package com.example.benji.homerepairapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class Homepage extends AppCompatActivity {

    TextView status;
    User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        status = findViewById(R.id.statusDisplay);
        Intent i = getIntent();
        activeUser = (User)i.getSerializableExtra("ActiveUser");
        status.setText("Welcome " + activeUser.getUsername() + " you are logged in as " + activeUser.getClass().toString().substring(activeUser.getClass().toString().lastIndexOf('.')+1));
    }
}
