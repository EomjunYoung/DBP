package com.example.ejy77.myapplication.itemManage;



import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ejy77.myapplication.DB.DBHelper;
import com.example.ejy77.myapplication.R;

/**
 * Created by ejy77 on 2017-10-15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ejy77.myapplication.R;
import com.example.ejy77.myapplication.Recognition.Loadimage;

public class Solditem extends AppCompatActivity
{

    Button sellbtn;
    DBHelper dbHelperItem;
    SQLiteDatabase db;
    ListView listview;
    Cursor cursor;



    //final String querySelect = String.format( "SELECT FROM * SKshops");




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itembar);

        String sql = "select * from SKshops2";

       listview = (ListView)findViewById(R.id.lv1);
       dbHelperItem = new DBHelper(this, "itemdb4.db", null, 1);
       db = dbHelperItem.getWritableDatabase();
       Cursor cursor = db.rawQuery(sql, null);
       sellbtn = (Button)findViewById(R.id.sellbtn);




        ItemCursorAdapter itemCursorAdapter = new ItemCursorAdapter(this, cursor, 0);
        listview.setAdapter(itemCursorAdapter);


        sellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(Solditem.this);
                ad.setTitle("물품에 등록될 사진이 있는지를 묻는 알림창");
                ad.setMessage("등록할 사진이 없으면 YES를 없으시면 NO를");
                ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        Intent intent = new Intent(getApplicationContext(), Loadimage.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        Intent intent = new Intent(getApplicationContext(), ItemSell.class);
                        startActivity(intent);
                        dialog.dismiss();

                    }
                });
                ad.create();
                ad.show();
            }
        });



        // selectDB();



        /*new Handler().post(new Runnable() {
            @Override
            public void run() {

                itemCursorAdapter = new ItemCursorAdapter(Solditem.this, cursor, 0);
                listview.setAdapter(itemCursorAdapter);


            }
        });*/





        /*



        ListView listView;
        itembarAdapter itembarAdapter;

        itembarAdapter = new itembarAdapter();
        listView = (ListView)findViewById(R.id.lv1);
        listView.setAdapter(itembarAdapter);


        itembarAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cap1), "SK와이번스 테스트캡 1", "15000", "10", "착용감이 좋은 모자1");
        itembarAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cap2), "SK와이번스 테스트캡 1", "15000", "10", "착용감이 좋은 모자1");
        itembarAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cap3), "SK와이번스 테스트캡 1", "15000", "10", "착용감이 좋은 모자1");
        itembarAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cap4), "SK와이번스 테스트캡 1", "15000", "10", "착용감이 좋은 모자1");
        itembarAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cap5), "SK와이번스 테스트캡 1", "15000", "10", "착용감이 좋은 모자1");
        itembarAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cap6), "SK와이번스 테스트캡 1", "15000", "10", "착용감이 좋은 모자1");
        itembarAdapter.addItem(ContextCompat.getDrawable(this, R.drawable.cap7), "SK와이번스 테스트캡 1", "15000", "10", "착용감이 좋은 모자1");

    */

    }

    /*private void selectDB()
    {
        dbHelperItem = new DBHelper(Solditem.this, "itemdb2.db", null, 1);
        db = dbHelperItem.getWritableDatabase();
        String sql = "select * from SKshops2";
        cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0)
        {
            startManagingCursor(cursor);
            ItemCursorAdapter itemCursorAdapter = new ItemCursorAdapter(Solditem.this, cursor, 0);
            listview.setAdapter(itemCursorAdapter);
        }
    }
*/



}
