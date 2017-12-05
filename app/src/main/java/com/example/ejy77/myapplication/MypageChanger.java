package com.example.ejy77.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejy77.myapplication.DB.DBHelper;
import com.example.ejy77.myapplication.itemManage.ManageItem;
import com.example.ejy77.myapplication.itemManage.Solditem;

import org.w3c.dom.Text;

/**
 * Created by ejy77 on 2017-11-21.
 */

public class MypageChanger extends AppCompatActivity

{


    DBHelper dbHelperUser;
    ImageView ivtest;
    Button manageInfor, manageItem, btnCheck, btnCancel;
    private final int REQUEST_WIDTH = 512;
    private final int REQUEST_HEIGHT = 512;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        manageInfor = (Button)findViewById(R.id.manageInfor);
        manageItem = (Button)findViewById(R.id.manageItem);
        id = getIntent().getStringExtra("login");
        dbHelperUser = new DBHelper(getApplicationContext(), "logindb5.db", null, 1);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCheck = (Button)findViewById(R.id.btnCheck);



        manageInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = dbHelperUser.getReadableDatabase();
                String sql = "Select * from UsersInfor2 where id='"+id+"'";
                final Cursor cursor = db.rawQuery(sql, null);
                cursor.moveToFirst();
                final String money1 = cursor.getString(cursor.getColumnIndexOrThrow("money"));


                AlertDialog.Builder ad = new AlertDialog.Builder(MypageChanger.this);
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view2 = (LinearLayout) inflater.inflate(R.layout.dialog_cash, null);
                ad.setView(view2);
                final Button btnCancel = (Button)view2.findViewById(R.id.btnCancel);
                final Button btnOK = (Button)view2.findViewById(R.id.btnOK);
                final TextView nowcash = (TextView)view2.findViewById(R.id.nowcash);
                final EditText editCash = (EditText) view2.findViewById(R.id.editCash);
                final AlertDialog dialog = ad.create();
                nowcash.setText("현재 회원님이 보유하신 금액 : " + money1);
                Log.d("eom", "현재 보유한 돈 :" + money1);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        String cash = editCash.getText().toString();
                        dbHelperUser.cashupdate(cash, id);
                       // SQLiteDatabase db = dbHelperUser.getReadableDatabase();
                        //String sql = "Select * from UsersInfor2 where id='"+id+"'";
                        //Cursor cursor1 = db.rawQuery(sql, null);
                       // String money2 = cursor1.getString(cursor.getColumnIndexOrThrow("money"));
                        Toast.makeText(getApplicationContext(), cash+"원이 충전되었습니다.",Toast.LENGTH_SHORT).show();



                        dialog.dismiss();


                    }
                });

               dialog.show();


            }
        });

        manageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManageItem.class);
                startActivity(intent);
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


}
