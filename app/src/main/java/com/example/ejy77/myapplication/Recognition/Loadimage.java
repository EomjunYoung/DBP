package com.example.ejy77.myapplication.Recognition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ejy77.myapplication.R;

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
                   Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri);
                   // 혹은 bmp = Images.Media.getBitmap(getContentResolver(), url);

                   iv1.setImageBitmap(bmp);
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


}
