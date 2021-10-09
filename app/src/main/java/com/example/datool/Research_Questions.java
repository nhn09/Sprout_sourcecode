package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Research_Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research__questions);
        ImageButton Back = findViewById(R.id.back_2);
        Button Add_new_questions=findViewById(R.id.add_new);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Research_Questions.this,Current_Research_Options.class);
                startActivity(intent);
            }
        });
        Add_new_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Research_Questions.this,Researcher_Enter_Questions.class);
                startActivity(intent1);
            }
        });

    }
}