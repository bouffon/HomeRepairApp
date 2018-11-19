package com.example.benji.homerepairapp;

import com.example.benji.homerepairapp.User;

import java.util.List;

public class ServiceProvider extends User {

    private String company;
    private String description;
    private boolean license;
    private String[] times = new String[13];
    private Service[] services = new Service[4];

    public ServiceProvider(String username, String password, String fName, String lName, String email, String phone, String address) {
        super(username, password, fName, lName, email, phone, address);
    }

    public void additionalInfo(String company, String description, boolean license, String[] newTimes, Service[] newServices){
        this.company = company;
        this.description = description;
        this.license = license;

        for (int i =0; i<newTimes.length; i++){
            times[i] = newTimes[i];
        }
        for (int j =0; j<newServices.length; j++){
            times[j] = newTimes[j];
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

    public Service[] getServices(){
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

    public void setServices(String[] newServices){
        for (int i =0; i<newServices.length; i++){
            times[i] = newServices[i];
        }
    }
}
