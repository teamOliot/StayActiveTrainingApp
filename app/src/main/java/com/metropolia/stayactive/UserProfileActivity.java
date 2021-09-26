package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    //variable for user profile object
    private UserProfile userProfile;

    // Shared preferences storage for user profile info
    private SharedPreferences userProfileStorage;

    //variables for saving and retrieving User Profile info that user has inputed and has been saved to shared preferences
    private String savedUserName;
    private Integer savedWeight;
    private Integer savedHeight;
    private Integer savedExerciseGoal;

    // variables for Edit Text views: username, weight, height and exercise goal
    private EditText editTextUserName;
    private EditText editTextWeight;
    private EditText editTextHeight;
    private EditText editExerciseGoal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        // getting saved info from Shared Preferences storage called userProfileStorage
        userProfileStorage = getSharedPreferences("userProfileStorage", Activity.MODE_PRIVATE);
        // getting saved values from user
        savedUserName = userProfileStorage.getString("userName", " ");
        savedWeight = userProfileStorage.getInt("weight", 0);
        savedHeight = userProfileStorage.getInt("height", 0);
        savedExerciseGoal = userProfileStorage.getInt("exerciseGoal", 0);
        // Creating userProfile object with stored information
        userProfile = new UserProfile(savedUserName, savedWeight, savedHeight, savedExerciseGoal);
        // using method to update UI
        updateUI();

    }


    // editing and adding user input values to Shared Preferences saveUserProfile() when button save is pressed
    public void saveUserProfile(View v) {
        findViewById(R.id.imageButtonSave);
        Log.d("Debug", "imageButtonSave pressed");
        SharedPreferences.Editor userProfileEditor = userProfileStorage.edit();
        // Finding user name field and extracting text to variable, and putting it to editor
        savedUserName = editTextUserName.getText().toString();
        userProfileEditor.putString("userName", savedUserName);
        // Finding weight field and extracting text to variable, and putting it to editor
        savedWeight = Integer.parseInt(editTextWeight.getText().toString());
        userProfileEditor.putInt("weight", savedWeight);
        // Finding height field and extracting text to variable, and putting it to editor
        savedHeight = Integer.parseInt(editTextHeight.getText().toString());
        userProfileEditor.putInt("height", savedHeight);
        // Finding exercise goal field and extracting text to variable, and putting it to editor
        savedExerciseGoal = Integer.parseInt(editExerciseGoal.getText().toString());
        userProfileEditor.putInt("exerciseGoal", savedExerciseGoal);
        // committing all changes
        userProfileEditor.commit();
    }


    public void updateUI() {
        editTextUserName = findViewById(R.id.editTextUserNameValue);
        editTextUserName.setText(userProfile.getUserName());
        editTextWeight = findViewById(R.id.editTextWeightValue);
        editTextWeight.setText(Integer.toString(userProfile.getWeight()));
        editTextHeight = findViewById(R.id.editTextHeightValue);
        editTextHeight.setText(Integer.toString(userProfile.getHeight()));
        editExerciseGoal = findViewById(R.id.editTextExerciseGoalValue);
        editExerciseGoal.setText(Integer.toString(userProfile.getExerciseGoal()));

    }


}