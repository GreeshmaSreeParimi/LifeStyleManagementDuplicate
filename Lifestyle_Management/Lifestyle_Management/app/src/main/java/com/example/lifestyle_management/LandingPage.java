package com.example.lifestyle_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LandingPage extends AppCompatActivity {
    CardView date_card, break_card, routine_card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        date_card =  findViewById(R.id.date_card);
        break_card = findViewById(R.id.breaks_card);

        DatabaseHelper databasehelper = new DatabaseHelper(LandingPage.this);
        boolean doesTableExist = databasehelper.doesTableExist("BREAKS_TABLE");
        if(!doesTableExist){
            databasehelper.createBreaksTable();
            databasehelper.addBreak("Work Break", "2022-12-1","2:30 pm", 121);
            databasehelper.addBreak("Gym Break", "2022-12-9","6:30 am", 162);
            databasehelper.addBreak("Water Break", "2022-11-30","11:30 am", 741);
       }

        date_card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               openCurrentDatePage();

            }
        });
        break_card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              openBreaksPage();

            }
        });
    }
    public void openCurrentDatePage(){
        Intent intent = new Intent(this,CurrentDatePage.class);
        startActivity(intent);
    }
    public void openBreaksPage(){
        Intent intent = new Intent(this,BreaksPage.class);
        startActivity(intent);
    }
    
   @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("logged", true)) {
            finish();
        }
        else
        {
            moveTaskToBack(true);
        }

    }
}
