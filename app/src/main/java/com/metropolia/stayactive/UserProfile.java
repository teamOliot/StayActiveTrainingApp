package com.metropolia.stayactive;

/**
 * A class for user profile
 * @author Katja Dahlman
 * @version 10/2021
 */
public class UserProfile {
    private String userName;
    private Integer weight;
    private Integer height;
    private Integer exerciseGoal;

    /**
     * Class variables and constructor for user profile with info user has provided
     * @author Katja Dahlman
     * @param userName nickname for user
     * @param weight user's current weight
     * @param height user's height
     * @param exerciseGoal exercise goal that user has decided to for themselves.
     */
    public UserProfile(String userName, int weight, int height, int exerciseGoal) {
        this.userName = userName;
        this.weight = weight;
        this.height = height;
        this.exerciseGoal = exerciseGoal;
    }

    /**
     * Gets user's nickname
     * @return A string value containing user's nickname
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets user's weight
     * @return An integer value containing user's weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * Gets user's height
     * @return An integer containing user's height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Gets user's exercise goal
     * @return An integer containing user's exercise goal
     */
    public Integer getExerciseGoal() {
        return exerciseGoal;
    }

    /**
     * Method for getting and calculating user's body mass index
     * @return An integer calculated from weight and height values user gives. First counted in float and then returned as rounded to integer.
     */
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
