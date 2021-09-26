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

    //variable for user profile
    private UserProfile userProfile;

    // Shared preferences storage for user profile info
    private SharedPreferences userProfileStorage;

    //variables for saving and retrieving User Profile info
    private String savedUserName;
    private Double savedWeight;
    private Double savedHeight;
    private Integer savedExerciseGoal;

    // variables for Edit Text views
    private EditText editTextUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        // getting user profile storage and saved info from shared preferences
        userProfileStorage = getSharedPreferences("userProfileStorage", Activity.MODE_PRIVATE);
        savedUserName = userProfileStorage.getString("userName","0");
        // Creating userProfile object
        userProfile = new UserProfile(savedUserName,70.00, 1.72,30);
        // updating UI
        updateUI();

    }

    public void storeUserInputData (){
        // Finding user name field and extracting text to variable savedUserName
        editTextUserName = findViewById(R.id.editTextUserNameValue);
        savedUserName = editTextUserName.getText().toString();


        findViewById(R.id.editTextHeightValue);
        findViewById(R.id.editTextExerciseGoalValue);

    }


    // editing and adding user input values to Shared Preferences saveUserProfile()
    public void saveUserProfile (View v){
        storeUserInputData();
        findViewById(R.id.imageButtonSave);
        Log.d("Debug", "imageButtonSave pressed");
        SharedPreferences.Editor userProfileEditor = userProfileStorage.edit();
        userProfileEditor.putString("userName", savedUserName);
        userProfileEditor.commit();
        // editing and adding user input values to Shared Preferences

    }

    public void updateUI(){
        editTextUserName = findViewById(R.id.editTextUserNameValue);
        editTextUserName.setText(userProfile.getUserName());
    }



}