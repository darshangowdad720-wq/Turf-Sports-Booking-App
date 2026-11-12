package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtPhone;
    Spinner spinnerSport, spinnerTime;
    Button btnDate, btnBook;
    TextView txtResult;

    String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect XML components
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);

        spinnerSport = findViewById(R.id.spinnerSport);
        spinnerTime = findViewById(R.id.spinnerTime);

        btnDate = findViewById(R.id.btnDate);
        btnBook = findViewById(R.id.btnBook);

        txtResult = findViewById(R.id.txtResult);


        // Sports list
        String[] sports = {
                "Football",
                "Cricket",
                "Box Cricket",
                "Badminton"
        };

        ArrayAdapter<String> sportAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        sports
                );

        spinnerSport.setAdapter(sportAdapter);


        // Time slots
        String[] timeSlots = {
                "6:00 AM - 7:00 AM",
                "7:00 AM - 8:00 AM",
                "8:00 AM - 9:00 AM",
                "5:00 PM - 6:00 PM",
                "6:00 PM - 7:00 PM",
                "7:00 PM - 8:00 PM",
                "8:00 PM - 9:00 PM",
                "9:00 PM - 10:00 PM"
        };

        ArrayAdapter<String> timeAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        timeSlots
                );

        spinnerTime.setAdapter(timeAdapter);


        // Date button
        btnDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(
                                MainActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(
                                            DatePicker view,
                                            int year,
                                            int month,
                                            int dayOfMonth) {

                                        selectedDate =
                                                dayOfMonth + "/" +
                                                        (month + 1) + "/" +
                                                        year;

                                        btnDate.setText(selectedDate);
                                    }

                                },
                                year,
                                month,
                                day
                        );

                datePickerDialog.show();
            }
        });


        // Book button
        btnBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();

                String sport =
                        spinnerSport.getSelectedItem().toString();

                String time =
                        spinnerTime.getSelectedItem().toString();


                // Check name
                if (name.isEmpty()) {

                    edtName.setError("Enter your name");
                    return;
                }


                // Check phone
                if (phone.isEmpty()) {

                    edtPhone.setError("Enter phone number");
                    return;
                }


                // Check date
                if (selectedDate.isEmpty()) {

                    Toast.makeText(
                            MainActivity.this,
                            "Please select a date",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }


                // Booking confirmation
                String result =
                        "BOOKING CONFIRMED\n\n" +
                                "Name: " + name + "\n" +
                                "Phone: " + phone + "\n" +
                                "Sport: " + sport + "\n" +
                                "Date: " + selectedDate + "\n" +
                                "Time: " + time + "\n\n" +
                                "Thank you for booking!";

                txtResult.setText(result);

                Toast.makeText(
                        MainActivity.this,
                        "Slot booked successfully!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}