package com.example.ejy77.myapplication;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ejy77.myapplication.DB.DBHelper;

/**
 * Created by ejy77 on 2017-11-21.
 */

public class MypageCustomer extends AppCompatActivity
{

    Button btncash;
    DBHelper dbHelperUser;
    SQLiteDatabase db;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        dbHelperUser = new DBHelper(getApplicationContext(), "logindb2.db", null, 1);
        btncash = (Button)findViewById(R.id.btncash);
        id = getIntent().getStringExtra("login");
        btncash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MypageCustomer.this);
                ad.setTitle("캐시충전");
                ad.setMessage("캐시충전!");

                final EditText et = new EditText(MypageCustomer.this);
                ad.setView(et);


                ad.setPositiveButton("YES", new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String value = et.getText().toString();
                        dbHelperUser.cashupdate(value, id);






                        dialog.dismiss();
                    }
                });

                ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });


                ad.show();
            }

        });



    }
}
