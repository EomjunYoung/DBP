package com.example.ejy77.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

    }
}
