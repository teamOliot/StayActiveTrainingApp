package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
                // getYear will be replaced with date value
                .setText(Integer.toString(Trainings.getInstance().getTraining(i).getYear()));

        ((TextView) findViewById(R.id.tvTrainingType))
                .setText(Trainings.getInstance().getTraining(i).getTrainingType());

        ((TextView) findViewById(R.id.tvTrainingLength))
                .setText(Integer.toString(Trainings.getInstance().getTraining(i).getTrainingLength()));
    }

    // Method for deleting certain training from Trainings list
    public void deleteTraining (View view) {
        // Use Trainings class deleteTraining method to delete certain Training object
        Trainings.deleteTraining(i);
        // Return to ListViewTrainingsActivity
        finish();
    }
}