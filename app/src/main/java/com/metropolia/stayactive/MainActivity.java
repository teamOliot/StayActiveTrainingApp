package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //takes user to UserProfileActivity
    public void toUserProfileView (View view) {
        Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
        Log.d("debug", "buttonUserProfile button works");
        startActivity(intent);
    }

    // takes user to ListViewTrainingsActivity
    public void toTrainingsListView (View view) {
        Intent intent = new Intent(MainActivity.this, ListViewTrainingsActivity.class);
        Log.d("debug", "buttonTrainingsList button works");
        startActivity(intent);
    }


}