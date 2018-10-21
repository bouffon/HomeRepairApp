package com.example.benji.homerepairapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.view.View;

public class AccountCreation extends AppCompatActivity {

    EditText fNameBox;
    EditText lNameBox;
    EditText usernameBox;
    EditText passwordBox;
    EditText emailBox;
    EditText phoneBox;
    Switch serviceProviderSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);
        fNameBox = findViewById(R.id.firstNameInput);
        lNameBox = findViewById(R.id.lastNameInput);
        usernameBox = findViewById(R.id.usernameInput);
        passwordBox = findViewById(R.id.passwordInput);
        emailBox = findViewById(R.id.emailInput);
        phoneBox = findViewById(R.id.phoneInput);
        serviceProviderSwitch = findViewById(R.id.serviceProviderSwitch);
    }

    public void newUser(View view){
        String fName = fNameBox.getText().toString();
        String lName = lNameBox.getText().toString();
        String username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        String email = emailBox.getText().toString();
        String phone = phoneBox.getText().toString();
        Boolean switchResult = serviceProviderSwitch.isChecked();

        if (switchResult) { //depending on if switch is flipped creates Homeowner vs ServiceProvider
            ServiceProvider user = new ServiceProvider(username,password,fName,lName,email,phone);
        } else {
            Homeowner user = new Homeowner(username,password,fName,lName,email,phone);
        }
        //TODO still need to implement database! (add the created account to the database) ALSO need to check to see if account already exists!
    }

    public void cancel(View view){
        //TODO provide implementation for canceling account creation and returning to login screen
    }

}
