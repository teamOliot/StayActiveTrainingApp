package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class UserProfileActivity extends AppCompatActivity {

    //variable for user profile object
    private UserProfile userProfile;

    // Shared preferences storage for user profile info
    private SharedPreferences userProfileStorage;

    //variables for saving and retrieving User Profile info that user has inputed and has been saved to shared preferences
    private String savedUserName;
    private Integer savedWeight, savedHeight, savedExerciseGoal;

    // variables for Edit Text views: username, weight, height and exercise goal
    private EditText editTextUserName, editTextWeight, editTextHeight, editExerciseGoal;
    private TextView tvBMIvalue;

    boolean isAllFieldsChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getUserInfo();
        Log.d("Debug", "OnCreate () done");
    }


    public void getUserInfo() {
        // getting saved info from Shared Preferences storage called userProfileStorage
        userProfileStorage = getSharedPreferences("userProfileStorage", Activity.MODE_PRIVATE);
        // getting saved values from user
        savedUserName = userProfileStorage.getString("userName", "");
        savedWeight = userProfileStorage.getInt("weight", 0);
        savedHeight = userProfileStorage.getInt("height", 0);
        savedExerciseGoal = userProfileStorage.getInt("exerciseGoal", 0);
        // Creating userProfile object with stored information
        userProfile = new UserProfile(savedUserName, savedWeight, savedHeight, savedExerciseGoal);
        // using method to update UI
        updateUI();
    }

    // Saving user information when button is pressed
    public void saveUserProfile(View v) {
        isAllFieldsChecked = CheckAllFields();
        findViewById(R.id.imageButtonSave);
        Log.d("Debug", "imageButtonSave pressed");
        if (isAllFieldsChecked) {
            // calling saveInfo() method if all fields are filled
            saveInfo();

        }
        getUserInfo();

    }

    // editing and adding user input values to Shared Preferences
    public void saveInfo() {
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
        userProfileEditor.apply();
        Log.d("Debug", "saveInfo() done");
    }

    // Method for updating UI (edit text views)
    public void updateUI() {
        editTextUserName = findViewById(R.id.editTextUserNameValue);
        editTextUserName.setText(userProfile.getUserName());
        /* Weight, Height and Exercise goal are integeres, and value 0 has been given as preset to Shared preferences onCreate
        Here if value is 0, text will be set to empty, so hints are visible, otherwise saved info is fetched. Username is string, so value has been saved as empty.
        */

        editTextWeight = findViewById(R.id.editTextWeightValue);
        if (userProfile.getWeight() != 0) {
            editTextWeight.setText(Integer.toString(userProfile.getWeight()));
        } else {
            editTextWeight.setText("");
        }
        editTextHeight = findViewById(R.id.editTextHeightValue);
        if (userProfile.getHeight() != 0) {
            editTextHeight.setText(Integer.toString(userProfile.getHeight()));
        } else {
            editTextHeight.setText("");
        }
        editExerciseGoal = findViewById(R.id.editTextExerciseGoalValue);
        if (userProfile.getExerciseGoal() != 0) {
            editExerciseGoal.setText(Integer.toString(userProfile.getExerciseGoal()));
        } else {
            editExerciseGoal.setText("");
        }
        Log.d("Debug", userProfile.getBmi() + "");
        tvBMIvalue = findViewById(R.id.tvBMIvalue);
        tvBMIvalue.setText(Float.toString(userProfile.getBmi()));
        Log.d("Debug", "updateUI() done");

    }

    //Method for checking if inserted text is empty
    // https://www.geeksforgeeks.org/implement-form-validation-error-to-edittext-in-android/

    private boolean CheckAllFields() {

        if (editTextUserName.length() == 0) {
            Log.d("Debug", "editTextUserName error set");
            editTextUserName.setError("Tämä kenttä on pakollinen");
            return false;
        }

        if (editTextWeight.length() == 0) {
            Log.d("Debug", "editTextWeight error set");
            editTextWeight.setError("Tämä kenttä on pakollinen");
            return false;
        }

        if (editTextHeight.length() == 0) {
            Log.d("Debug", "editTextHeight error set");
            editTextHeight.setError("Tämä kenttä on pakollinen");
            return false;
        } else if (editExerciseGoal.length() == 0) {
            Log.d("Debug", "editExcerciseGoal error set");
            editExerciseGoal.setError("Tämä kenttä on pakollinen");
            return false;
        }
        Log.d("Debug", "CheckAllFields() done");
        // after all validation done and user has corrected them, return true
        return true;

    }


}




