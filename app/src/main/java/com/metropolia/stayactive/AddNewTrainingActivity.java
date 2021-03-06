package com.metropolia.stayactive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * Activity for adding new training
 * @author Iina Laamo
 * @author Henrik Lappi
 * @version 10/2021
 */
public class AddNewTrainingActivity extends AppCompatActivity {
    private EditText editTrainingLengthView;
    private int newYearValue;
    private int newMonthValue;
    private int newDayValue;
    private String trainingType;

    // Shared preferences storage for user profile info
    private SharedPreferences userProfileStorage;
    private int savedExerciseGoal;

    /**
     * onCreate displays a form (containing CalendarView, Spinner and EditText for training length) for adding new training information
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_training);

        editTrainingLengthView = findViewById(R.id.edit_training_length);
        final CalendarView calendar = findViewById(R.id.calendarView);

        // Getting saved info from Shared Preferences storage called userProfileStorage
        userProfileStorage = getSharedPreferences("userProfileStorage", Activity.MODE_PRIVATE);
        // Getting saved exerciseGoal value from user
        savedExerciseGoal = userProfileStorage.getInt("exerciseGoal", 0);

        // Get current date as default value
        Calendar currentDate = Calendar.getInstance();
        // Save currentDate year, month and day default values in variables
        newYearValue = currentDate.get(Calendar.YEAR);
        newMonthValue = currentDate.get(Calendar.MONTH);
        newDayValue = currentDate.get(Calendar.DAY_OF_MONTH);

        // If user selects date from calendar view this method is called
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // Save calendarView year, month and day values in variables
                newYearValue = year;
                newMonthValue = month;
                newDayValue = dayOfMonth;
            }
        });

        // Spinner functionality
        // https://developer.android.com/guide/topics/ui/controls/spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.training_types_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new SpinnerActivity() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the item position as a string
                String item_position = String.valueOf(position);
                // Change string value into int
                int positonInt = Integer.valueOf(item_position);
                // Get and save selected trainingType in trainingType variable
                trainingType = adapter.getItem(positonInt).toString();
                }
        });
    }

    /**
     * Method for saving new training information
     * Checks that user's input in training length is valid
     * Creates a Training object and adds it to the Trainings list
     * Displays an AlertDialog if user has achieved their daily exercise goal
     * At the end finish(); method returns the user to the previous activity
     * @param view
     */
    public void saveNewTraining (View view) {
        boolean isTrainingLengthFieldChecked = CheckTrainingLengthField();

        if (isTrainingLengthFieldChecked) {
        int trainingLength = Integer.parseInt(editTrainingLengthView.getText().toString());
        Log.d("debug", trainingLength + "");
            // Create new Training object. We add 1 in newMonthValue because CalendarView start counting monthValue index from 0
            Training training = new Training(trainingType, trainingLength, newYearValue, newMonthValue + 1, newDayValue);
            // Add new Training object in Trainings list
            Trainings.getInstance().getTrainings().add(training);

            int allCertainDatesMinutes = Trainings.countDatesMinutes(training);
            Log.d("debug", "certain dates minutes " + allCertainDatesMinutes);
            // Check if user has achieved his daily exercise goal - compare users daily exercise goal (savedExerciseGoal) to allCertainDatesMinutes
            if (savedExerciseGoal <= allCertainDatesMinutes) {
                Log.d("debug", "tavoite saavutettu " + training.trainingDate() + " p??iv??lt??.");
                // AlertDialog tells user that a certain date's exercise goal has been achieved
                AlertDialog.Builder builder = new AlertDialog.Builder(AddNewTrainingActivity.this);
                builder.setMessage("P??iv??n " + training.trainingDate() + " liikuntatavoite on saavutettu! Hyv???? ty??t??!");
                // User has to click OK button to confirm that the message has been received
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Hides the alert dialog
                        dialog.dismiss();
                        // Return to ListViewTrainingsActivity
                        finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                finish();
            }
        }
    }

    // Method for checking if inserted trainingLength value is empty or 0
    private boolean CheckTrainingLengthField() {
        Log.d("debug", "CheckTrainingLengthField");

        if (editTrainingLengthView.getText().toString().length() == 0) {
            editTrainingLengthView.setError("T??m?? kentt?? on pakollinen.");
            editTrainingLengthView.requestFocus();
            return false;
        }
        if (Integer.parseInt(editTrainingLengthView.getText().toString()) <= 0) {
            editTrainingLengthView.setError("Harjoituksen keston t??ytyy olla pidempi kuin 0 minuuttia.");
            editTrainingLengthView.requestFocus();
            return false;
        }
        return true;
    }
}