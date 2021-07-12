package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.datool.R.id.next;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



      
    }



    public void onButtonClick (View view){
         EditText edtTextNameFirst = findViewById(R.id.editTextFirst);
         EditText edtTextNameLast = findViewById(R.id.edtTextLast);
         EditText edtTextMail = findViewById(R.id.edtTextMail);

         TextView textFisrtName = findViewById(R.id.textView1);
         TextView textLastName = findViewById(R.id.textView2);
         TextView textemail = findViewById(R.id.textView3);

         textFisrtName.setText(edtTextNameFirst.getText().toString());
         textLastName.setText(edtTextNameLast.getText().toString());
         textemail.setText(edtTextMail.getText().toString());
     }



    public void nextPage(View view)  // triggers when Next button is called
     {

          //the activity after clicking next button
         Intent intent = new Intent( MainActivity.this, TheNextPage01.class);
         startActivity(intent);

         //Toast.makeText(MainActivity.this, "Its working", Toast.LENGTH_SHORT).show();

     }
}