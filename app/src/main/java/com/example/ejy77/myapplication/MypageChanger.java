package com.example.ejy77.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ejy77 on 2017-11-21.
 */

public class MypageChanger extends AppCompatActivity

{

    Button btncustomer, btnmanager;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);


        btncustomer = (Button)findViewById(R.id.btncustomer);
        btnmanager = (Button)findViewById(R.id.btnmanager);
        id = getIntent().getStringExtra("login");

        btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), MypageCustomer.class).putExtra("login", id));

            }
        });

        btnmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MypageManager.class);
                startActivity(intent);
            }
        });


    }
}
