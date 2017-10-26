package com.example.ejy77.myapplication.itemManage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejy77.myapplication.DB.DBHelper;
import com.example.ejy77.myapplication.R;

import java.io.ByteArrayOutputStream;


/**
 * Created by ejy77 on 2017-10-16.
 */

public class ItemManager extends AppCompatActivity

{

    Button btn1;
    Button btn2;
    Button btn3;
    TextView itemId;
    TextView itemName;
    TextView itemNation;
    TextView itemPrice;
    TextView itemNumber;
    ImageView itemPicture;
    TextView select;
    //private byte[] itemPicture;
    //drawable에 있는 이미지를 byte[]형으로 변경한 이후에 저장하기위해
    DBHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_manageitem);

        dbHelper = new DBHelper(getApplicationContext(), "itemdb.db", null, 1);



        select = (TextView)findViewById(R.id.select);
        itemId = (TextView)findViewById(R.id.itemId);
        itemName = (TextView)findViewById(R.id.itemName);
        itemNation = (TextView)findViewById(R.id.itemNation);
        itemPrice = (TextView)findViewById(R.id.itemPrice);
        itemNumber = (TextView)findViewById(R.id.itemNumber);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String id = itemId.getText().toString();
                String name = itemName.getText().toString();
                String nation = itemNation.getText().toString();
                String price = itemPrice.getText().toString();
                String number = itemNumber.getText().toString();
                byte[] bytes = drawabletobyte(getResources().getDrawable(R.drawable.cap1));

                dbHelper.SKshopInsert(id, name, nation, price, number, null);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String sql = "select * from UsersInfor";
                Cursor cursor = db.rawQuery(sql, null);
                while(cursor.moveToNext())
                {

                    String name = cursor.getString(0);
                    String id = cursor.getString(1);
                    String pwd = cursor.getString(2);
                    String email = cursor.getString(3);
                    String sex = cursor.getString(4);

                    String moon = name + id + pwd + email + sex;

                    select.setText(moon);

                }

                db.close();


            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = itemId.getText().toString();
                String id = itemName.getText().toString();
                String pwd = itemNation.getText().toString();
                String email = itemPrice.getText().toString();
                String sex = itemNumber.getText().toString();

                dbHelper.UsersInfoInsert(name, id, pwd, email, sex);

            }
        });

    }

    public byte[] drawabletobyte(Drawable drawable)
    {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bytes;
        Bitmap bitmap1, bitmap2;

        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap1 = ((BitmapDrawable)drawable).getBitmap();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,70,byteArrayOutputStream);
        bytes = byteArrayOutputStream.toByteArray();
        //bitmap2 = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        return bytes;
    }
}
