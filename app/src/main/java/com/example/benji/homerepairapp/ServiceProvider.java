package com.example.benji.homerepairapp;

import com.example.benji.homerepairapp.User;

public class ServiceProvider extends User {

    private String company;
    private String description;
    private boolean license;
    private String[] times = new String[13];

    public ServiceProvider(String username, String password, String fName, String lName, String email, String phone, String address) {
        super(username, password, fName, lName, email, phone, address);
    }

    public void additionalInfo(String company, String description, boolean license, String[] newTimes){
        this.company = company;
        this.description = description;
        this.license = license;

        for (int i =0; i<newTimes.length; i++){
            times[i] = newTimes[i];
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
}
