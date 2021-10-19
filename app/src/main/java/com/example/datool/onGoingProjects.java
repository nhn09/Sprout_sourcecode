package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class onGoingProjects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_going_projects);

        TextView Name;
        Button Research;
        Research=findViewById(R.id.research_2);
        FirebaseAuth fAuth;
        FirebaseFirestore fStore;
        fAuth = FirebaseAuth.getInstance();
        fStore=  FirebaseFirestore.getInstance();
        String userID;
        userID= fAuth.getCurrentUser().getUid();
        FirebaseFirestore dbroot;
        dbroot=FirebaseFirestore.getInstance();
        DocumentReference documentReference= dbroot.collection("allResearchBrief").document("userID");
        documentReference.addSnapshotListener(onGoingProjects .this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e)
            {
                Research.setText(documentSnapshot.getString("Name"));
            }

        });






    }

    public void addNewProject(){

    }



}