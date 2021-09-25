package com.metropolia.stayactive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import java.util.Calendar;

public class AddNewTrainingActivity extends AppCompatActivity {
    private EditText editTrainingTypeView;
    private EditText editTrainingLengthView;
    private int newYearValue;
    private int newMonthValue;
    private int newDayValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_training);

        editTrainingTypeView = findViewById(R.id.edit_training_type);
        editTrainingLengthView = findViewById(R.id.edit_training_length);
        //get this localDate if you don't change it on calendarView
    //    localDate = LocalDate.now();

        final CalendarView calendar = findViewById(R.id.calendarView);

        Calendar currentDate = Calendar.getInstance();
        Log.d("cal", currentDate.get(Calendar.MONTH) + "");

        newYearValue = currentDate.get(Calendar.YEAR);
        newMonthValue = currentDate.get(Calendar.MONTH);
        newDayValue = currentDate.get(Calendar.DAY_OF_MONTH);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                newYearValue = year;
                newMonthValue = month;
                newDayValue = dayOfMonth;
                System.out.println(month);
            }
        });

        final Button button = findViewById(R.id.button_save);

        button.setOnClickListener(view -> {
            String trainingType = editTrainingTypeView.getText().toString();
            int trainingLength = Integer.parseInt(editTrainingLengthView.getText().toString());

            Training training = new Training(trainingType, trainingLength, newYearValue, newMonthValue + 1, newDayValue);
            Trainings.getInstance().getTrainings().add(training);
            finish();
        });
    }

}