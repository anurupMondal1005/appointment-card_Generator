package com.example.uemapplication;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    SQLiteDatabase sd;
    Button generatePDF ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        sd=openOrCreateDatabase("UEMApp",MODE_PRIVATE,null);
        sd.execSQL("CREATE TABLE IF NOT EXISTS Info(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,address TEXT,purpose TEXT,faculty TEXT,contact TEXT,date TEXT);");
        sd.execSQL("INSERT INTO Info(name,email,address,purpose,faculty,contact,date) VALUES(?,?,?,?,?,?,?);",new String[]{name,email,address,purpose,faculty,contact,date});
        sd.close();

        final ImageView _imv= findViewById(R.id.imageView2);
        final Bitmap _bitmap = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length);
        _imv.setImageBitmap(_bitmap);


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

        findViewById(R.id.buttonOpenLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.generatePDF).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrintAttributes pa=new PrintAttributes.Builder().setColorMode(PrintAttributes.COLOR_MODE_COLOR).setMediaSize(PrintAttributes.MediaSize.NA_LETTER).setResolution(new PrintAttributes.Resolution("zooey", PRINT_SERVICE, 1080, 1920)).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build();
                PrintedPdfDocument document = new PrintedPdfDocument(Main2Activity.this,pa);
                PdfDocument.Page page = document.startPage(0);

                Canvas canvas = page.getCanvas();
                Paint paint = new Paint();
                paint.setTextSize(28);
                canvas.drawBitmap(_bitmap,250,100,paint);
                canvas.drawText("Name:  "+name, 100, 400, paint);
                canvas.drawText("Email:  "+email, 100, 445, paint);
                canvas.drawText("Address:  "+address, 100, 490,paint);
                canvas.drawText("Purpose:  "+purpose, 100, 535, paint);
                canvas.drawText("Faculty:  "+faculty, 100, 580, paint);
                canvas.drawText("Contact:  "+contact, 100, 625,paint);
                canvas.drawText("Date:  "+date, 100, 670,paint);



                // draw something on the page
//                View content = findViewById(R.id.layout);
//                content.draw(page.getCanvas());

                document.finishPage(page);
                try {
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dir";

                    File dir = new File(path);
                    if(!dir.exists())
                        dir.mkdirs();

                    File file = new File(dir, "newFile.pdf");
                    FileOutputStream fOut = new FileOutputStream(file);
                    document.writeTo(fOut);
                    document.close();
                    fOut.close();
                    Toast.makeText(Main2Activity.this, "Success", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException("Error generating file", e);
                }
//                Bitmap bitmap = takeScreenshot();
//                saveBitmap(bitmap);
            }
        });

    }
    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }
    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
}