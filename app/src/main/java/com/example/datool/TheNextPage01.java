package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TheNextPage01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_next_page01);
    }

    public void prevPage(View view)  // triggers when Next button is called
    {

        //the activity after clicking next button
        Intent intent = new Intent( TheNextPage01.this, MainActivity.class);
        startActivity(intent);

        //Toast.makeText(MainActivity.this, "Its working", Toast.LENGTH_SHORT).show();

    }
}