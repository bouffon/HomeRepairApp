package com.example.benji.homerepairapp;

import android.accounts.Account;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {

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
        User activeUser = new Admin("admin","admin","admin","admin","admin","admin");

        Intent intent = new Intent(this, Homepage.class);
        intent.putExtra("ActiveUser", activeUser);
        startActivity(intent);
    }

    public void createAccount(View view){
        Intent intent = new Intent(this, AccountCreation.class);
        startActivity(intent);
    }
    //TODO LITERALLY EVERYTHING! Need to search DB to see if username and password belong to an account, and action depending on if there is or not
}
