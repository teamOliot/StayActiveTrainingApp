package com.metropolia.stayactive;

public class UserProfile {

    private String userName;
    private Integer weight;
    private Integer height;
    private double bmi;
    private Integer exerciseGoal;

    public UserProfile(String userName, int weight, int height, int exerciseGoal) {
        this.userName = userName;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        ///this.bmi = getBmi();
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

    public double getBmi() {
        this.bmi = this.weight / ((this.height/100) * (this.height/100));
        return bmi;
    }



    @Override
    public String toString() {
        return userName + weight + height + bmi + exerciseGoal;
    }
}
