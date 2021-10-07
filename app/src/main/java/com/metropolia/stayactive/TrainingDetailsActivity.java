package com.metropolia.stayactive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Activity for displaying certain training's details
 * @author Henrik Lappi
 * @author Iina Laamo
 * @version 10/2021
 */
public class TrainingDetailsActivity extends AppCompatActivity {
    private int i;

    /**
     * On create gets and displays certain training's details based on training's index value in Trainings list
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);

        Bundle b = getIntent().getExtras();
        // Get value of extra. If there is no given EXTRA value, give defaultValue(0)
        i = b.getInt(ListViewTrainingsActivity.EXTRA, 0);

        ((TextView) findViewById(R.id.tvTrainingDate))
                .setText(Trainings.getInstance().getTraining(i).trainingDate());

        ((TextView) findViewById(R.id.tvTrainingType))
                .setText(Trainings.getInstance().getTraining(i).getTrainingType());

        ((TextView) findViewById(R.id.tvTrainingLength))
                .setText(Trainings.getInstance().getTraining(i).getTrainingLength() + " minuuttia");
    }

    /**
     * Deletes certain training from Trainings list based on training's index
     * Contains AlertDialog to confirm whether user wants to delete the training or not
     * @param view
     */
    public void deleteTraining (View view) {
        // AlertDialog - https://stackoverflow.com/questions/22424064/creating-simple-confirmation-dialog-on-button-press-android/22424098
        AlertDialog.Builder builder = new AlertDialog.Builder(TrainingDetailsActivity.this);
        builder.setMessage("Haluatko varmasti poistaa harjoituksen?");
        builder.setPositiveButton("Kyllä", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Hides the alert dialog
                dialog.dismiss();
                // Use Trainings class deleteTraining method to delete certain Training object
                Trainings.deleteTraining(i);
                // Return to ListViewTrainingsActivity
                finish();
            }
        });
        builder.setNegativeButton("Ei", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}