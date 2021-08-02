package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class q_6 extends AppCompatActivity {

    private String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_6);

        Intent intent;
        intent = getIntent();
        UserId = intent.getStringExtra("UserId");
    }

    Map<String, Object> dataToSave = new HashMap<String,Object>();



    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.notatall:
                if (checked)
                {
                    Button Chosen = findViewById(R.id.notatall) ;
                    String check_ans = Chosen.getText().toString();
                    dataToSave.put("Q_6",check_ans);
                }
                break;


            case R.id.several:
                if (checked)
                {
                    Button Chosen = findViewById(R.id.several) ;
                    String check_ans = Chosen.getText().toString();
                    dataToSave.put("Q_6",check_ans);
                }
                break;
            case R.id.morethanhalf:
                if (checked)
                {
                    Button Chosen = findViewById(R.id.morethanhalf) ;
                    String check_ans = Chosen.getText().toString();
                    dataToSave.put("Q_6",check_ans);
                }
                break;

            case R.id.everyday:
                if (checked)
                {
                    Button Chosen = findViewById(R.id.everyday) ;
                    String check_ans = Chosen.getText().toString();
                    dataToSave.put("Q_6",check_ans);
                }
                break;

        }

        DocumentReference mUserDoc = FirebaseFirestore.getInstance().collection("Users").document(UserId).collection("Answers").document("6");
        mUserDoc.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void nextPage(View view)  // triggers when Next button is called
    {

        //the activity after clicking next button
        Intent intent = new Intent( q_6.this, App_Usage.class);
        intent.putExtra("UserId",UserId);
        startActivity(intent);

        //Toast.makeText(MainActivity.this, "Its working", Toast.LENGTH_SHORT).show();

    }

}