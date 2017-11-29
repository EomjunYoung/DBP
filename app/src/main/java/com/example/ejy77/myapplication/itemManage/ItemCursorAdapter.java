package com.example.ejy77.myapplication.itemManage;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.Toast;

import com.example.ejy77.myapplication.DB.DBHelper;
import com.example.ejy77.myapplication.R;

import static android.R.attr.bitmap;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by ejy77 on 2017-11-06.
 */

public class ItemCursorAdapter extends CursorAdapter
{

    private LayoutInflater cursorInflater;
    private final int REQUEST_WIDTH = 512;
    private final int REQUEST_HEIGHT = 512;
    String id;

    public ItemCursorAdapter(Context context, Cursor c, int flag) {

        super(context, c, flag);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
            // ListView에 표시될 View를 리턴
    {


        return LayoutInflater.from(context).inflate(R.layout.shopitem, parent, false);
}
/*
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        id = intent.getStringExtra("login");
        return startId;
    }*/

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

        Log.d("eom", "1");

        Log.d("eom", "2");

            String name = cursor.getString(cursor.getColumnIndexOrThrow("ItemName"));
            String nation = cursor.getString(cursor.getColumnIndexOrThrow("ItemNation"));
            String price = cursor.getString(cursor.getColumnIndexOrThrow("ItemPrice"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("ItemNumber"));
            byte[] bytes = cursor.getBlob(cursor.getColumnIndex("ItemPicture"));
           // Bitmap bitmap = bytetobitmap(bytes);
            //Bitmap bitmap2 = resizeBitmap(bitmap);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 16;
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);

            Log.d("eom", "3");

            tvname.setText(name);
            tvnation.setText(nation);
            tvprice.setText(price);
            tvnumber.setText(number);
            ivimage.setImageBitmap(bitmap);
            Log.d("eom", "4");

            btnpurchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SQLiteDatabase db, db2;

                    DBHelper dbHelperItem = new DBHelper(context, "itemdb3.db", null, 1);
                    DBHelper dbHelperUser = new DBHelper(context, "logindb3.db", null, 1);

                    db = dbHelperItem.getReadableDatabase();
                    db2 = dbHelperUser.getReadableDatabase();

                    String sql = "select * from SKshops2 where _id='Item_id3'";
                    String sql2 = "select * from UsersInfor2 where id='test2'";


                    Cursor cursor2 = db.rawQuery(sql, null);
                    Cursor cursor1 = db2.rawQuery(sql2, null);
                    cursor2.moveToFirst();
                    cursor1.moveToFirst();

                    int UserMoney = Integer.parseInt(cursor1.getString(5));
                    int ItemPrice = Integer.parseInt(cursor2.getString(3));

                    int total = UserMoney - ItemPrice;

                    Toast.makeText(context, total+"", Toast.LENGTH_SHORT).show();


                }
            });

            //  ivimage.setImageBitmap(bitmap);





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

    private int setSimpleSize(BitmapFactory.Options options, int requestWidth, int requestHeight){
        // 이미지 사이즈를 체크할 원본 이미지 가로/세로 사이즈를 임시 변수에 대입.
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;

        // 원본 이미지 비율인 1로 초기화
        int size = 1;

        // 해상도가 깨지지 않을만한 요구되는 사이즈까지 2의 배수의 값으로 원본 이미지를 나눈다.
        while(requestWidth < originalWidth || requestHeight < originalHeight){
            originalWidth = originalWidth / 2;
            originalHeight = originalHeight / 2;

            size = size * 2;
        }
        return size;
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



}
