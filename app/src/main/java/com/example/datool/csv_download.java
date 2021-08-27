package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class csv_download extends AppCompatActivity {


    //variables
    private String UserId;
    private static final String TAG = "csv_download";
    List<String[]> alldata = new ArrayList<String[]>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csv_download);
        //get intents from previous page
        Intent intent;
        intent = getIntent();
        UserId = intent.getStringExtra("UserId");
    }





    //create the string for csv body
    public void createContent(String collection){

        FirebaseFirestore.getInstance().collection(collection).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //if successful
                //make a list of document snapsots
                List<DocumentSnapshot> snapshotsList;

                //extract documents from queryDocumentSnapshots
                snapshotsList = queryDocumentSnapshots.getDocuments();
                //iterate through the list of docs


                for (DocumentSnapshot each : snapshotsList) {
                    //inside a single user

                    List<String> ansList = new ArrayList<String>();
                    Map<String, Object> map = each.getData();
                    if (map != null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            ansList.add(entry.getValue().toString());
                        }
                    }


                    //convert to string array


                    String[] ansArray  = (new String[ansList.size()]);
                    ansList.toArray(ansArray);
                    //add each users data as strings in a string list
                    alldata.add(ansArray);

                }




            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //if failed
                Toast.makeText(csv_download.this,"Permission not Granted"+e,Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void saveText (String fileName){
        try (

                FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath(),fileName));
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                CSVWriter writer = new CSVWriter(osw)) {
                writer.writeAll(alldata);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void download(View view) {

        String[] header = new String[]{"q_1", "q_2", "q_3", "q_4", "q_5", "q_6", "q_7", "q_8", "q_9", "q_10"};
        alldata.add(header);
        createContent("Users");
        EditText filename = findViewById(R.id.filename);
        String fileName = filename.getText().toString();
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT);


        if ((fileName) != null) {


            saveText(fileName);
        }
    }



    public void nextPage(View view){
        Intent intent = new Intent( csv_download.this, q_3.class);
        intent.putExtra("UserId",UserId);
        startActivity(intent);
    }



}
