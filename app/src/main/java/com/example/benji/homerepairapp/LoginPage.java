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
        DBHandler db = new DBHandler(this);
        Admin admin = new Admin("admin","admin",null,null,null,null);
        db.addUser(admin);
    }

    public void login(View view){
        DBHandler db = new DBHandler(this);
        User activeUser = db.findUser(usernameBox.getText().toString(),passwordBox.getText().toString());

        if (activeUser != null) {
            Intent intent = new Intent(this, Homepage.class);
            intent.putExtra("ActiveUser", activeUser);
            startActivity(intent);
        }

    }

    public void createAccount(View view){
        Intent intent = new Intent(this, AccountCreation.class);
        startActivity(intent);
    }
}
