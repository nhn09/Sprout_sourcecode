package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseRole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
    }

    public void ResearcherRole(View view){
        Intent intent = new Intent (ChooseRole.this,ResearchersSignUpPage.class);
        startActivity(intent);
    }

    public void surveyRole(View view){
        Intent intent = new Intent (ChooseRole.this,Signup.class);
        startActivity(intent);
    }
}

