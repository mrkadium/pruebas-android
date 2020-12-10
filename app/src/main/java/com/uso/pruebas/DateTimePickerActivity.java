package com.uso.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateTimePickerActivity extends AppCompatActivity {
    TextView texviewDate;
    TextView texviewTime;
    Button buttonDate;
    Button buttonTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);

        texviewDate = findViewById(R.id.textviewDate);
        texviewTime = findViewById(R.id.textviewTime);
        buttonDate = findViewById(R.id.buttonDate);
        buttonTime = findViewById(R.id.buttonTime);

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog();
            }
        });
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog();
            }
        });
    }

    private void timePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                Calendar time = Calendar.getInstance();
                time.set(Calendar.HOUR, h);
                time.set(Calendar.MINUTE, m);

                CharSequence charseq = DateFormat.format("h:mm a", time); //convert time to format

                texviewTime.setText(charseq);
            }
        }, hour, minute, false); //hour by default, true or false if is 24 hours format

        dialog.show();
    }

    private void datePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) { //year, month and date selected
                Calendar date = Calendar.getInstance();
                date.set(Calendar.YEAR, y);
                date.set(Calendar.MONTH, m);
                date.set(Calendar.DATE, d);

                CharSequence charSequence = DateFormat.format("EEEE, d 'de' MMMM 'de' yyyy", date); //convert date to format

                texviewDate.setText(charSequence.toString());
            }
        }, year, month, date); //hour that appears by default

        dialog.show();
    }
}