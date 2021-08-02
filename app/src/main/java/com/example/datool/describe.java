package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class describe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation2_k);
    }

    public void onClickServey(View view){
        Intent intent = new Intent( describe.this, Signup.class);
        startActivity(intent);
    }
}