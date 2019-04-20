package com.info.eventoutfyp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class EventsFragment extends Fragment implements View.OnClickListener {

    public EditText titleEdittext,descEdittext, quotaEdittext;
    public TextView dateEdittext,timeEdittext, time2Edittext;
    public Button publishButton;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog, timePickerDialog2;
    private String amPm;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    private DatabaseReference mDatabase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_event,container,false);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        //Bind views with their ids
        titleEdittext=(EditText)view.findViewById(R.id.event_title);
        dateEdittext=(TextView)view.findViewById(R.id.event_date);
        time2Edittext=(TextView)view.findViewById(R.id.event_end_time);
        timeEdittext=(TextView)view.findViewById(R.id.event_start_time);
        descEdittext=(EditText)view.findViewById(R.id.event_desc);
        publishButton=(Button)view.findViewById(R.id.create_event);
        quotaEdittext=(EditText)view.findViewById(R.id.event_quota);

        mDatabase = firebaseDatabase.getReference("events");

        //Set listeners of views
        dateEdittext.setOnClickListener(this);
        timeEdittext.setOnClickListener(this);
        publishButton.setOnClickListener(this);
        time2Edittext.setOnClickListener(this);

        //Create DatePickerDialog to show a calendar to user to select birthdate

        //Get current date
        Calendar calendar= Calendar.getInstance();

            //Create datePickerDialog with initial date which is current and decide what happens when a date is selected.
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateEdittext.setText(day + "/" + (month+1) + "/" + year);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));




        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);


        timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                if (hourOfDay >= 12) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                timeEdittext.setText(String.format("%02d:%02d", hourOfDay, minutes) + " " + amPm);
            }
        }, currentHour, currentMinute, false);

        timePickerDialog2 = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                if (hourOfDay >= 12) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                time2Edittext.setText(String.format("%02d:%02d", hourOfDay, minutes) + " " + amPm);
            }
        }, currentHour, currentMinute, false);

        return view;
    }



    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.event_date:
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            case R.id.event_start_time:
                timePickerDialog.show();
                break;
            case R.id.event_end_time:
                timePickerDialog2.show();
                break;
            case R.id.create_event:
                addEvents();

 /*               DatabaseReference newEvent = events.push();
                newEvent.child("title").setValue(title);
                newEvent.child("description").setValue(desc);
                newEvent.child("quota").setValue(quota);
                newEvent.child("date").setValue(date);
                newEvent.child("start").setValue(time1);
                newEvent.child("end").setValue(time2);*/

                Toast.makeText(getActivity(), "event created", Toast.LENGTH_LONG).show();
        }
    }

        public void addEvents()
        {
            String title = titleEdittext.getText().toString();
            String desc = descEdittext.getText().toString();
            String quota = quotaEdittext.getText().toString();
            String time1 = timeEdittext.getText().toString();
            String time2 = time2Edittext.getText().toString();
            String date = dateEdittext.getText().toString();
            String id = mDatabase.push().getKey();
            String venueId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Event event = new Event(title, venueId, desc, true, " ", quota, date, time1, time2 );
            mDatabase.child(id).setValue(event);
            timeEdittext.setText("");
            dateEdittext.setText("");
            titleEdittext.setText("");
            descEdittext.setText("");
            quotaEdittext.setText("");
            timeEdittext.setText("");
            time2Edittext.setText("");

        }







}





