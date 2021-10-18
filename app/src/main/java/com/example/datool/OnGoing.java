package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Document;

import javax.annotation.Nullable;

public class OnGoing extends AppCompatActivity {

    TextView name;
    Button Research;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_going);

     FirebaseAuth fAuth;
    FirebaseFirestore fStore;
     fAuth = FirebaseAuth.getInstance();
     fStore=  FirebaseFirestore.getInstance();
        String userID;
     userID= fAuth.getCurrentUser().getUid();
     DocumentReference documentReference= fStore.collection("users").document(userID);
     documentReference.addSnapshotListener(OnGoing.this,new EventListener<DocumentSnapshot>() {
         @Override
         public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e)
         {
             name.setText(documentSnapshot.getString("Name"));
         }

     });










    }}