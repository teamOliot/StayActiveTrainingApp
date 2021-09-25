package com.metropolia.stayactive;

import java.time.LocalDate;

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
        return this.day + ":" + this.month + ":" + this.year;
    }
    /*
    public LocalDate trainingDate() {
        LocalDate trainingDate = LocalDate.of(this.year, this.month, this.day);
        return trainingDate;
    }
    */
    @Override
    public String toString() {
        return trainingDate() + " " + this.trainingType;
    }

    //with Comparable interface's compareTo method you can create your own compare rules
    @Override
    public int compareTo(Training training) {
        int returnValue = comparator(this.year, training.getYear());
        if (returnValue != 0) {
            return returnValue;
        }
        returnValue = comparator(this.month, training.getMonth());
        if (returnValue != 0) {
            return returnValue;
        }
        returnValue = comparator(this.day, training.getDay());
        return returnValue;
    }

    // method for helping compareTo method
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
