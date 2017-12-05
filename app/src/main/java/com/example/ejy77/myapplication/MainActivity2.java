package com.example.ejy77.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejy77.myapplication.Recognition.Loadimage;
import com.example.ejy77.myapplication.Recognition.search;
import com.example.ejy77.myapplication.itemManage.ItemCursorAdapter;
import com.example.ejy77.myapplication.itemManage.ItemManager;
import com.example.ejy77.myapplication.itemManage.ItemSell;
import com.example.ejy77.myapplication.itemManage.Solditem;

import static android.R.attr.id;
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
        Log.d("eom", id);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity2.this);
                LayoutInflater inflater = getLayoutInflater();
                View view2 = inflater.inflate(R.layout.activity_dialog, null);
                ad.setView(view2);
                final Button buttonSell = (Button) view2.findViewById(R.id.buttonSell);
                final Button buttonPurchase = (Button) view2.findViewById(R.id.buttonPurchase);
                final Button buttonCancel = (Button) view2.findViewById(R.id.buttonCancel);
                final AlertDialog dialog = ad.create();

                buttonSell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(MainActivity2.this, ItemSell.class).putExtra("login",id));
                        dialog.dismiss();
                    }
                });

                buttonPurchase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*Intent intent = new Intent(MainActivity2.this, Solditem.class);
                        startActivity(intent);*/
                        startActivity(new Intent(MainActivity2.this, Solditem.class).putExtra("login",id));
                        dialog.dismiss();

                    }
                });

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MypageChanger.class).putExtra("login", id));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

     //           startActivity(new Intent(getApplicationContext(), ItemSell.class).putExtra("login", id));

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ItemManager.class);
                startActivity(intent);
            }
        });

    }



}
