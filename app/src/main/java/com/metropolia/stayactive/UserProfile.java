package com.metropolia.stayactive;

public class UserProfile {

    private String userName;
    private Integer weight;
    private Integer height;
    private float bmi;
    private Integer exerciseGoal;

    public UserProfile(String userName, int weight, int height, int exerciseGoal) {
        this.userName = userName;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.exerciseGoal = exerciseGoal;
    }

    public UserProfile() {
        this.userName = userName;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getExerciseGoal() {
        return exerciseGoal;
    }

    /*public double getBmi() {
        double height = this.height*1.0/100;
        this.bmi = (this.weight*1.0/(height*height));
        return this.bmi;
    }*/

    public float getBmi(){
        float bmi;
        bmi = (float) (this.weight*1.0/((height*1.0/100)*(height*1.0/100)));
        return bmi;
    }



    @Override
    public String toString() {
        return userName + weight + height + bmi + exerciseGoal;
    }
}
