package com.example.uemapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    SQLiteDatabase sd;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sd=openOrCreateDatabase("UEMApp",MODE_PRIVATE,null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        RecyclerView recyclerView = findViewById(R.id.recyclerLOG);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        Cursor c=sd.rawQuery("SELECT * FROM Info;",null);
        c.moveToFirst();
        List<LogS> logArray=new ArrayList<>();
        while(!c.isLast())
        {
            String message=c.getString(1)+c.getString(2)+c.getString(3)+c.getString(4)+c.getString(5)+c.getString(6)+c.getString(7);
            logArray.add(new LogS(message));
            c.moveToNext();
        }
        String message=c.getString(1)+c.getString(2)+c.getString(3)+c.getString(4)+c.getString(5)+c.getString(6)+c.getString(7);
        logArray.add(new LogS(message));
        mAdapter = new LogAdapter(this, logArray);

        recyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onDestroy() {
        sd.close();
        super.onDestroy();
    }
}
