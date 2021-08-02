package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class checkboxQstn extends AppCompatActivity {

    private String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_qstn);

        Intent intent;
        intent = getIntent();
        UserId = intent.getStringExtra("UserId");
    }

    Map<String, Object> dataToSave = new HashMap<String,Object>();


    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.pancake:
                if (checked)
                {
                    Button Chosen = findViewById(R.id.pancake) ;
                    String check_ans = Chosen.getText().toString();
                    dataToSave.put("Q_2",check_ans);
                }
                break;


            case R.id.MuttonKabab:
                if (checked)
                {
                    Button Chosen = findViewById(R.id.MuttonKabab) ;
                    String check_ans = Chosen.getText().toString();
                    dataToSave.put("Q_2",check_ans);
                }
                    break;
            case R.id.shwarma:
                if (checked)
                {
                    Button Chosen = findViewById(R.id.shwarma) ;
                    String check_ans = Chosen.getText().toString();
                    dataToSave.put("Q_2",check_ans);
                }
                    break;

        }

        DocumentReference mUserDoc = FirebaseFirestore.getInstance().collection("Users").document(UserId).collection("Answers").document("2");
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
        Intent intent = new Intent( checkboxQstn.this, q_3.class);
        startActivity(intent);

        //Toast.makeText(MainActivity.this, "Its working", Toast.LENGTH_SHORT).show();

    }

}