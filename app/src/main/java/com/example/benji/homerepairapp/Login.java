package com.example.benji.homerepairapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;

public class Login extends AppCompatActivity {

    EditText usernameBox;
    EditText passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameBox = findViewById(R.id.usernameLogin);
        passwordBox = findViewById(R.id.passwordLogin);
    }

    public void login(View view){
    }

    public void createAccount(View view){
    }
    //TODO LITERALLY EVERYTHING! Need to search DB to see if username and password belong to an account, and action depending on if there is
    //TODO or not. Also provide implementation of create account button linking to create account activity.
}
