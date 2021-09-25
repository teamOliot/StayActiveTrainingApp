package com.metropolia.stayactive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trainings {

    private List<Training> trainings;
    private static final Trainings ourInstance = new Trainings();

    public static Trainings getInstance() {
        return ourInstance;
    }

    private Trainings() {
        trainings = new ArrayList<>();
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public Training getTraining (int i) {
        Training training = this.trainings.get(i);
        return training;
    }

    public static void deleteTraining (int i) {
        Trainings.getInstance().getTrainings().remove(i);
    }

    //because Training class implements Comparable interface you can sort Trainings based on dates
    public static void sortDates () {
        Collections.sort(Trainings.getInstance().getTrainings());
    }

}
