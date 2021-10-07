package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Activity for displaying all added trainings in ListView
 * @author Henrik Lappi
 * @author Iina Laamo
 * @version 10/2021
 */
public class ListViewTrainingsActivity extends AppCompatActivity {
    public static final String EXTRA = "com.metropolia.stayactive.EXTRA";
    private ArrayAdapter myAdapter;

    /**
     * onCreate displays the ListView which contains all trainings and buttonAddTraining
     * Each training can be clicked and this opens a TrainingDetailsActivity
     * buttonAddTraining can be clicked and this opens AddNewTrainingActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_trainings);
        Log.d("debug", "onCreate()");

        ListView lv = findViewById(R.id.ListViewTrainings);

        Button buttonAddTraining = findViewById(R.id.buttonAddTraining);
        buttonAddTraining.setOnClickListener(view -> {
            Intent nextActivity = new Intent(ListViewTrainingsActivity.this, AddNewTrainingActivity.class);
            startActivity(nextActivity);
        });

        if (Trainings.getInstance().getTrainings().isEmpty()) {
            // Load trainings list data
            loadData();
        }
        // sortDates() sorts trainings list based on trainings dates
        Trainings.sortDates();
        // Converts given list to individual list items
        myAdapter = (new ArrayAdapter<Training>(
                this,
                R.layout.list_item_layout, // XML item layout
                Trainings.getInstance().getTrainings())); // Array of data

        lv.setAdapter(myAdapter);
        // When user clicks individual list item this method takes user to detailed view (TrainingDetailsActivity) about that list item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemClick(" + i + ")");
                Intent nextActivity = new Intent(ListViewTrainingsActivity.this, TrainingDetailsActivity.class);
                // Put i (index of list item) to the next view
                nextActivity.putExtra(EXTRA, i); // EXTRA is the key and i is the value
                startActivity(nextActivity);
            }
        });
    }

    /**
     * onResume sorts trainings based on their dates and updates myAdapter
     */
    @Override
    protected void onResume () {
        super.onResume();
        Log.d("debug", "onResume()");
        // sortDates() is called every time ListViewTrainingsActivity onResume() is called
        Trainings.sortDates();
        // When myAdapter.notifyDataSetChanged() has been called, myAdapter is updated
        myAdapter.notifyDataSetChanged();
    }

    /**
     * onStop saves user input data (trainings list)
     */
    @Override
    protected void onStop () {
        super.onStop();
        Log.d("debug", "onStop()");
        saveData();
    }

    // Gson @authors Henrik Lappi, Iina Laamo, Katja Dahlman
    // https://stackoverflow.com/questions/7145606/how-do-you-save-store-objects-in-sharedpreferences-on-android
    // Based on Trainings list, makes a Gson object and saves it to SharedPreferences
    private void saveData() {
        Log.d("debug", "saveData()");
        SharedPreferences sharedPreferences = getSharedPreferences("trainingsStorage", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String jsonTrainings = gson.toJson(Trainings.getInstance().getTrainings());
        editor.putString("trainings", jsonTrainings);
        editor.apply();
    }

    // Gson @authors Henrik Lappi, Iina Laamo, Katja Dahlman
    // https://stackoverflow.com/questions/7145606/how-do-you-save-store-objects-in-sharedpreferences-on-android
    // Gets Trainings list data from SharedPreferences and saves the data in Trainings list
    private void loadData() {
        Log.d("debug", "loadData()");
        SharedPreferences sharedPreferences = getSharedPreferences("trainingsStorage", MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonTrainings = sharedPreferences.getString("trainings", null);
        Type type = new TypeToken<ArrayList<Training>>() {}.getType();
        // Add stored Training objects in trainingList
        ArrayList<Training> trainingList = gson.fromJson(jsonTrainings, type);

        if (!(trainingList == null)) {
            // Add trainingList values (Training objects) in singleton Trainings list
            for (Training training : trainingList) {
                Trainings.getInstance().getTrainings().add(training);
            }
        }
    }
}