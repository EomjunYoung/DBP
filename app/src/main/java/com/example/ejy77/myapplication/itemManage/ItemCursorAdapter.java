package com.example.ejy77.myapplication.itemManage;

import android.content.Context;
import android.database.Cursor;
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


    public ItemCursorAdapter(Context context, Cursor c) {
        super(context, c);
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

        String name = cursor.getString(cursor.getColumnIndex("ItemName"));
        String nation = cursor.getString(cursor.getColumnIndex("ItemNation"));
        String price = cursor.getString(cursor.getColumnIndex("ItemPrice"));
        String number = cursor.getString(cursor.getColumnIndex("ItemNumber"));
        byte[] bytes = cursor.getBlob(cursor.getColumnIndex("ItemPicture"));

    }

    /*
        private String id;
        private String name;
        private String nation;
        private String price;
        private String number;
        private byte[] image;
*/
}
