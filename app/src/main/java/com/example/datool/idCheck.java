package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class idCheck extends AppCompatActivity {

    private static  String TAG ;
    boolean isExist=false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_check);

    }






    public void Proceed(View view){
        EditText IDCheckNumber = findViewById(R.id.IDCheck);
        String IdString= IDCheckNumber.getText().toString();
        if( TextUtils.isEmpty(IDCheckNumber.getText())){
            /**
             *   You can Toast a message here that the Username is Empty
             **/

            IDCheckNumber.setError( "Research ID is required!" );

        }else {

            FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
            DocumentReference docIdRef = rootRef.collection("allResearchIDs").document(IdString);
            docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            isExist=true;
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            isExist=false;
                            IDCheckNumber.setError( "Research ID is wrong.Please collect it from respective researcher." );
                            Log.d(TAG, "Document does not exist!");
                        }
                    } else {

                        Log.d(TAG, "Failed with: ", task.getException());
                    }
                }
            });

        }

    }



}