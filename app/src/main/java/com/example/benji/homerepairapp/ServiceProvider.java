package com.example.benji.homerepairapp;

import android.util.Log;

import com.example.benji.homerepairapp.User;

import java.util.ArrayList;
import java.util.List;

public class ServiceProvider extends User {

    private String company;
    private String description;
    private boolean license;
    private String[] times = new String[14];
    private String [] services;
    private double rating;

    public ServiceProvider(String username, String password, String fName, String lName, String email, String phone, String address) {
        super(username, password, fName, lName, email, phone, address);
    }

    public void additionalInfo(String company, String description, boolean license, String[] newTimes, ArrayList<Service> newServices, double rating) {
        this.company = company;
        this.description = description;
        this.license = license;
        this.rating = rating;

        if (newServices != null) {
            services = new String [newServices.size()];

            for (int i = 0; i < newTimes.length; i++) {
                times[i] = newTimes[i];
            }
            for (int i = 0; i < newServices.size(); i++) {
                services[i] = newServices.get(i).getServiceName();
            }
        }
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public boolean getLicense() {
        return license;
    }

    public String[] getTimes() {
        return times;
    }

    public double getRating() {
        return rating;
    }

    public String[] getServices(){
        return services;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }

    public void setTimes(String[] newTimes){
        for (int i =0; i<newTimes.length; i++){
            times[i] = newTimes[i];
        }
    }

    public void setServices(ArrayList<Service> newServices){
        for (int i = 0; i<newServices.size(); i++){
            services [i] = newServices.get(i).getServiceName();
        }
    }
}
