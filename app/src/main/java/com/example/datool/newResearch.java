package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class newResearch extends AppCompatActivity {

    private String UserId;
    public String rId;
    public int rQuestionNumberData;
    public Map<String, Object> data = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_research);

        Intent intent;
        intent = getIntent();
        UserId = intent.getStringExtra("UserId");



    }





    public void addField() {


            DocumentReference mResearcherDoc = FirebaseFirestore.getInstance().collection("Researchers").document(UserId).collection("allResearchBrief").document(rId);
            //rId =mResearcherDoc.getId();
            data.put("r_id", rId);

            mResearcherDoc.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(),"DocumentSnapshot successfully updated!",Toast.LENGTH_SHORT).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error adding document" + e,Toast.LENGTH_SHORT).show();
                        }
                    });



    }

    public void genID(View view){

        DocumentReference mResearcherDoc = FirebaseFirestore.getInstance().collection("Researchers").document(UserId).collection("allResearchBrief").document();
        rId =mResearcherDoc.getId();
        TextView showID = findViewById(R.id.showID);
        showID.setText(rId);
        EditText rName = findViewById(R.id.rName);
        EditText rDescription = findViewById(R.id.descrip);
        EditText rQuestionNumber = findViewById(R.id.qstnnum);
        rName.setVisibility(View.VISIBLE);
        rDescription.setVisibility(View.VISIBLE);
        rQuestionNumber.setVisibility(View.VISIBLE);
        showID.setVisibility(View.VISIBLE);

    }





    public void editDetails(View view){
        EditText rName = findViewById(R.id.rName);
        EditText rDescription = findViewById(R.id.descrip);
        EditText rQuestionNumber = findViewById(R.id.qstnnum);
        String rNameData = rName.getText().toString();
        String rDescriptionData = rDescription.getText().toString();


        rQuestionNumberData = Integer.parseInt(rQuestionNumber.getText().toString());





        data.put("r_describe",rDescriptionData);
        data.put("r_name",rNameData );
        data.put("r_qnumber", rQuestionNumberData);


        addField();
        Intent intent = new Intent( newResearch.this, setDetails.class);
        intent.putExtra("UserId",UserId);
        intent.putExtra("qstnNumber",rQuestionNumberData);
        intent.putExtra("rId",rId);
        startActivity(intent);

    }
}