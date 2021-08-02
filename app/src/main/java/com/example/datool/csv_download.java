package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class csv_download extends AppCompatActivity {

    private String UserId;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csv_download);
        Intent intent;
        intent = getIntent();
        UserId = intent.getStringExtra("UserId");
    }

    private void saveText (String fileName,String content){

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),fileName);


        try {
           FileOutputStream fos = new FileOutputStream(file);


            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"File Not Found",Toast.LENGTH_SHORT).show();
        }
         catch (IOException e) {
                e.printStackTrace();
             Toast.makeText(this,"Error saving the file.",Toast.LENGTH_SHORT).show();
        }



    }
    public void createContent(String collection, String document){

        DocumentReference mUserDoc = FirebaseFirestore.getInstance().collection(collection).document(document);
        mUserDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists())
                {
                    content = documentSnapshot.getString("Q_3")+","+documentSnapshot.getString("Q_4");
                }
            }
        });


    }

    public void download(View view){
        EditText filename = findViewById(R.id.filename);
        String fileName = filename.getText().toString();
        createContent("Users",UserId);

        //String contentpre = "1,2,2,4/n3,4,5,6";

        if( (fileName)!=null){

            if(content!=null){

                saveText(fileName,content);
            }
            else{
                Toast.makeText(this,"Please enter content",Toast.LENGTH_SHORT);
            }



        }
        else {
            Toast.makeText(this,"Please enter filename ",Toast.LENGTH_SHORT);
        }
    }



    public void nextPage(View view){
        Intent intent = new Intent( csv_download.this, q_3.class);
        intent.putExtra("UserId",UserId);
        startActivity(intent);
    }



}
