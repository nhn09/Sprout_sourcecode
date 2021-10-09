package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Current_Research extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current__research);
        ImageButton Back_1=findViewById(R.id.back_0);
        Button Research = findViewById(R.id.research_1);
        Button Add_New_Research= findViewById(R.id.add_new);




        Back_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_back_1=new Intent(Current_Research.this,Researcher_Dashboard.class);
                startActivity(go_back_1);
            }
        });
        Research.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Current_Research.this,Current_Research_Options.class);
                startActivity(intent);
            }
        });

        Add_New_Research.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }

        });
    }
}