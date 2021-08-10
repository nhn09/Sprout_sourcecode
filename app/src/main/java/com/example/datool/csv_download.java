package com.example.datool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class csv_download extends AppCompatActivity {


    //variables
    private String UserId;
    private String content;
    private static final String TAG = "csv_download";
    Map <String,Object> research_data= new HashMap<>();
    public String description;
    public StringBuilder eachDataCombine = new StringBuilder();
    public int qstn_no=10,researchers_id;
    ArrayList<String> headerFields = new ArrayList<>();
    public StringBuilder csvHeader = new StringBuilder();
    ArrayList<String> alldata= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csv_download);
        //get intents from previous page
        Intent intent;
        intent = getIntent();
        UserId = intent.getStringExtra("UserId");
    }

    //for download
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

    //retrieving questions

    public void questionCollector() {
        DocumentReference q_doc = FirebaseFirestore.getInstance().collection("allResearchIDs").document("12345");
        // store all basic info of a research
        q_doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + documentSnapshot.getData());
                    //take all values as a hashmap from entire doc
                    research_data = documentSnapshot.getData();
                    description = documentSnapshot.getString("description").toString();
                    researchers_id = documentSnapshot.getLong("researchers_id").intValue();
                    qstn_no = documentSnapshot.getLong("qstn_no").intValue();
                } else {
                    Toast.makeText(csv_download.this, "No such Document", Toast.LENGTH_SHORT);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "get failed with " + e);
            }
        });
        //---------------------------------------------------//
    }

    //make the array of qstn names
    public void qstnFieldArrayMaker(){

        for (int i = 1; i <= qstn_no; i++){
            String eachHead = "q_"+i;
            headerFields.add(eachHead);
        }
    }

    //make the header of csv
    private void headerMaker(){
        Iterator<String> it = headerFields.iterator();
            while (it.hasNext()) {
               csvHeader.append(it.next()+",");
            }
            csvHeader.append("\n");


    }


    //create the string for csv body
    public void createContent(String collection){

        //make a string of questions as csv header


        FirebaseFirestore.getInstance().collection(collection).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //if successful
                //make a list of document snapsots
                List<DocumentSnapshot> snapshotsList;

                //extract documents from queryDocumentSnapshots
                snapshotsList=queryDocumentSnapshots.getDocuments();
                //iterate through the list of docs

                //questionCollector();
                qstnFieldArrayMaker();
                headerMaker();

                for(DocumentSnapshot each : snapshotsList)
                {
                    //inside a single user
                    //if any field is not present in any user but present in question set, fill will NaN

                    Iterator<String> it = headerFields.iterator();

                        while (it.hasNext()) {
                            //TODO:field present ache kina check kora hoy nai
                            if (each.get(it.next()) != null) {
                                eachDataCombine.append(each.get(it.next()).toString() + ",");
                            } else {
                                eachDataCombine.append("NaN" + ",");
                            }
                        }

                    //last element
                    //add new line character
                    eachDataCombine.append("\n");
                    //adding each info of one user as comma separated string to the Array list of strings
                    alldata.add(eachDataCombine.toString());
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




    public void download(View view){

        createContent("Users");
        content = csvHeader.toString()+"\n"+ TextUtils.join(", ", alldata);
        EditText filename = findViewById(R.id.filename);
        String fileName = filename.getText().toString();
        //createContent("Users");
        Toast.makeText(this,"the content "+content,Toast.LENGTH_SHORT);



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
