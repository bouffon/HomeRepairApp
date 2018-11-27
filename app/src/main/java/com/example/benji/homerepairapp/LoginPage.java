package com.example.benji.homerepairapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

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

        //add default admin
        if (db.findUser("admin", "admin") == null){
            Admin user = new Admin("admin", "admin", "admin", "admin", "admin@admin.ca", "9059059055", "69 Admin place");
            db.addUser(user);
        }

        //add default service provider
        if (db.findUser("guy", "guy") == null){
            ServiceProvider user = new ServiceProvider("guy", "guy", "guy", "guy", "guy@guy.ca", "9059059055", "69 Admin place");
            db.addUser(user);
            db.addSPInfo("guy","guy","defaultCompany","defaultDescription",true,"__:__","__:__","__:__",
                    "__:__","__:__","__:__","__:__","__:__","__:__","__:__","__:__","__:__",
                    "__:__","__:__");

        }
    }

    public void login(View view){
        DBHandler db = new DBHandler(this);

        User activeUser = db.findUser(usernameBox.getText().toString(),passwordBox.getText().toString());

        if (activeUser != null) {
            if (usernameBox.getText().toString().equals("admin") && passwordBox.getText().toString().equals("admin")){
                Intent intent = new Intent(this, AdminNav.class);
                intent.putExtra("admin", activeUser);
                startActivity(intent);
            }
            else if(activeUser.getClass() == ServiceProvider.class){
                Intent intent = new Intent(this, ServiceProviderNav.class);
                intent.putExtra("sp", activeUser);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this, HomeOwnerNav.class);
                intent.putExtra("hm", activeUser);
                startActivity(intent);
            }
        }

    }

    public void createAccount(View view){
        Intent intent = new Intent(this, AccountCreation.class);
        startActivity(intent);
    }
}
