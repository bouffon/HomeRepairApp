package com.example.benji.homerepairapp;

/**
 The Service class represents a service that can be offered by a service provider, it has a name and an
 hourly rate.
 */
public class Service {

    private double rate;
    private String serviceName;

    public Service(String serviceName, double rate){
        this.rate = rate;
        this.serviceName = serviceName;
    }

    public void setRate(double rate){
        this.rate = rate;
    }

    public double getRate(){
        return rate;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName(){
        return serviceName;
    }
}
