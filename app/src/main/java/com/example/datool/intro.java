package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button Test = findViewById(R.id.researcher);
        Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent( intro.this, Researcher_Dashboard.class);
                startActivity(intent1);
            }
        });
    }

    public void onClickCont(View view){
        Intent intent = new Intent( intro.this, describe.class);
        startActivity(intent);

    }



}