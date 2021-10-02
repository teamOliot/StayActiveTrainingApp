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

public class ListViewTrainingsActivity extends AppCompatActivity {
    public static final String EXTRA = "com.metropolia.stayactive.EXTRA";
    private ArrayAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_trainings);
        Log.d("debug", "onCreate()");

        ListView lv = findViewById(R.id.ListViewTrainings);

        Button buttonAddTraining = findViewById(R.id.buttonAddTraining);
        buttonAddTraining.setOnClickListener(view -> {
            Log.i("testi", "testataan save buttonia");
            Intent nextActivity = new Intent(ListViewTrainingsActivity.this, AddNewTrainingActivity.class);
            startActivity(nextActivity);
        });

        if (Trainings.getInstance().getTrainings().isEmpty()) {
            loadData();
        }
        // sortDates() sorts trainings list based on trainings dates
        Trainings.sortDates();
        // Converts given list to individual list items
        myAdapter = (new ArrayAdapter<Training>(
                this,
                R.layout.list_item_layout, //XML item layout
                Trainings.getInstance().getTrainings())); //array of data

        lv.setAdapter(myAdapter);
        // When user clicks individual list item this method takes user to detailed view (TrainingDetailsActivity) about that list item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemClick(" + i + ")");
                Intent nextActivity = new Intent(ListViewTrainingsActivity.this, TrainingDetailsActivity.class);
                // put i = index of list item to the next view
                nextActivity.putExtra(EXTRA, i); // EXTRA is the key and i is the value
                startActivity(nextActivity);
            }
        });
    }

    @Override
    protected void onResume () {
        super.onResume();
        Log.d("debug", "onResume()");
        // sortDates() is called every time ListViewTrainingsActivity onResume() is called
        Trainings.sortDates();
        // When myAdapter.notifyDataSetChanged() has been called, myAdapter is updated
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause () {
        super.onPause();
        Log.d("debug", "onPause()");
    }

    @Override
    protected void onStop () {
        super.onStop();
        Log.d("debug", "onStop()");
        // Save user input data (trainings list)
        saveData();
    }

    private void saveData() {
        Log.d("debug", "saveData()");
        SharedPreferences sharedPreferences = getSharedPreferences("trainingsStorage", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String jsonTrainings = gson.toJson(Trainings.getInstance().getTrainings());
        editor.putString("trainings", jsonTrainings);
        editor.apply();
    }

    private void loadData() {
        Log.d("debug", "loadData()");
        SharedPreferences sharedPreferences = getSharedPreferences("trainingsStorage", MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonTrainings = sharedPreferences.getString("trainings", null);

        Type type = new TypeToken<ArrayList<Training>>() {}.getType();

        ArrayList<Training> trainingList = gson.fromJson(jsonTrainings, type);
        if (!(trainingList == null)) {
            for (Training training : trainingList) {
                Trainings.getInstance().getTrainings().add(training);
            }
        }
    }
}