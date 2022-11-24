package com.example.lifestyle_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class BreaksPage extends AppCompatActivity implements AddBreaksPage.AddBreaksPageListener, EditBreaksPage.EditBreaksPageListener {
    private FloatingActionButton addBreakBtn;
    BreakAdapter breakAdapter;
    ArrayList<Breaks_Storage_Model> Breaks_Storage_ModelArrayList;
    RecyclerView breakRV;
    DatabaseHelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breaks_page);

        breakRV = findViewById(R.id.idRVBreaks);
        breakRV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                editDialog();
            }
        });

        databasehelper = new DatabaseHelper(BreaksPage.this);
        Cursor cursor = databasehelper.getALlBreaksData();
        Breaks_Storage_ModelArrayList = new ArrayList<Breaks_Storage_Model>();
        if(cursor.getCount() == 0){
            Toast.makeText(this.getApplicationContext(),"No Breaks data to display", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                Breaks_Storage_ModelArrayList.add(new Breaks_Storage_Model(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3)));
            }
        }

        addBreakBtn = (FloatingActionButton) findViewById(R.id.fab);
        addBreakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDialog();
            }
        });

        // we are initializing our adapter class and passing our arraylist to it.
        breakAdapter = new BreakAdapter(this, Breaks_Storage_ModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        breakRV.setLayoutManager(linearLayoutManager);
        breakRV.setAdapter(breakAdapter);
    }


    private void openAddDialog() {
        AddBreaksPage addBreaksPage = new AddBreaksPage();
        addBreaksPage.show(getSupportFragmentManager(), "Add breaks");
    }

    private void editDialog(){
        EditBreaksPage editBreaksPage = new EditBreaksPage();
        editBreaksPage.show(getSupportFragmentManager(),"Edit breaks");

    }

    @Override
    public void saveBreaksData(String break_title,int year,int month,int day,int hour,int min,String am_pm) {
        // This data will be received from add breaks dialog

        String date = year + "-" + month + "-" +day;
        String time = hour + ":" + min + " " +am_pm;

        databasehelper = new DatabaseHelper(BreaksPage.this);
        databasehelper.addBreak(break_title,date,time);

        Cursor cursor = databasehelper.getALlBreaksData();
        Breaks_Storage_ModelArrayList = new ArrayList<Breaks_Storage_Model>();
        if(cursor.getCount() == 0){
            Toast.makeText(this.getApplicationContext(),"No Breaks data to display", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                Log.println(Log.ERROR,"DB_DATA",cursor.getString(1));
                Breaks_Storage_ModelArrayList.add(new Breaks_Storage_Model(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3)));
            }
        }
        breakAdapter = new BreakAdapter(this, Breaks_Storage_ModelArrayList);
        breakRV.setAdapter(breakAdapter);

    }

    @Override
    public void updateBreaksData(String break_title,int year,int month,int day,int hour,int min,String am_pm, int position){

        String date = year + "-" + month + "-" +day;
        String time = hour + ":" + min + " " +am_pm;
        SharedPreferences.Editor editor = getSharedPreferences("breaks_data",MODE_PRIVATE).edit();
        SharedPreferences sharedPreferences = getSharedPreferences("breaks_data",MODE_PRIVATE);
        String breaks_data = sharedPreferences.getString("breaks_list",null);
        Type type = new TypeToken<ArrayList<Breaks_Storage_Model>>(){

        }.getType();
        Gson gson = new Gson();
        System.out.println(position);
        Breaks_Storage_ModelArrayList = (breaks_data == null) ? Breaks_Storage_ModelArrayList : gson.fromJson(breaks_data,type);
        System.out.println(date);
      //  Breaks_Storage_ModelArrayList.set(position,new Breaks_Storage_Model(break_title, time, date));
        breakRV.getAdapter().notifyItemChanged(position);

        //System.out.println(Breaks_Storage_ModelArrayList.size() + " this is the count of breaks ");

        String breaks_list = gson.toJson(Breaks_Storage_ModelArrayList);
        editor.putString("breaks_list",breaks_list);
        editor.apply();
        breakAdapter = new BreakAdapter(this, Breaks_Storage_ModelArrayList);
        breakRV.setAdapter(breakAdapter);


    }


}