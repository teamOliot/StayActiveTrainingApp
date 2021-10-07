package com.metropolia.stayactive;

/** A class for user profile
 * @author Katja Dahlman
 * @version 10/2021
 */
public class UserProfile {

    private String userName;
    private Integer weight;
    private Integer height;
    private Integer exerciseGoal;
    /** Class variables and constructor for user profile with info user has provided
     * @author Katja Dahlman
     * @param userName nickname for user
     * @param weight users current weight
     * @param height users height
     * @param exerciseGoal exercise goal that user has decided to for themselves.
     */

    public UserProfile(String userName, int weight, int height, int exerciseGoal) {
        this.userName = userName;
        this.weight = weight;
        this.height = height;
        this.exerciseGoal = exerciseGoal;
    }

    /** Gets users nickname
     * @author Katja Dahlman
     * @return A string value containing users nickname
     */
    public String getUserName() {
        return userName;
    }

    /** Gets users weight
     * @author Katja Dahlman
     * @return A integer value containing users weight
     */
    public Integer getWeight() {
        return weight;
    }

    /** Gets users height
     * @author Katja Dahlman
     * @return A integer containing users height
     */
    public Integer getHeight() {
        return height;
    }

    /** Gets users exercise goal
     * @author Katja Dahlman
     * @return A integer containing users exercise goal
     */
    public Integer getExerciseGoal() {
        return exerciseGoal;
    }

    /** Method for getting and calculating users body mass index
     * @author Katja Dahlman
     * @return A integer calculated from weight and height values user gives. First counted in float and then returned as rounded to integer.
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
