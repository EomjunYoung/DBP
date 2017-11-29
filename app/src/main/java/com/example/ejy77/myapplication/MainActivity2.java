package com.example.ejy77.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ejy77.myapplication.Recognition.Loadimage;
import com.example.ejy77.myapplication.Recognition.search;
import com.example.ejy77.myapplication.itemManage.ItemCursorAdapter;
import com.example.ejy77.myapplication.itemManage.ItemManager;
import com.example.ejy77.myapplication.itemManage.Solditem;

import static com.example.ejy77.myapplication.R.id.btn1;
import static com.example.ejy77.myapplication.R.id.btn2;
import static com.example.ejy77.myapplication.R.id.btn3;
import static com.example.ejy77.myapplication.R.id.btn4;

public class MainActivity2 extends AppCompatActivity {


    Button button, button2, button3, button4;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);


        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        id = getIntent().getStringExtra("login");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Solditem.class).putExtra("login", id));

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ItemManager.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), MypageChanger.class).putExtra("login", id));

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Loadimage.class);
                startActivity(intent);
            }
        });

    }


}
