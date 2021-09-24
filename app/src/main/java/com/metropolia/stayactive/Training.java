package com.metropolia.stayactive;

import java.time.LocalDate;

public class Training {
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

    /*
    public LocalDate trainingDate() {
        LocalDate trainingDate = LocalDate.of(this.year, this.month, this.day);
        return trainingDate;
    }
    */

    @Override
    public String toString() {
        return this.trainingType;
    }

}
