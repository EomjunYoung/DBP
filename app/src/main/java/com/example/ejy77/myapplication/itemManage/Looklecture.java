package com.example.ejy77.myapplication.itemManage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejy77.myapplication.R;

/**
 * Created by ejy77 on 2017-11-15.
 */

public class Looklecture extends AppCompatActivity
{

    TextView text;
    Button btnadd, btndel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);

        btnadd = (Button)findViewById(R.id.btnadd);
        btndel = (Button)findViewById(R.id.btndel);
        text = (TextView)findViewById(R.id.text);

    }





}
