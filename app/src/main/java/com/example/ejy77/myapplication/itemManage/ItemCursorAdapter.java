package com.example.ejy77.myapplication.itemManage;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejy77.myapplication.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by ejy77 on 2017-11-06.
 */

public class ItemCursorAdapter extends CursorAdapter
{

    private LayoutInflater cursorInflater;

    public ItemCursorAdapter(Context context, Cursor c, int flag) {

        super(context, c, flag);
        cursorInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
            // ListView에 표시될 View를 리턴
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v =layoutInflater.inflate(R.layout.shopitem, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
            // //View의 각 위젯들의 속성을 지정하는것이 bindView
    {


        TextView tvname = (TextView)view.findViewById(R.id.itemname);
        TextView tvnation = (TextView)view.findViewById(R.id.itemnation);
        TextView tvprice = (TextView)view.findViewById(R.id.itemprice);
        TextView tvnumber = (TextView)view.findViewById(R.id.itemnumber);
        ImageView ivimage = (ImageView)view.findViewById(R.id.itemimage);


        int count = cursor.getCount();

        for(int i=0; i<count; i++) {

            cursor.moveToNext();
            String name = cursor.getString(cursor.getColumnIndex("ItemName"));
            String nation = cursor.getString(cursor.getColumnIndex("ItemNation"));
            String price = cursor.getString(cursor.getColumnIndex("ItemPrice"));
            String number = cursor.getString(cursor.getColumnIndex("ItemNumber"));
            byte[] bytes = cursor.getBlob(cursor.getColumnIndex("ItemPicture"));
            Bitmap bitmap = bytetobitmap(bytes);


            tvname.setText(name);
            tvnation.setText(nation);
            tvprice.setText(price);
            tvnumber.setText(number);
            ivimage.setImageBitmap(bitmap);

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
