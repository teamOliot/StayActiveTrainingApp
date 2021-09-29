package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewTrainingActivity extends AppCompatActivity {
    private EditText editTrainingTypeView;
    private EditText editTrainingLengthView;
    private int newYearValue;
    private int newMonthValue;
    private int newDayValue;
    private String trainingType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_training);

    //    editTrainingTypeView = findViewById(R.id.edit_training_type);
        editTrainingLengthView = findViewById(R.id.edit_training_length);
        final CalendarView calendar = findViewById(R.id.calendarView);

        // get current date as default value
        Calendar currentDate = Calendar.getInstance();
        // save currentDate year, month and day default values in variables
        newYearValue = currentDate.get(Calendar.YEAR);
        newMonthValue = currentDate.get(Calendar.MONTH);
        newDayValue = currentDate.get(Calendar.DAY_OF_MONTH);

        // if user selects date from calendar view this method is called
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                // save calendarView year, month and day values in variables
                newYearValue = year;
                newMonthValue = month;
                newDayValue = dayOfMonth;
            }
        });

        // spinner functionality // return only "juoksu" value
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
                //first,  we have to retrieve the item position as a string
                // then, we can change string value into integer
                String item_position = String.valueOf(position);

                int positonInt = Integer.valueOf(item_position);
                trainingType = adapter.getItem(positonInt).toString();
            //    Toast.makeText(AddNewTrainingActivity.this, "value is "+ positonInt, Toast.LENGTH_SHORT).show();
            }
    });
    }

    public void saveNewTraining (View view) {
        //String trainingType = editTrainingTypeView.getText().toString();
        int trainingLength = Integer.parseInt(editTrainingLengthView.getText().toString());

        Training training = new Training(trainingType, trainingLength, newYearValue, newMonthValue + 1, newDayValue);
        Trainings.getInstance().getTrainings().add(training);
        finish();
    }

}