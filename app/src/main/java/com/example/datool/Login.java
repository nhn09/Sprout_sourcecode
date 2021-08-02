package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

    }


    public void GoToSignUp(View view){  // goes back to sign Up

        Intent intent = new Intent(Login.this,Signup.class);
        startActivity(intent);
    }




    public void signIn(View view)
    {
        EditText signInEmailEditText = findViewById(R.id.loginMail);
        EditText signInPasswordEditText= findViewById(R.id.loginPass);

        // Store email and password from text input
        String email =  signInEmailEditText.getText().toString().trim(); //trim to ignore white space
        String password = signInPasswordEditText.getText().toString().trim();

        // mail validity check

        if(email.isEmpty())
        {

            //if the entry is empty
            signInEmailEditText.setError("Enter an email");
            signInEmailEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            // if @ is not used
            signInEmailEditText.setError("Enter a valid email address");
            signInEmailEditText.requestFocus();
            return;


        }

        if(password.isEmpty())
        {

            signInPasswordEditText.setError("Enter a password");
            signInPasswordEditText.requestFocus();
            return;
        }

        if(password.length()<6)
        {
            // if length less than 6 characters
            signInPasswordEditText.setError("Password should be more than 6 character long");
            signInPasswordEditText.requestFocus();
            return;

        }

        //login main task

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Login.this, "Login successful.", Toast.LENGTH_SHORT).show();
                    String userID = String.valueOf(mAuth.getCurrentUser().getUid());

                    Intent intent = new Intent(Login.this,q_3.class);
                    intent.putExtra("UserId",userID);
                    startActivity(intent);
                }
            }
        });



    }
}