//package com.example.datool;
//
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.Map;
//
//public class addField {
//    public void addField(Map map, String collection, String document){
//
//        if(document!=null){
//            DocumentReference mUserDoc = FirebaseFirestore.getInstance().collection(collection).document(document);
//
//            mUserDoc.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getApplicationContext(), "not done :( ", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        else
//        {
//            Toast.makeText(getApplicationContext(), "not done because document is null :( "+document, Toast.LENGTH_SHORT).show();
//        }
//}}
//
