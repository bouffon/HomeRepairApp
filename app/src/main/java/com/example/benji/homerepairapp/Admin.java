package com.example.benji.homerepairapp;

import com.example.benji.homerepairapp.User;

/**
 The Admin Class represents a user in the system that can create and edit services, it can also see all
 the users in the system.
 */
public class Admin extends User {

    public Admin(String username, String password, String fName, String lName, String email, String phone, String address) {
        super(username, password, fName, lName, email, phone, address);
    }
}
