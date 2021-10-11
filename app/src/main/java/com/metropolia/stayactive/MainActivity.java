package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * Main activity that contains methods for the main two activities: user profile and exercise diary
 * @author Katja Dahlman
 * @author Iina Laamo
 * @author Henrik Lappi
 * @version 10/2021
 */
public class MainActivity extends AppCompatActivity {

    /**
     * On create gets and displays the activity_main xml
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method that takes the user to UserProfileActivity
     * @param view
     */
    public void toUserProfileView (View view) {
        Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
        Log.d("debug", "buttonUserProfile button works");
        startActivity(intent);
    }

    /**
     * Method that takes user to ListViewTrainingsActivity
     * @param view
     */
    public void toTrainingsListView (View view) {
        Intent intent = new Intent(MainActivity.this, ListViewTrainingsActivity.class);
        Log.d("debug", "buttonTrainingsList button works");
        startActivity(intent);
    }
}