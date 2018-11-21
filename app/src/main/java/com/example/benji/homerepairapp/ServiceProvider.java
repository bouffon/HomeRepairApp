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
    //private List<Service> services = new ArrayList<Service>();
    private String [] services;

    public ServiceProvider(String username, String password, String fName, String lName, String email, String phone, String address) {
        super(username, password, fName, lName, email, phone, address);
    }

    public void additionalInfo(String company, String description, boolean license, String[] newTimes, List<Service> newServices) {
        this.company = company;
        this.description = description;
        this.license = license;

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

    public void setServices(List<Service> newServices){
        for (int i = 0; i<newServices.size(); i++){
            services [i] = newServices.get(i).getServiceName();
        }
    }
}
