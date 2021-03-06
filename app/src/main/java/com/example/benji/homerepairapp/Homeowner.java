package com.example.benji.homerepairapp;

import com.example.benji.homerepairapp.User;

/**
 The Homeowner Class represents a user in the system that can book a service provider
 */
public class Homeowner extends User {

    public Homeowner(String username, String password, String fName, String lName, String email, String phone, String address) {
        super(username, password, fName, lName, email, phone, address);
    }
}
