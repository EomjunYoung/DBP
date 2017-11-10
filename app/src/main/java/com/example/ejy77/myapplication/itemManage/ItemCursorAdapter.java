package com.example.ejy77.myapplication.itemManage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejy77.myapplication.DB.DBHelper;
import com.example.ejy77.myapplication.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by ejy77 on 2017-11-06.
 */

public class ItemCursorAdapter extends CursorAdapter
{

    private LayoutInflater cursorInflater;

    public ItemCursorAdapter(Context context, Cursor c, int flag) {

        super(context, c, 0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
            // ListView에 표시될 View를 리턴
    {


        return LayoutInflater.from(context).inflate(R.layout.shopitem, parent, false);
}

    @Override
    public void bindView(View view, final Context context, final Cursor cursor)
            // //View의 각 위젯들의 속성을 지정하는것이 bindView
    {


        TextView tvname = (TextView)view.findViewById(R.id.itemname);
        TextView tvnation = (TextView)view.findViewById(R.id.itemnation);
        TextView tvprice = (TextView)view.findViewById(R.id.itemprice);
        TextView tvnumber = (TextView)view.findViewById(R.id.itemnumber);
        Button btnpurchase = (Button)view.findViewById(R.id.itempurchase);
        ImageView ivimage = (ImageView)view.findViewById(R.id.itemimage);


        int count = cursor.getCount();
        cursor.moveToFirst();
        Log.d("eom", "1");

        for(int i=0; i<count-1; i++) {

            cursor.moveToNext();
            Log.d("eom", "2");


            String name = cursor.getString(cursor.getColumnIndexOrThrow("ItemName"));
            String nation = cursor.getString(cursor.getColumnIndexOrThrow("ItemNation"));
            String price = cursor.getString(cursor.getColumnIndexOrThrow("ItemPrice"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("ItemNumber"));
           // byte[] bytes = cursor.getBlob(cursor.getColumnIndex("ItemPicture"));
            //Bitmap bitmap = bytetobitmap(bytes);


            Log.d("eom", "3");

            tvname.setText(name);
            tvnation.setText(nation);
            tvprice.setText(price);
            tvnumber.setText(number);
            Log.d("eom", "4");

            btnpurchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SQLiteDatabase db, db2;
                    DBHelper dbHelperItem = new DBHelper(context, "itemdb.db", null, 2);
                    DBHelper dbHelperUser = new DBHelper(context, "logindb.db", null, 2);

                    db = dbHelperItem.getWritableDatabase();
                    db2 = dbHelperUser.getWritableDatabase();

                    String sql = "select ItemPrice from SKshops";
                    String sql2 = "select money from UsersInfor";

                    Cursor cursor1 = db2.rawQuery(sql2, null);
                    Cursor cursor2 = db.rawQuery(sql, null);

                    int UserMoney = Integer.parseInt(cursor1.getString(0));
                    int ItemPrice = Integer.parseInt(cursor2.getString(0));

                    int total = UserMoney - ItemPrice;










                }
            });

            //  ivimage.setImageBitmap(bitmap);



        }

    }

    /*
        private String id;
        private String name;
        private String nation;
        private String price;
        private String number;
        private byte[] image;
*/

    public Bitmap bytetobitmap(byte[] bytes)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }
}
