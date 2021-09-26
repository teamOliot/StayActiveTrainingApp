package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class UserProfileActivity extends AppCompatActivity {

    //variable for user profile
    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

    }

    // editing and adding user input values to Shared Preferences onPause()
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "onPause() executed");
        // here I should commit to shared preferences
    }

    public void saveUserProfile (View v){
        Log.d("Debug", "imageButtonSave pressed");
        findViewById(R.id.imageButtonSave);
        // täällä pitäisi tallentaa tiedot
    }


}