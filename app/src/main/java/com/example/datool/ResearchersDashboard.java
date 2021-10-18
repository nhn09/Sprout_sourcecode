package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ResearchersDashboard extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_researchers_dashboard);

        Intent intent;
        intent = getIntent();
        UserId = intent.getStringExtra("UserId");
    }

    public void Ongoing(View view){

        Intent intent = new Intent( ResearchersDashboard.this, onGoingProjects.class);
        intent.putExtra("UserId",UserId);
        startActivity(intent);
    }

    public void addNew (View view){

        Intent intent = new Intent( ResearchersDashboard.this, newResearch.class);
        intent.putExtra("UserId",UserId);
        startActivity(intent);

    }
}