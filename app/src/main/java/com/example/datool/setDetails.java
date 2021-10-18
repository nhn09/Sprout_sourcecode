package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class setDetails extends AppCompatActivity {
    Integer docname=1;
    String UserId;
    String type;
    String rID;
    public int questionNumber=10;
    int serial;
    String qBody; String op1; String op2; String op3; String op4;
    public Map<String, Object> data = new HashMap<>();
    private LinearLayout layoutToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_details);

        layoutToAdd = findViewById(R.id.linearlayoutforEdit);
        LayoutInflater inflater = getLayoutInflater();

        Intent intent;
        intent = getIntent();
        UserId = intent.getStringExtra("UserId");
        rID = intent.getStringExtra("rId");
        questionNumber =  intent.getIntExtra("qstnNumber", 3);
        Toast.makeText(getApplicationContext(),"question number"+questionNumber,Toast.LENGTH_SHORT).show();

        //

        //---------------------------------

        //-----------------------------------






        for( int i=1;i<= questionNumber;i++){




            serial = i;
            View para = inflater.inflate(R.layout.eachqedit, null);
            Button buttonSub = (Button) para.findViewById(R.id.submitDetails);
            buttonSub.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    qBody = ((EditText) para.findViewById(R.id.qstnbody)).getText().toString();
                    op1 = ((EditText) para.findViewById(R.id.op1)).getText().toString();
                    op2 = ((EditText) para.findViewById(R.id.op2)).getText().toString();
                    op3 = ((EditText) para.findViewById(R.id.op3)).getText().toString();
                    op4 = ((EditText) para.findViewById(R.id.op4)).getText().toString();
                    //serial = i;

                    String docName = Integer.toString(docname);
                    DocumentReference mResearcherDoc = FirebaseFirestore.getInstance().collection("Researchers").document(UserId).collection("AllResearches").document(rID).collection("EachQuestions").document(docName);

                    data.put("qBody", qBody);
                    data.put("type", type);
                    data.put("op1", op1);
                    data.put("op2", op2);
                    data.put("op3", op3);
                    data.put("op4", op4);

                    mResearcherDoc.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "DocumentSnapshot successfully updated!", Toast.LENGTH_SHORT).show();
                            docname++;
                            buttonSub.setEnabled(false);
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error adding document" + e, Toast.LENGTH_SHORT).show();
                                }
                            });



                }
            });

            switch (serial) {
                case 1:  para.setId(R.id.q1);
                case 2:  para.setId(R.id.q2);
                case 3:  para.setId(R.id.q3);
                case 4:  para.setId(R.id.q4);
                case 5:  para.setId(R.id.q5);
                case 6:  para.setId(R.id.q6);
                case 7:  para.setId(R.id.q7);
                case 8:  para.setId(R.id.q8);
                case 9:  para.setId(R.id.q9);
                case 10:  para.setId(R.id.q10);
                case 11:  para.setId(R.id.q11);
                case 12:  para.setId(R.id.q12);

            }



            layoutToAdd.addView(para);





        }






    }





    public void onSubmit( View view){
        addField(serial);

    }


    //-----------------------------

    public void onTypeClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio:
                if (checked)
                {
    type = "radio";
                }
                break;


            case R.id.check:
                if (checked)
                {

                    type = "check";
                }
                break;
            case R.id.text:
                if (checked)
                {
                    type = "text";

                }
                break;



        }







    }


    public void addField(int serial) {
        qBody = ((EditText) findViewById(R.id.qstnbody)).getText().toString();
        op1 = ((EditText) findViewById(R.id.op1)).getText().toString();
        op2 = ((EditText) findViewById(R.id.op2)).getText().toString();
        op3 = ((EditText) findViewById(R.id.op3)).getText().toString();
        op4 = ((EditText) findViewById(R.id.op4)).getText().toString();
        //serial = i;
        Integer docname = 1;
        String docName = Integer.toString(docname);
        DocumentReference mResearcherDoc = FirebaseFirestore.getInstance().collection("Researchers").document(UserId).collection("AllResearches").document(rID).collection("EachQuestions").document(docName);

        data.put("qBody", qBody);
        data.put("type", type);
        data.put("op1", op1);
        data.put("op2", op2);
        data.put("op3", op3);
        data.put("op4", op4);

        mResearcherDoc.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "DocumentSnapshot successfully updated!", Toast.LENGTH_SHORT).show();

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error adding document" + e, Toast.LENGTH_SHORT).show();
                    }
                });


    }


    public void Done(View view){
        Intent intent = new Intent(setDetails.this,ResearchersDashboard.class);
        startActivity(intent);

    }
}


