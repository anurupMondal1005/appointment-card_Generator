package com.example.uemapplication;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.uemapplication.MainActivity.getAddress;
import static com.example.uemapplication.MainActivity.getContact;
import static com.example.uemapplication.MainActivity.getFaculty;
import static com.example.uemapplication.MainActivity.getPurpose;
import static com.example.uemapplication.MainActivity.getdate;

public class Main2Activity extends AppCompatActivity {
    String name = (String) MainActivity.getName();
    String email = (String) MainActivity.getEmail();
    String address = getAddress();
    String purpose = getPurpose();
    String faculty = getFaculty();
    String contact = getContact();
    String date=getdate();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        TextView textView = findViewById(R.id.name);
        textView.setText(name);

        TextView textView2 = findViewById(R.id.editText2);
        textView2.setText(email);

        TextView textView3 = findViewById(R.id.editText3);
        textView3.setText(address);

        TextView textView4 = findViewById(R.id.editText4);
        textView4.setText(purpose);

        TextView textView5 = findViewById(R.id.editText5);
        textView5.setText(faculty);

        final TextView textView6 = findViewById(R.id.contact);
        textView6.setText(contact);

        final TextView dateField =  findViewById(R.id.textView16);
        dateField.setText(date);



    }

}