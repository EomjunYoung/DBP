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
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.R.id.list;
import static com.example.ejy77.myapplication.R.id.itemid;
import static com.example.ejy77.myapplication.R.id.itemname;
import static com.example.ejy77.myapplication.R.id.itemnation;
import static com.example.ejy77.myapplication.R.id.itemnumber;
import static com.example.ejy77.myapplication.R.id.itemprice;


/**
 * Created by ejy77 on 2017-10-16.
 */

public class ItemManager extends AppCompatActivity

{

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    TextView Itemid, Itemname, Itemnation, Itemprice, Itemnumber;
    TextView Username, Userid, Userpwd, Useremail, Usersex;
    ImageView testiv;
    byte[] bytes;
    TextView select;
    //private byte[] itemPicture;
    //drawable에 있는 이미지를 byte[]형으로 변경한 이후에 저장하기위해
    DBHelper dbHelperItem, dbHelperUser;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_manageitem);

        dbHelperItem = new DBHelper(getApplicationContext(), "itemdb.db", null, 1);
        dbHelperUser = new DBHelper(getApplicationContext(), "logindb.db", null, 1);



        select = (TextView)findViewById(R.id.select);
        Itemid = (TextView)findViewById(R.id.itemid);
        Itemname = (TextView)findViewById(R.id.itemname);
        Itemnation = (TextView)findViewById(R.id.itemnation);
        Itemprice = (TextView)findViewById(R.id.itemprice);
        Itemnumber = (TextView)findViewById(R.id.itemnumber);

        Username = (TextView)findViewById(R.id.username);
        Userid = (TextView)findViewById(R.id.userid);
        Userpwd = (TextView)findViewById(R.id.userpwd);
        Useremail = (TextView)findViewById(R.id.useremail);
        Usersex = (TextView)findViewById(R.id.usersex);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        testiv = (ImageView)findViewById(R.id.testiv);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String id = Itemid.getText().toString();
                String name = Itemname.getText().toString();
                String nation = Itemnation.getText().toString();
                String price = Itemprice.getText().toString();
                String number = Itemnumber.getText().toString();
                byte[] bytes = getByteArrayFromDrawable(getResources().getDrawable(R.drawable.cap1));

               // dbHelperItem.SKshopInsert(id, name, nation, price, number, null);
               dbHelperItem.SKshopImageInsert(id, name, nation, price, number, bytes);


            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SQLiteDatabase db = dbHelperUser.getReadableDatabase();
                String sql = "select * from UsersInfor";
                Cursor cursor = db.rawQuery(sql, null);
                int count = cursor.getCount();
                String str = "";
                cursor.moveToFirst();



                 for(int i=0; i<count; i++) {



                    String name = cursor.getString(0);
                    String id = cursor.getString(1);
                    String pwd = cursor.getString(2);
                    String email = cursor.getString(3);
                    String sex = cursor.getString(4);


                    str+= "이름 :" + name + "id :" + id + "pwd :" + pwd + "email :"+ email + "sex :" + sex + "\n";

                    cursor.moveToNext();

                    select.setText(str);


                }



                db.close();


            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = Username.getText().toString();
                String id = Userid.getText().toString();
                String pwd = Userpwd.getText().toString();
                String email = Useremail.getText().toString();
                String sex = Usersex.getText().toString();

                dbHelperUser.UsersInfoInsert(name, id, pwd, email, sex);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SQLiteDatabase db = dbHelperItem.getReadableDatabase();
                String sql = "select * from SKshops";
                String str = "";
                Cursor cursor = db.rawQuery(sql, null);
                cursor.moveToFirst();

                while(cursor.moveToNext())
                {


                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    String nation = cursor.getString(2);
                    String price = cursor.getString(3);
                    String number = cursor.getString(4);
                    byte[] bytes = cursor.getBlob(5);

                   str += id + name + nation + price + number;
                    Bitmap bitmap = bytetobitmap(bytes);

                    select.setText(str);
                    testiv.setImageBitmap(bitmap);

                    cursor.moveToNext();

                }


                db.close();


            }
        });

    }

  /*  public byte[] drawabletobyte(Drawable drawable)
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
    }*/

    public byte[] getByteArrayFromDrawable(Drawable d) {
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();

        return data;
    }



    public Bitmap bytetobitmap(byte[] bytes)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }
}
