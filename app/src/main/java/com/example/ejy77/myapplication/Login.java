package com.example.ejy77.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ejy77.myapplication.DB.DBHelper;

/**
 * Created by ejy77 on 2017-10-04.
 */

public class Login extends AppCompatActivity
{


    Button btn0, btn1;
    EditText et0, et1;
    DBHelper dbHelper;
    static String userid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn1 = (Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MemberRegister.class);
                startActivity(intent);
            }
        });


    }
}
