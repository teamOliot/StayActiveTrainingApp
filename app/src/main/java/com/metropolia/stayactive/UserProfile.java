package com.metropolia.stayactive;

public class UserProfile {

    private String userName;
    private Integer weight;
    private Integer height;
    private Integer exerciseGoal;

    public UserProfile(String userName, int weight, int height, int exerciseGoal) {
        this.userName = userName;
        this.weight = weight;
        this.height = height;
        this.exerciseGoal = exerciseGoal;
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


    public Integer getBmi(){
        float bmi;
        bmi = (float) (this.weight*1.0/((height*1.0/100)*(height*1.0/100)));
        bmi=Math.round(bmi);
        int returnBmi = (int) bmi;
        return returnBmi;
    }



    @Override
    public String toString() {
        return userName + weight + height + exerciseGoal;
    }
}
