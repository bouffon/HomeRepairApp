package com.example.benji.homerepairapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        //creates a default homeowner
        if (db.findUser("bob", "bob") == null){
            Homeowner user = new Homeowner("bob", "bob", "bob", "bob", "bob@bob.ca", "9059059055", "bob");
            db.addUser(user);
        }
        //add default service provider 1
        if (db.findUser("guy", "guy") == null){
            ServiceProvider user = new ServiceProvider("guy", "guy", "guy", "guy", "guy@guy.ca", "9059059055", "69 Admin place");
            db.addUser(user);
            db.addSPInfo("guy","guy","defaultCompany","defaultDescription",true,"10 : 00","18 : 00","08 : 00",
                    "23 : 00","__ : __","__ : __","__ : __","__ : __","__ : __","__ : __","__ : __","__ : __",
                    "__ : __","__ : __");
            db.updateRating("guy","guy", 3,"");
        }
        //add default service provider 2
        if (db.findUser("jim", "jim") == null){
            ServiceProvider user = new ServiceProvider("jim", "jim", "jim", "michael", "jim@jim.ca", "9059059055", "12 jim place");
            db.addUser(user);
            db.addSPInfo("jim","jim","defaultCompany","defaultDescription",true,"11 : 00","12 : 00","13 : 00",
                    "23 : 00","__ : __","__ : __","14 : 00","15 : 30","__ : __","__ : __","__ : __","__ : __",
                    "__ : __","__ : __");
            db.updateRating("jim","jim", 2,"");
        }
        //add default service provider 3
        if (db.findUser("kate", "kate") == null){
            ServiceProvider user = new ServiceProvider("kate", "kate", "kate", "timmins", "kate@kate.ca", "9059059055", "22 kim place");
            db.addUser(user);
            db.addSPInfo("kate","kate","defaultCompany","defaultDescription",true,"09 : 00","12 : 00","17 : 00",
                    "20 : 00","__ : __","__ : __","14 : 00","15 : 30","__ : __","__ : __","__ : __","__ : __",
                    "06 : 00","12 : 00");
            db.updateRating("kate","kate", 4,"");
        }
        //add default services
        if (db.findService("Roofing") == null) {
            Service s = new Service("Roofing", 25);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
            db.addSPService("jim", "jim", s.getServiceName());
        }
        if (db.findService("Plumbing") == null) {
            Service s = new Service("Plumbing", 29.75);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
            db.addSPService("kate", "kate", s.getServiceName());
        }
        if (db.findService("Appliance Repair") == null) {
            Service s = new Service("Appliance Repair", 35);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
        }
        if (db.findService("Landscaping") == null) {
            Service s = new Service("Landscaping", 21.50);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
            db.addSPService("jim", "jim", s.getServiceName());
        }
        if (db.findService("Gutter Cleaning") == null) {
            Service s = new Service("Gutter Cleaning", 23);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
        }
        if (db.findService("Painting") == null) {
            Service s = new Service("Painting", 22.25);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
            db.addSPService("kate", "kate", s.getServiceName());
        }
        if (db.findService("Mold Remediation") == null) {
            Service s = new Service("Mold Remediation", 33.25);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
        }
        if (db.findService("Pool Maintenance") == null) {
            Service s = new Service("Pool Maintenance", 27.45);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
        }
        if (db.findService("Extermination") == null) {
            Service s = new Service("Extermination", 24.99);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
            db.addSPService("kate", "kate", s.getServiceName());
        }
        if (db.findService("Electrical Work") == null) {
            Service s = new Service("Electrical Work", 24.99);
            db.addService(s);
            db.addSPService("guy", "guy", s.getServiceName());
            db.addSPService("jim", "jim", s.getServiceName());
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
                Intent intent = new Intent(this, HomeOwnerPage.class);
                intent.putExtra("hO", activeUser);
                startActivity(intent);
            }
        }

    }

    public void createAccount(View view){
        Intent intent = new Intent(this, AccountCreation.class);
        startActivity(intent);
    }
}
