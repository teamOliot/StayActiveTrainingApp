package com.metropolia.stayactive;

/**
 * A Class for Training
 * Implements Comparable interface to compare objects
 * @author Iina Laamo
 * @author Henrik Lappi
 * @version 10/2021
 */
public class Training implements Comparable<Training> {
    private String trainingType;
    private int trainingLength;
    private int year;
    private int month;
    private int day;

    /**
     * Class variables and constructor for Training with info user has provided
     * @param trainingType for example "juoksu" or "kuntosali"
     * @param trainingLength in minutes
     * @param year of training date
     * @param month of training date
     * @param day of training date
     */
    public Training (String trainingType, int trainingLength, int year, int month, int day) {
        this.trainingType = trainingType;
        this.trainingLength = trainingLength;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Gets and returns trainingType
     * @return String trainingType
     */
    public String getTrainingType() {
        return this.trainingType;
    }

    /**
     * Gets and returns trainingLength
     * @return int trainingLength
     */
    public int getTrainingLength() {
        return this.trainingLength;
    }

    /**
     * Gets and returns year
     * @return int year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Gets and returns month
     * @return int month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Gets and returns day
     * @return int day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Forms and returns trainingDate based on this.year, this.month, this.day
     * Formats dayValue and month value to always contain two characters
     * @return String formed trainingDate
     */
    public String trainingDate() {
        String dayValue = Integer.toString(this.day);
        String monthValue = Integer.toString(this.month);
        // Format dayValue always containing two characters
        if (this.day < 10) {
            dayValue = "0" + dayValue;
        }
        // Format monthValue always containing two characters
        if (this.month < 10) {
            monthValue = "0" + monthValue;
        }
        return dayValue + "." + monthValue + "." + this.year;
    }

    @Override
    public String toString() {
        return trainingDate() + " " + this.trainingType;
    }

    /**
     * compareTo method compares trainings dates (int year, int month, int day)
     * With Comparable interface's compareTo method you can create your own compare rules
     * @return int returnValue
     */
    @Override
    public int compareTo(Training training) {
        // Compares years of two training and returns comparing value (0, 1, -1)
        int returnValue = comparator(this.year, training.getYear());
        // If other year value is greater than other, return -1 or 1
        if (returnValue != 0) {
            return returnValue;
        }
        // If returnValue is 0 (years have the same values), continue comparing values of months
        returnValue = comparator(this.month, training.getMonth());
        // If other month value is greater than other, return -1 or 1
        if (returnValue != 0) {
            return returnValue;
        }
        // If returnValue is 0 (months have the same values), continue comparing values of days
        returnValue = comparator(this.day, training.getDay());
        // Return 0, 1 or -1
        return returnValue;
    }

    /**
     * Compares int type numbers
     * Helps compareTo method
     * @param firstNumber
     * @param secondNumber
     * @return int value (-1, 0 or 1)
     */
    public int comparator (int firstNumber, int secondNumber) {
        if (firstNumber == secondNumber) {
            return 0;
        } else if (firstNumber < secondNumber) {
            return 1;
        } else {
            return -1;
        }
    }
}