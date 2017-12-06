package com.example.ejy77.myapplication.itemManage;



import android.content.Context;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import android.widget.Toast;

import com.example.ejy77.myapplication.R;
import com.example.ejy77.myapplication.Recognition.Loadimage;

import static com.example.ejy77.myapplication.R.id.buttonCancel;
import static com.example.ejy77.myapplication.R.id.buttonOK;
import static com.example.ejy77.myapplication.R.id.editnumber;
import static com.example.ejy77.myapplication.R.id.editnumber2;

public class ManageItem extends AppCompatActivity
{

    Button sellbtn, deleteItem;
    DBHelper dbHelperItem, dbHelperLogin;
    SQLiteDatabase dbItem, dbLogin;
    ListView listview;
    String id;
    Cursor cursor0;



    //final String querySelect = String.format( "SELECT FROM * SKshops");




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itembar2);


        id = getIntent().getStringExtra("login");

        listview = (ListView)findViewById(R.id.lv1);
        dbHelperItem = new DBHelper(this, "itemdb5.db", null, 1);
        dbHelperLogin = new DBHelper(this, "logindb5.db", null, 1);
        dbItem = dbHelperItem.getWritableDatabase();
        dbLogin = dbHelperLogin.getWritableDatabase();
        sellbtn = (Button)findViewById(R.id.sellbtn);
        deleteItem = (Button)findViewById(R.id.deleteItem);
        String sql0 = "Select * from Eoms";
        cursor0 = dbItem.rawQuery(sql0, null);



        ManageCursorAdapter manageCursorAdapter = new ManageCursorAdapter(this, cursor0, 0);
        listview.setAdapter(manageCursorAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                AlertDialog.Builder ad = new AlertDialog.Builder(ManageItem.this);
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view2 = (LinearLayout) inflater.inflate(R.layout.dialog_deletecheck, null);
                ad.setView(view2);
                final Button btnOK = (Button)view2.findViewById(R.id.btnOK);
                final AlertDialog dialog = ad.create();

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


        sellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(ManageItem.this);
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view2 = (LinearLayout) inflater.inflate(R.layout.dialog_delete, null);
                ad.setView(view2);
                final Button btnCancel = (Button)view2.findViewById(R.id.btnCancel);
                final Button btnOK = (Button)view2.findViewById(R.id.btnOK);
                final EditText editselect = (EditText) view2.findViewById(R.id.editselect);
                final AlertDialog dialog = ad.create();

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Itemid = editselect.getText().toString();
                        /*String sql = "select * from Eoms where _id='"+Itemid+"'";
                        Log.d("eom", sql);
                        String sql2 = "select * from UsersInfor2 where id='"+id+"'";
                        Log.d("eom", sql2);
                        Cursor cursor = dbItem.rawQuery(sql, null);
                        cursor.moveToFirst();
                        Cursor cursor2 = dbLogin.rawQuery(sql2, null);
                        cursor2.moveToFirst();
                        String selectedItemId = cursor.getString(cursor.getColumnIndexOrThrow("ItemPrice"));
                        String selectedId = cursor2.getString(5);*/


                        dbHelperItem.itemdelete(Itemid);
                        Log.d("eom", "삭제된 id"+Itemid);
                        String sqll = "select * from Eoms";
                        Cursor cursor00 = dbItem.rawQuery(sqll, null);
                        cursor00.moveToFirst();
                        ItemCursorAdapter itemCursorAdapter = new ItemCursorAdapter(getApplicationContext(), cursor00, 0);
                        itemCursorAdapter.notifyDataSetChanged();
                        listview.setAdapter(itemCursorAdapter);

                        dialog.dismiss();


                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });




                dialog.show();
            }
        });

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

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
