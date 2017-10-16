package com.example.ejy77.myapplication.itemManage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ejy77.myapplication.R;

/**
 * Created by ejy77 on 2017-10-16.
 */

public class ItemManager extends AppCompatActivity

{

    TextView itemId;
    TextView itemName;
    TextView itemNation;
    TextView itemPrice;
    TextView itemNumber;
    private byte[] itemPicture;
    //drawable에 있는 이미지를 byte[]형으로 변경한 이후에 저장하기위해

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_manageitem);








    }
}
