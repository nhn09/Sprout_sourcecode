package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class csv_download extends AppCompatActivity {

    private String UserId;
    private ArrayList<String> content = new ArrayList<String>();
    private static final String TAG = "csv_download";

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
    public void createContent(String collection){
        ArrayList<String> alldata= new ArrayList<String>();
        FirebaseFirestore.getInstance().collection(collection).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
            //if successful
                //make a list of document snapsots
                List<DocumentSnapshot> snapshotsList;

                //extract documents from queryDocumentSnapshots
                snapshotsList=queryDocumentSnapshots.getDocuments();
                //iterate through the list of docs

                for(DocumentSnapshot each : snapshotsList)
                {
                    Map<String, Object> data = each.getData();
                    for (Map.Entry<String,Object> entry : data.entrySet()){
                        if(entry.getValue()!=null){
                            Log.d(TAG,entry.getValue().toString());
                        }
                    }
                    //String dataLine =
                    alldata.add(each.get("Q_3").toString()+","+each.get("Q_3").toString());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //if failed
                Toast.makeText(csv_download.this,"Permission not Granted"+e,Toast.LENGTH_SHORT).show();
            }
        });

 content = alldata;
 // TODO: 8/4/2021 add data to file creator and write file with newline.

    }

    public void download(View view){
        EditText filename = findViewById(R.id.filename);
        String fileName = filename.getText().toString();
        createContent("Users");

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
