package com.metropolia.stayactive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Singleton class Trainings
 * @author Henrik Lappi
 * @author Iina Laamo
 * @version 10/2021
 */

public class Trainings {
    private List<Training> trainings;
    private static final Trainings ourInstance = new Trainings();

    /**
     * Returns Trainings object
     * @return Trainings ourInstance
     */
    public static Trainings getInstance() {
        return ourInstance;
    }

    private Trainings() {
        trainings = new ArrayList<>();
    }

    /**
     * Returns Trainings object's trainings list
     * @return List<Training> trainings
     */
    public List<Training> getTrainings() {
        return trainings;
    }

    /**
     * Gets and returns one training object based on its index value
     * @param i certain training index value in trainings list
     * @return Training training
     */
    public Training getTraining (int i) {
        Training training = this.trainings.get(i);
        return training;
    }

    /**
     * Deletes one training object based on its index value
     * @param i certain training index value in trainings list
     */
    public static void deleteTraining (int i) {
        Trainings.getInstance().getTrainings().remove(i);
    }

    /**
     * Sorts Trainings (list) based on dates
     * Training class implements Comparable interface which makes sorting possible
     */
    public static void sortDates () {
        // Sort original list based on Training class compareTo method
        Collections.sort(Trainings.getInstance().getTrainings());
    }

    /**
     * Checks if user has achieved date's exercise goal in minutes
     * @param latestAddedTrainingDate is Training object which contains the comparing training date
     * @return int datesTrainingMinutes
     */
    public static int countDatesMinutes (Training latestAddedTrainingDate) {
        int datesTrainingMinutes = 0;
        for (Training training : Trainings.getInstance().getTrainings()) {
            // If returnValue is 0 the dates are same
            int returnValue = training.compareTo(latestAddedTrainingDate);
            if (returnValue == 0) {
                datesTrainingMinutes += training.getTrainingLength();
            }
        }
        return datesTrainingMinutes;
    }
}
