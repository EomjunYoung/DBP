package com.example.ejy77.myapplication.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.attr.track;

/**
 * Created by ejy77 on 2017-10-04.
 */

    public class DBHelper extends SQLiteOpenHelper


    {

    private Context context;
    private StringBuilder sb = new StringBuilder();


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void initStringBuilder(){
        sb.delete(0,sb.length());
        sb.trimToSize();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        initStringBuilder();

        try {

            sb.append("CREATE TABLE IF NOT EXISTS UsersInfor( ");
            sb.append("name VARCHAR(20), ");
            sb.append("id VARCHAR(20) PRIMARY KEY NOT NULL, ");
            sb.append("pwd VARCHAR(20) NOT NULL, ");
            sb.append("email VARCHAR(30), ");
            sb.append("sex VARCHAR(10)) ");

            db.execSQL(sb.toString());

            initStringBuilder();

            sb.append("CREATE TABLE IF NOT EXISTS SKshops(");
            sb.append("ItemId VARCHAR(20) PRIMARY KEY, ");
            sb.append("ItemName VARCHAR(30), ");
            sb.append("ItemNation VARCHAR(20), ");
            sb.append("ItemPrice VARCHAR(20), ");
            sb.append("ItemNumber VARCHAR(20),");
            sb.append("ItemPicture BLOB)");

            db.execSQL(sb.toString());
            Log.d("able", "TABLE 생성완료!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UsersInfoInsert(String name, String id, String pwd, String email, String sex)
        {
            initStringBuilder();
            try {
                SQLiteDatabase db = getWritableDatabase();
                sb.append("INSERT INTO UsersInfor VALUES(");
                sb.append("'" + name + "', '" + id + "', '" + pwd + "', '" + email + "', '" + sex + "'");
                sb.append(")");
                db.execSQL(sb.toString());
                db.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
}


    public void SKshopInsert(String id, String name, String nation, String price, String number, byte[] image)
    {
        initStringBuilder();
        try {

            SQLiteDatabase db = getWritableDatabase();
            sb.append("INSERT INTO SKshops VALUES( ");
            sb.append("'" + id + "', '" + name + "', '" + nation + "', '" + price + "','" + number + "' ,'" + image + "'");
            sb.append(")");
            db.execSQL(sb.toString());
            db.close();
            Log.d("Insert", "삽입완료!!");
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // 이미지를 byte형식으로 저장할땐 compileStatement를 통해 해야 byte가 정상적으로 잘 저장됨.
    public void SKshopImageInsert(String id, String name, String nation, String price, String number, byte[] image)
    {
        initStringBuilder();
        try{
            SQLiteDatabase db = getWritableDatabase();
            SQLiteStatement p = db.compileStatement("INSERT INTO SKshops(ItemId, ItemName, ItemNation, ItemPrice, ItemNumber,ItemPicture) Values(?,?,?,?,?,?)");
            p.bindString(1, id);
            p.bindString(2, name);
            p.bindString(3, nation);
            p.bindString(4, price);
            p.bindString(5, number);
            p.bindBlob(6, image);
            p.execute();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void shopSelect()
    {
        initStringBuilder();

        try {

            SQLiteDatabase db = getReadableDatabase();
            sb.append("select * from SKshops");
            Cursor cursor = db.rawQuery(sb.toString(), null);
            while(cursor.moveToNext())
            {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String nation = cursor.getString(2);
                String price = cursor.getString(3);
                String number = cursor.getString(4);
                byte[] bytes = cursor.getBlob(5);

            }

            db.close();

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void UserSelect()
    {
        initStringBuilder();

        try {
            SQLiteDatabase db = getReadableDatabase();
            sb.append("select * from UsersInfo ");
            Cursor cursor = db.rawQuery(sb.toString(), null);
            while(cursor.moveToNext())
            {
                String name = cursor.getString(0);
                String id = cursor.getString(1);
                String pwd = cursor.getString(2);
                String email = cursor.getString(3);
                String sex = cursor.getString(4);

            }
            db.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
