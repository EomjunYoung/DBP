package com.example.ejy77.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by ejy77 on 2017-11-21.
 */

public class MypageChanger extends AppCompatActivity

{

    ImageView ivtest;
    Button btncustomer, btnmanager;
    private final int REQUEST_WIDTH = 512;
    private final int REQUEST_HEIGHT = 512;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);


        btncustomer = (Button)findViewById(R.id.btncustomer);
        btnmanager = (Button)findViewById(R.id.btnmanager);
        id = getIntent().getStringExtra("login");
        ivtest = (ImageView)findViewById(R.id.ivtest);

        btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(getApplicationContext(), MypageCustomer.class).putExtra("login", id));


             /*   BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.eom);
                Bitmap bitmap = drawable.getBitmap();
                Bitmap bitmap2 = resizeBitmap(bitmap, REQUEST_WIDTH, REQUEST_HEIGHT);
                ivtest.setImageBitmap(bitmap2);*/

                BitmapFactory.Options options = new BitmapFactory.Options();
                // inJustDecodeBounds = true일때 BitmapFactory.decodeResource는 리턴하지 않는다.
                // 즉 bitmap은 반환하지않고, options 변수에만 값이 대입된다.
                //options.inJustDecodeBounds = true;
               // BitmapFactory.decodeResource(getResources(), R.drawable.eom, options);

                // 이미지 사이즈를 필요한 사이즈로 적당히 줄이기위해 계산한 값을
                // options.inSampleSize 에 2의 배수의 값으로 넣어준다.
                options.inSampleSize = 8;

                // options.inJustDecodeBounds 에 false 로 다시 설정해서 BitmapFactory.decodeResource의 Bitmap을 리턴받을 수 있게한다.
               // options.inJustDecodeBounds = false;
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.eom, options);

                // 이미지 size가 재설정된 이미지를 출력한다.
                ivtest.setImageBitmap(bitmap);





            }
        });

        btnmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MypageManager.class);
                startActivity(intent);
            }
        });


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

}
