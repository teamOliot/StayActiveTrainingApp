package com.metropolia.stayactive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TrainingDetailsActivity extends AppCompatActivity {
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);

        Bundle b = getIntent().getExtras();
        // get value of extra. If there is no given EXTRA value, give defaultValue(0)
        i = b.getInt(ListViewTrainingsActivity.EXTRA, 0);

        ((TextView) findViewById(R.id.tvTrainingDate))
                .setText(Trainings.getInstance().getTraining(i).trainingDate());

        ((TextView) findViewById(R.id.tvTrainingType))
                .setText(Trainings.getInstance().getTraining(i).getTrainingType());

        ((TextView) findViewById(R.id.tvTrainingLength))
                .setText(Trainings.getInstance().getTraining(i).getTrainingLength() + " minuuttia");
    }

    // Method for deleting certain training from Trainings list
    public void deleteTraining (View view) {
        // AlertDialog - https://stackoverflow.com/questions/22424064/creating-simple-confirmation-dialog-on-button-press-android/22424098
        AlertDialog.Builder builder = new AlertDialog.Builder(TrainingDetailsActivity.this);
        // builder.setTitle(R.string.app_name);
        builder.setMessage("Haluatko varmasti poistaa harjoituksen?");
        builder.setPositiveButton("Kyllä", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // hides the alert dialog
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