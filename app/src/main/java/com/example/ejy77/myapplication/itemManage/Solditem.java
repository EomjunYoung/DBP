package com.example.ejy77.myapplication.itemManage;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

public class Solditem extends AppCompatActivity
{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itembar);


        DBHelper dbHelperItem;
        SQLiteDatabase db;
        Cursor cursor;
        ItemCursorAdapter itemCursorAdapter;

        final  String querySelect = String.format( "SELECT FROM * SKshops");



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




}
