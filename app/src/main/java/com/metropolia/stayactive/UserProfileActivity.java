package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Katja Dahlman
 * @version 10/2021
 */

public class UserProfileActivity extends AppCompatActivity {

    // Variable for user profile object
    private UserProfile userProfile;

    // Shared preferences storage for user profile info
    private SharedPreferences userProfileStorage;

    // Variables for saving and retrieving User Profile info that user has inputted and has been saved to shared preferences
    private String savedUserName;
    private Integer savedWeight, savedHeight, savedExerciseGoal;

    // Variables for Edit Text views: username, weight, height and exercise goal
    private EditText editTextUserName, editTextWeight, editTextHeight, editExerciseGoal;
    private TextView tvBMIvalue;

    boolean isAllFieldsChecked = false;

    /**
     * On create where content is set user info is fetched from shared preferences
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getUserInfo();
        Log.d("Debug", "OnCreate () done");
    }

    /**
     * Gets user info from Shared Preferences storage
     */
    public void getUserInfo() {
        // Getting saved info from Shared Preferences storage called userProfileStorage
        userProfileStorage = getSharedPreferences("userProfileStorage", Activity.MODE_PRIVATE);
        // Getting saved values from user
        savedUserName = userProfileStorage.getString("userName", "");
        savedWeight = userProfileStorage.getInt("weight", 0);
        savedHeight = userProfileStorage.getInt("height", 0);
        savedExerciseGoal = userProfileStorage.getInt("exerciseGoal", 0);
        // Creating userProfile object with stored information
        userProfile = new UserProfile(savedUserName, savedWeight, savedHeight, savedExerciseGoal);
        // Using method to update UI
        updateUI();
    }

    // Saving user information when @imageButtonSave is pressed

    /**
     * Method for saving information user has inputted. Also doing data validation before saving.
     * @param v
     */
    public void saveUserProfile(View v) {
        isAllFieldsChecked = CheckAllFields();
        Log.d("Debug", "imageButtonSave pressed");
        if (isAllFieldsChecked) {
            // Calling saveInfo() method if all fields are filled
            saveInfo();
            // Calling getUserInfo() so that saved user info is also updated to UI
            getUserInfo();

        }
    }

    /**
     * In this method data that user has filled will be edited, added and applied to Shared Preferences
     */
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
        // Committing all changes
        userProfileEditor.apply();
        Log.d("Debug", "saveInfo() done");
    }

    /**
     * Method for updating user interface and setting values in text fields and edit text fields
     */
    public void updateUI() {
        editTextUserName = findViewById(R.id.editTextUserNameValue);
        editTextUserName.setText(userProfile.getUserName());
        /* Weight, Height and Exercise goal are integers, and value 0 has been given as preset to Shared preferences onCreate
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
        if (userProfile.getBmi() > 0){
            tvBMIvalue.setText(Integer.toString(userProfile.getBmi()));
        }
        Log.d("Debug", "updateUI() done");
    }

    // Got inspiration and help from https://www.geeksforgeeks.org/implement-form-validation-error-to-edittext-in-android/
    /**
     * Method for data validation
     * @return Returns true when all fields are checked and correct. Used to validate user inputted data before saving.
     */
    private boolean CheckAllFields() {
        if (editTextUserName.length() == 0) {
            Log.d("Debug", "editTextUserName error set");
            editTextUserName.setError("Tämä kenttä on pakollinen");
            editTextUserName.requestFocus();
            return false;
        }

        if (editTextWeight.length() == 0) {
            Log.d("Debug", "editTextWeight error set");
            editTextWeight.setError("Tämä kenttä on pakollinen");
            editTextWeight.requestFocus();
            return false;
        }

        if (editTextHeight.length() == 0) {
            Log.d("Debug", "editTextHeight error set");
            editTextHeight.setError("Tämä kenttä on pakollinen");
            editTextHeight.requestFocus();
            return false;
        } else if (editExerciseGoal.length() == 0) {
            Log.d("Debug", "editExerciseGoal error set");
            editExerciseGoal.setError("Tämä kenttä on pakollinen");
            editExerciseGoal.requestFocus();
            return false;
        }
        Log.d("Debug", "CheckAllFields() done");
        // After all validation done and user has corrected them, return true
        return true;
    }
}
