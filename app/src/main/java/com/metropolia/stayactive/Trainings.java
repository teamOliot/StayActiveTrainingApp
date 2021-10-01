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

    // get one training based on its index value
    public Training getTraining (int i) {
        Training training = this.trainings.get(i);
        return training;
    }

    // delete one training based on its index value
    public static void deleteTraining (int i) {
        Trainings.getInstance().getTrainings().remove(i);
    }

    // because Training class implements Comparable interface you can sort Trainings (list) based on dates
    public static void sortDates () {
        // Sort original list based on Training class compareTo method
        Collections.sort(Trainings.getInstance().getTrainings());
    }

    // method for checking if user has achieved date's exercise goal in minutes
    // latestAddedTrainingDate is Training object which contains the comparing training date
    public static int countDatesMinutes (Training latestAddedTrainingDate) {
        int datesTrainingMinutes = 0;
        for (Training training : Trainings.getInstance().getTrainings()) {
            // if returnValue is 0 the dates are same
            int returnValue = training.compareTo(latestAddedTrainingDate);
            if (returnValue == 0) {
                datesTrainingMinutes += training.getTrainingLength();
            }
        }
        return datesTrainingMinutes;
    }

}
