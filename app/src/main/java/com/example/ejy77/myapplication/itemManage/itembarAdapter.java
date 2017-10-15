package com.example.ejy77.myapplication.itemManage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejy77.myapplication.R;

import java.util.ArrayList;

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


        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.iv1);
        TextView nameTextView = (TextView)convertView.findViewById(R.id.tv1);
        TextView priceTextView = (TextView)convertView.findViewById(R.id.tv2);
        TextView pointTextView = (TextView)convertView.findViewById(R.id.tv3);
        TextView explainTextView = (TextView)convertView.findViewById(R.id.tv4);

        Mainitembar mainitembar = Mainitembarlist.get(position);

        iconImageView.setImageDrawable(mainitembar.getItemimage());
        nameTextView.setText(mainitembar.getItemname());
        priceTextView.setText(mainitembar.getItemprice());
        pointTextView.setText(mainitembar.getItempoint());
        explainTextView.setText(mainitembar.getItemexplain());

        return convertView;


    }

    public void addItem(Drawable image, String name, String price, String point, String explain )
    {
        Mainitembar item = new Mainitembar();
        item.setItemimage(image);
        item.setItemname(name);
        item.setItemprice(price);
        item.setItempoint(point);
        item.setItemexplain(explain);

        Mainitembarlist.add(item);
    }


}
