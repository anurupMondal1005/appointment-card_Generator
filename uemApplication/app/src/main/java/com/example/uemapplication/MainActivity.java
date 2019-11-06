package com.example.uemapplication;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static String name;
    static String email;
    static String address;
    static String purpose;
    static String faculty;
    static String contact;
    static String data;
    static String refid;

    EditText nameField;
    EditText emailField;
    EditText addressField;
    EditText purposeField;
    EditText facultyField;
    EditText contactField;
    TextView refidField;

    Bitmap image;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String[] users = { "Sajal Dasgupta","Satyajit Chakraborty", "Rohini Alavala", "Praveen Kumar", "Madhav Sai" };
        //Spinner spin = (Spinner) findViewById(R.id.spinner1);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spin.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        Button takePhoto=findViewById(R.id.button1);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                nameField =  findViewById(R.id.editText1);
                name = nameField.getText().toString();

                emailField = findViewById(R.id.editText2);
                email = emailField.getText().toString();

                addressField = findViewById(R.id.editText3);
                address = addressField.getText().toString();

                purposeField = findViewById(R.id.editText4);
                purpose = purposeField.getText().toString();

                facultyField = findViewById(R.id.editText4);
                faculty = facultyField.getText().toString();

                contactField = findViewById(R.id.editText6);
                contact = contactField.getText().toString();
                intent.putExtra("name",name);
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                intent.putExtra("image",_bs.toByteArray());
                startActivity(intent);
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},1);
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 120);
            }
        });

        final TextView dateField = (TextView)findViewById(R.id.textView16);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dfDate_day= new SimpleDateFormat("E, dd/MM/yyyy HH:mm:ss");
        data = dfDate_day.format(c.getTime());
        dateField.setText(data);

        final TextView refidField = (TextView)findViewById(R.id.textView18);
        Random r = new Random();
        int i1 = r.nextInt(8000000 - 100000) + 7615;
        refidField.setText("ref id is :"+i1);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 120) {
            image = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView) findViewById(R.id.imageView2); //sets imageview as the bitmap
            imageview.setImageBitmap(image);
        }
    }


    public static String getName()
    {
        return name;
    }

    public static String getEmail()
    {
        return email;
    }

    public static String getAddress()
    {
        return address;
    }

    public static String getPurpose()
    {
        return purpose;
    }

    public static String getFaculty()
    {
        return faculty;
    }

    public static String getContact()
    {
        return contact;
    }

    public static String getdate(){return data;}
}

