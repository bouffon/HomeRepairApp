package com.example.benji.homerepairapp;

public class User {

    private String username;
    private String password;
    private String fName;
    private String lName;
    private String email;
    private String phone;

    public User(String username, String password, String fName, String lName, String email, String phone){
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername(){ return username;}
    public String getPassword(){ return password;}
    public String getfName(){ return fName;}
    public String getlName(){ return lName;}
    public String getEmail(){ return email;}
    public String getPhone(){ return phone;}

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setfName(String fName){
        this.fName = fName;
    }
    public void setlName(String lName){
        this.lName = lName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

}
