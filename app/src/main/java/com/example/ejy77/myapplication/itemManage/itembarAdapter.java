package com.example.ejy77.myapplication.itemManage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejy77.myapplication.DB.DBHelper;
import com.example.ejy77.myapplication.R;

import java.util.ArrayList;

import static com.example.ejy77.myapplication.R.id.btn1;

/**
 * Created by ejy77 on 2017-10-15.
 */

public class itembarAdapter extends BaseAdapter
{




    private ArrayList<Mainitembar> Mainitembarlist = new ArrayList<Mainitembar>();

    public itembarAdapter()
    {

    }

    //Adapter에 사용되는 데이터갯수 리턴
    public int getCount() {
        return Mainitembarlist.size();
    }

    //해당 i위치 데이터 리턴
    @Override
    public Object getItem(int i) {
        return Mainitembarlist.get(i);
    }

    //해당 i위치 데이터 id 리턴
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final int pos = position;
        final Context context = parent.getContext();

        if ( convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.shopitem, parent, false);
        }


        TextView id = (TextView)convertView.findViewById(R.id.itemid);
        TextView name = (TextView)convertView.findViewById(R.id.itemname);
        TextView nation = (TextView)convertView.findViewById(R.id.itemnation);
        TextView price = (TextView)convertView.findViewById(R.id.itemprice);
        TextView number = (TextView)convertView.findViewById(R.id.itemnumber);
        ImageView image = (ImageView)convertView.findViewById(R.id.itemimage);

        Mainitembar mainitembar = Mainitembarlist.get(position);

        name.setText(mainitembar.getName());
        nation.setText(mainitembar.getNation());
        price.setText(mainitembar.getPrice());
        number.setText(mainitembar.getNumber());
        //image.setImageDrawable(mainitembar.getImage()); byte로 되어있으므로 넣을때 drawable 반환해서 넣기

        Button btn1 = (Button)convertView.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBHelper dbHelperItem = new DBHelper(context.getApplicationContext(), "itemdb.db", null, 1);
                SQLiteDatabase writeDB = dbHelperItem.getWritableDatabase();
                SQLiteDatabase readDB = dbHelperItem.getReadableDatabase();

                StringBuilder sb = new StringBuilder();


            }
        });

        return convertView;


    }

    public void addItem(String id, String name, String nation, String price, String number, byte[] bytes)
    {
        Mainitembar item = new Mainitembar();

        item.setId(id);
        item.setName(name);
        item.setNation(nation);
        item.setPrice(price);
        item.setNumber(number);
        item.setImage(bytes);

        Mainitembarlist.add(item);
    }


}
