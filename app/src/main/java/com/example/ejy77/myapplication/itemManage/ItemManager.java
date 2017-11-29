package com.example.ejy77.myapplication.itemManage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejy77.myapplication.DB.DBHelper;
import com.example.ejy77.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    TextView Username, Userid, Userpwd, Useremail, Usersex, Usermoney;
    EditText Item_id;
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

        dbHelperItem = new DBHelper(getApplicationContext(), "itemdb3.db", null, 1);
        dbHelperUser = new DBHelper(getApplicationContext(), "logindb3.db", null, 1);



        select = (TextView)findViewById(R.id.select);
        Itemid = (TextView)findViewById(R.id.itemid);
        Itemname = (TextView)findViewById(R.id.itemname);
        Itemnation = (TextView)findViewById(R.id.itemnation);
        Itemprice = (TextView)findViewById(R.id.itemprice);
        Itemnumber = (TextView)findViewById(R.id.itemnumber);
        Item_id = (EditText)findViewById(R.id._id);

        Username = (TextView)findViewById(R.id.username);
        Userid = (TextView)findViewById(R.id.userid);
        Userpwd = (TextView)findViewById(R.id.userpwd);
        Useremail = (TextView)findViewById(R.id.useremail);
        Usersex = (TextView)findViewById(R.id.usersex);
        Usermoney = (TextView)findViewById(R.id.usermoney);

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
                String _id = Item_id.getText().toString();
                Log.d("eom", "id대입완료");

                //bytes = getIntent().getByteArrayExtra("image");
                //bytes = getByteArrayFromDrawable(getResources().getDrawable(R.drawable.cap4));
                String imageuri = getIntent().getStringExtra("imageuri");

                if(imageuri != null) {
                    Uri muri = Uri.parse(imageuri);

                Bitmap bmp = null;


                    try {
                        bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), muri);
                        bytes = getByteArrayFromDrawable2(bmp);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 16;
                        Bitmap bitmap3 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
                        bytes = getByteArrayFromDrawable2(bitmap3);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    testiv.setImageBitmap(bmp);
                    Log.d("eom", "사진대입완료1");
                    //bytes = getByteArrayFromDrawable2(bitmap3);
                    Log.d("eom", "사진대입완료2");
                    dbHelperItem.SKshopImageInsert(id, name, nation, price, number, bytes, _id);
                    Log.d("eom", "insert 완료");
                }
                else
                {
                    dbHelperItem.SKshopImageInsert(id, name, nation, price, number, null, _id);
                    Log.d("eom", "insert 완료");
                }





               // dbHelperItem.SKshopInsert(id, name, nation, price, number, null);


            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SQLiteDatabase db = dbHelperUser.getReadableDatabase();
                String sql = "select * from UsersInfor2";
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
                    String money = cursor.getString(5);


                    str+= "이름 :" + name + "id :" + id + "pwd :" + pwd + "email :"+ email + "sex :" + sex + "money :" + money + "\n";

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
                String money = Usermoney.getText().toString();

                dbHelperUser.UsersInfoInsert(name, id, pwd, email, sex, money);




            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SQLiteDatabase db = dbHelperItem.getReadableDatabase();
                String sql = "select * from SKshops2";
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
                    String _id = cursor.getString(6);

                   str += id + name + nation + price + number +_id;

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 16;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

                    select.setText(str);
                    testiv.setImageBitmap(bitmap);



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

    public byte[] getByteArrayFromDrawable2(Bitmap b) {
        Bitmap bitmap = b;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();

        return data;
    }

    static public Bitmap resizeBitmap(Bitmap original) {

        int resizeWidth = 200;

        double aspectRatio = (double) original.getHeight() / (double) original.getWidth();
        int targetHeight = (int) (resizeWidth * aspectRatio);
        Bitmap result = Bitmap.createScaledBitmap(original, resizeWidth, targetHeight, false);
        if (result != original) {
            original.recycle();
        }
        return result;
    }




    public Bitmap bytetobitmap(byte[] bytes)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }
}
