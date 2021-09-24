package com.metropolia.stayactive;

public class UserProfile {

    private String userName;
    private double weight;
    private double height;
    private double bmi;

    public UserProfile(String userName, double weight, double height) {
        this.userName = userName;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
    }

    public UserProfile (){
        this.userName = userName;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
    }

    public String getUserName() {
        return userName;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getBmi() {
        this.bmi = this.weight / (this.height * this.height);
        return bmi;
    }


    @Override
    public String toString() {
        return userName + weight + height + bmi;
    }
}
