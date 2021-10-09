package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Current_Research_Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current__research__options);
        ImageButton Back_1=findViewById(R.id.back_1);
        ImageButton Questions=findViewById(R.id.questions);
        Back_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_back_1=new Intent(Current_Research_Options.this,Current_Research.class);
                startActivity(go_back_1);
            }
        });
        Questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Current_Research_Options.this,Research_Questions.class);
                startActivity(intent);
            }
        });
    }
}