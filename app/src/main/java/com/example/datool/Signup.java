package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static android.util.Patterns.EMAIL_ADDRESS;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    String userID;
    private String[] qstnPlaces = {"q_1", "q_2","q_3","q_4","q_5","q_6","q_7","q_8","q_9","q_10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();




        TextView signInTextView= findViewById(R.id.GoToSignIn);
        Button signUpPage = findViewById(R.id.signup);
    }

    // to save each data
    Map<String, Object> dataToSave = new HashMap<String,Object>();
    //function to add fields
    public void addField(Map map, String collection, String document){

        if(document!=null){
            DocumentReference mUserDoc = FirebaseFirestore.getInstance().collection(collection).document(document);

            mUserDoc.set(map, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(),
                            "done",
                            Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "not done :( ", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "not done because document is null :( "+document, Toast.LENGTH_SHORT).show();
        }



    }

    //create all the answers with space holders

    public void placeholder(String  userID) {
        for (String i : qstnPlaces) {
            dataToSave.put(i,"Not answered yet");
        }
        addField(dataToSave,
                "Users",
                userID);

    }




    public void GoToSignIn (View view)
    {
        Intent intent = new Intent( Signup.this, Login.class);
        startActivity(intent);
    }

    public void signUp (View view)
    {
        EditText signUpEmailEditText = findViewById(R.id.signUpMail);
        EditText signUpPasswordEditText= findViewById(R.id.signUpPass);

        // Store email and password from text input
        String email =  signUpEmailEditText.getText().toString().trim(); //trim to ignore white space
        String password = signUpPasswordEditText.getText().toString().trim();

        // mail validity check

        if(email.isEmpty())
        {

            //if the entry is empty
            signUpEmailEditText.setError("Enter an email");
            signUpEmailEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            // if @ is not used
            signUpEmailEditText.setError("Enter a valid email address");
            signUpEmailEditText.requestFocus();
            return;


        }

        if(email.isEmpty())
        {

            signUpPasswordEditText.setError("Enter a password");
            signUpEmailEditText.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            // if length less than 6 characters
            signUpPasswordEditText.setError("Password should be more than 6 character long");
            signUpPasswordEditText.requestFocus();
            return;

        }


        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {


                    String userID = String.valueOf(mAuth.getCurrentUser().getUid());
                    Toast.makeText(Signup.this, "Sign Up successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (Signup.this,MainActivity.class);
                    intent.putExtra("UserId",userID);
                    placeholder(userID);
                    startActivity(intent);
                }

                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        Toast.makeText(getApplicationContext(), "This account is already registered", Toast.LENGTH_SHORT).show();

                   else Toast.makeText(Signup.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

}