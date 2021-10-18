package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class inflatepractice extends AppCompatActivity {

    private LinearLayout layoutToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflatepractice);

        layoutToAdd = (LinearLayout) findViewById(R.id.linearlayout);

        LayoutInflater inflater = getLayoutInflater();


        String [] types = {"type-1","type-2","type-1","type-1","type-3",};



        for ( String each : types){
            switch(each) {
                case "type-1":
                    CheckBox checkCard1 = findViewById(R.id.checkBox1);
                    CheckBox checkCard2 = findViewById(R.id.checkBox2);
                    CheckBox checkCard3 = findViewById(R.id.checkBox3);
                    CheckBox checkCard4 = findViewById(R.id.checkBox4);
                    TextView checkCardQues = findViewById(R.id.qstnCheck);


                    View check = inflater.inflate(R.layout.q_t_check, null);
                    layoutToAdd.addView(check);
                    break;
                case "type-2":
                    RadioButton radioCard1 = findViewById(R.id.radioButton);
                    RadioButton radioCard2 = findViewById(R.id.radioButton2);
                    RadioButton radioCard3 = findViewById(R.id.radioButton3);
                    RadioButton radioCard4 = findViewById(R.id.radioButton4);
                    TextView radioCardQues = findViewById(R.id.qstnRadio);
                    View radio = inflater.inflate(R.layout.q_t_radio, null);
                    layoutToAdd.addView(radio);
                    break;
                case "type-3":
                    TextView paraCardQues = findViewById(R.id.qstnPara);
                    EditText paraEdit = findViewById(R.id.paraField);

                    View para = inflater.inflate(R.layout.q_t_paragraph, null);
                    layoutToAdd.addView(para);
                    break;

            }

        }



        };
    }

//-----------------------------------------------------------

