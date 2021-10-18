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

public class ResearchersSignUpPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_researchers_sign_up_page);
        mAuth = FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        TextView signInTextView= findViewById(R.id.GoToSignIn);
        Button signUpPage = findViewById(R.id.signup);
    }





    public void GoToSignIn (View view)
    {
        Intent intent = new Intent( ResearchersSignUpPage.this, ResearchersLoginPage.class);
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
                    Toast.makeText(ResearchersSignUpPage.this, "Sign Up successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (ResearchersSignUpPage.this,ResearchersDashboard.class);
                    intent.putExtra("UserId",userID);
                    startActivity(intent);
                }

                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        Toast.makeText(getApplicationContext(), "This account is already registered", Toast.LENGTH_SHORT).show();

                    else Toast.makeText(ResearchersSignUpPage.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

}