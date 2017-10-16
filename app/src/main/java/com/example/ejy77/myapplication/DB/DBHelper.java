package com.example.ejy77.myapplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;

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
        sb.append("CREATE TABLE IF NOT EXISTS UsersInfor( ");
        sb.append("name VARCHAR(20), ");
        sb.append("id VARCHAR(20) PRIMARY KEY NOT NULL. ");
        sb.append("pwd VARCHAR(20) NOT NULL, ");
        sb.append("email VARCHAR(30), ");
        sb.append("sex VARCHAR(10), ");

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


    }

    public void UsersInfoInsert(String name, String id, String pwd, String email, String sex)
        {
            initStringBuilder();
            SQLiteDatabase db = getWritableDatabase();
            sb.append("INSERT INTO UsersInfo VALUES(");
            sb.append("'" + name + "', '" + id + "', '" + pwd + "', '" + email + "', '" + sex + "'");
            sb.append(")");
            db.execSQL(sb.toString());
            db.close();
}


    public void SKshopInsert(String id, String name, String nation, String price, String number, byte[] image)
    {
        initStringBuilder();
        SQLiteDatabase db = getWritableDatabase();
        sb.append("INSERT INTO SKshop VALUES( ");
        sb.append("'"+ id +"', '"+ name +"', '"+ nation +"', '"+ price +"','"  + number + "' ,'"+ image +"'");
        sb.append(")");
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
