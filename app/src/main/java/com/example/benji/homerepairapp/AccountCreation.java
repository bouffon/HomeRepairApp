package com.example.benji.homerepairapp;

import android.content.Intent;
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
    EditText cPasswordBox;
    EditText addressBox;
    Switch serviceProviderSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);
        fNameBox = findViewById(R.id.firstNameInput);
        lNameBox = findViewById(R.id.lastNameInput);
        usernameBox = findViewById(R.id.usernameInput);
        passwordBox = findViewById(R.id.passwordInput);
        cPasswordBox = findViewById(R.id.cPasswordInput);
        addressBox = findViewById(R.id.addressInput);
        emailBox = findViewById(R.id.emailInput);
        phoneBox = findViewById(R.id.phoneInput);
        serviceProviderSwitch = findViewById(R.id.serviceProviderSwitch);
    }

    // E-MAIL VALIDATION
    private boolean isValidMail(){
        String email = emailBox.getText().toString();

        if (email.isEmpty()) {
            emailBox.setError("Field cannot be empty!");
            return false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailBox.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    // PHONE NUMBER VALIDATION
    private boolean isValidPhone(){
        String phone = phoneBox.getText().toString();

        if(phone.isEmpty()){
            phoneBox.setError("Field cannot be empty!");
            return false;
        }
        else if (!android.util.Patterns.PHONE.matcher(phone).matches() || !(phone.length() == 10)){
            phoneBox.setError("Please enter a valid 10 digit phone number");
            return false;
        }
        return true;
    }

    // IF THE FIRST NAME FIELD IS EMPTY
    private boolean isEmptyFName(){
        String fName = fNameBox.getText().toString();

        if(fName.isEmpty()){
            fNameBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }
    // IF THE LAST NAME FIELD IS EMPTY
    private boolean isEmptyLName(){
        String lName = lNameBox.getText().toString();

        if(lName.isEmpty()){
            lNameBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    // IF THE USERNAME FIELD IS EMPTY
    private boolean isEmptyUsername(){
        String username = usernameBox.getText().toString();

        if(username.isEmpty()){
            usernameBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    // IF THE ADDRESS FIELD IS EMPTY
    private boolean isEmptyAddress(){
        String address = addressBox.getText().toString();

        if(address.isEmpty()){
            addressBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    // IF THE PASSWORD FIELD IS EMPTY
    private boolean isEmptyPassword(){
        String password = passwordBox.getText().toString();

        if(password.isEmpty()){
            passwordBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    // IF THE CONFIRMED PASSWORD IS THE SAME AS THE ORIGINAL TYPED
    private boolean isSamePassword(){
        String cPassword = cPasswordBox.getText().toString();

        if (cPassword.isEmpty()){
            cPasswordBox.setError("Field cannot be empty!");
            return false;
        }
        else if (!(cPassword.equals(passwordBox.getText().toString()))){
            cPasswordBox.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    public void newUser(View view){
        DBHandler db = new DBHandler(this);

        String fName = fNameBox.getText().toString();
        String lName = lNameBox.getText().toString();
        String username = usernameBox.getText().toString();
        String password = passwordBox.getText().toString();
        String email = emailBox.getText().toString();
        String address = addressBox.getText().toString();
        String phone = phoneBox.getText().toString();
        Boolean switchResult = serviceProviderSwitch.isChecked();

        if (!isValidMail() | !isEmptyFName() | !isEmptyLName() | !isEmptyUsername() | !isEmptyPassword() | !isValidPhone() | !isSamePassword() | !isEmptyAddress()){
            return;
        }

        if (db.findUser(username, password) == null) {
            if (switchResult) { //depending on if switch is flipped creates Homeowner vs ServiceProvider
                ServiceProvider user = new ServiceProvider(username, password, fName, lName, email, phone, address);
                db.addUser(user);
                Intent intent = new Intent(this, ServiceProviderInformation.class);
                intent.putExtra("sp", user);
                startActivity(intent);
            } else {
                Homeowner user = new Homeowner(username, password, fName, lName, email, phone, address);
                db.addUser(user);
                Intent intent = new Intent(this, LoginPage.class);
                startActivity(intent);
            }
        }
    }

    public void cancel(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

}
