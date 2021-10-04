package com.metropolia.stayactive;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

// https://developer.android.com/guide/topics/ui/controls/spinner
public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // We override this method in AddNewTrainingActivity
        /*Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();*/
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
