package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TheNextPage01 extends AppCompatActivity {

    private String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_next_page01);

        Intent intent;
        intent = getIntent();
        UserId = intent.getStringExtra("UserId");
    }

    public void prevPage(View view)  // triggers when Next button is called
    {

        //the activity after clicking next button
        Intent intent = new Intent( TheNextPage01.this, MainActivity.class);
        startActivity(intent);

        //Toast.makeText(MainActivity.this, "Its working", Toast.LENGTH_SHORT).show();

    }

    public void Submit (View View)
    {
        EditText answer = findViewById(R.id.answerfield);
        String answerField = answer.getText().toString();

        if(answerField.isEmpty()) {return;}
        Map<String, Object> dataToSave = new HashMap<String,Object>();
        dataToSave.put("FirstName",answerField);
        DocumentReference mUserDoc = FirebaseFirestore.getInstance().collection("Users").document(UserId).collection("Answers").document("1");
        mUserDoc.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void nextPage(View view)  // triggers when Next button is called
    {

        //the activity after clicking next button
        Intent intent = new Intent( TheNextPage01.this, checkboxQstn.class);
        intent.putExtra("UserId",UserId);
        startActivity(intent);

        //Toast.makeText(MainActivity.this, "Its working", Toast.LENGTH_SHORT).show();

    }

}