package com.metropolia.stayactive;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

// https://developer.android.com/guide/topics/ui/controls/spinner

/**
 * Spinner activity that utilizes a reaction from user
 * @author Katja Dahlman
 * @author Iina Laamo
 * @author Henrik Lappi
 * @version 10/2021
 */
public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    /**
     * Method that is used to react to item selection
     * @param parent
     * @param view
     * @param pos
     * @param id
     */
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // We override this method in AddNewTrainingActivity
    }

    /**
     * @param parent
     */
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}