package com.metropolia.stayactive;

// with comparable interface you can compare objects
public class Training implements Comparable<Training> {
    private String trainingType;
    private int trainingLength;
    private int year;
    private int month;
    private int day;

    public Training (String trainingType, int trainingLength, int year, int month, int day) {
        this.trainingType = trainingType;
        this.trainingLength = trainingLength;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getTrainingType() {
        return this.trainingType;
    }

    public int getTrainingLength() {
        return this.trainingLength;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

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

    // With Comparable interface's compareTo method you can create your own compare rules
    // This compareTo method comparing trainings dates (int year, int month, int day)
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

    // Method for helping compareTo method
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