package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static com.example.datool.R.id.ongoing_research;

public class Researcher_Dashboard extends AppCompatActivity {

    ImageButton Ongoing_Research;
    ImageButton Add_new_Research;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_researcher__dashboard);

         Ongoing_Research = findViewById(ongoing_research);
         Add_new_Research = findViewById(R.id.add_new_research);

         Add_new_Research.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
              //Creates new research button and pages
             }
         });

    }

    public void NextPage(View view) {
        Intent intent1 = new Intent(Researcher_Dashboard.this,Current_Research.class);
         startActivity(intent1);
    }
}