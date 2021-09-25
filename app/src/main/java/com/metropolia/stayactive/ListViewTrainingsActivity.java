package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListViewTrainingsActivity extends AppCompatActivity {
    public static final String EXTRA = "com.metropolia.stayactive.EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_trainings);

        Button buttonAddTraining = findViewById(R.id.buttonAddTraining);
        buttonAddTraining.setOnClickListener(view -> {
            Log.i("testi", "testataan save buttonia");
            Intent nextActivity = new Intent(ListViewTrainingsActivity.this, AddNewTrainingActivity.class);
            startActivity(nextActivity);
        });

        // Hardcoded training objects for testing
        if(Trainings.getInstance().getTrainings().isEmpty()) {
            Trainings.getInstance().getTrainings().add(new Training("juoksu", 15, 2021, 9, 24));
            Trainings.getInstance().getTrainings().add(new Training("pyöräily", 45, 2021, 9, 24));
        }
    }

    @Override
    protected void onResume () {
        super.onResume();
        ListView lv = findViewById(R.id.ListViewTrainings);

        Trainings.sortDates();
        // Converts given list to individual list items
        lv.setAdapter(new ArrayAdapter<Training>(
                this,
                R.layout.list_item_layout, //XML item layout
                Trainings.getInstance().getTrainings()) //array of data
        );

        // when user clicks individual list item this method takes user to detailed view (TrainingDetailsActivity) about that list item
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
}