package com.example.benji.homerepairapp;

public class Service {

    private double rate;
    private String serviceName;

    public Service(double rate, String serviceName){
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
