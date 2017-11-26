package com.example.ejy77.myapplication.Recognition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ejy77.myapplication.R;
import com.example.ejy77.myapplication.itemManage.ItemManager;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
     * Created by ejy77 on 2017-11-13.
     */

    public class Loadimage extends AppCompatActivity
    {


        Button btncapture;
        ImageView iv1;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btncapture = (Button)findViewById(R.id.btncapture);
        iv1 = (ImageView)findViewById(R.id.iv1);



                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);


    }



        protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {


            if(resultCode == RESULT_CANCELED)
            {

            }

            else
            {

                Uri mUri = data.getData();

               try
               {
                   Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                   Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri);
                   // 혹은 bmp = Images.Media.getBitmap(getContentResolver(), url);
                   byte[] bytes = getByteArrayFromDrawable(bmp);
                   Intent intent = new Intent(getApplicationContext(), ItemManager.class).putExtra("imageuri", mUri.toString());
                   startActivity(intent);

               }
               catch(FileNotFoundException e)
               {
                   e.printStackTrace();
               }

               catch(IOException e)
               {
                   e.printStackTrace();
               }

            }


        }

//iv1.setImageURI(data.getData());



        public byte[] getByteArrayFromDrawable(Bitmap b) {
            Bitmap bitmap = b;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] data = stream.toByteArray();

            return data;
        }

}
