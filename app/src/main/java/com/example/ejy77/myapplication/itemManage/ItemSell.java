package com.example.ejy77.myapplication.itemManage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ejy77.myapplication.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static android.R.attr.bitmap;

/**
 * Created by ejy77 on 2017-12-01.
 */

public class ItemSell extends AppCompatActivity {

    private static final int PICK_FROM_CAMERA = 0;//사진촬영 후 찍힌 이미지처리
    private static final int PICK_FROM_ALBUM = 1;//앨범에서 사진고르고 이미지처리
    private static final int CROP_FROM_IMAGE = 2;//이미지를 크롭함
    private String absoultePath;
    private Uri mImageCaptureUri;


    Button btngallery;
    EditText ItemName, ItemNation, ItemPrice, ItemNumber, ItemType, ItemId;
    ImageView ItemImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        btngallery = (Button) findViewById(R.id.btngallery);
        ItemImage = (ImageView)findViewById(R.id.ItemImage);

        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //doTakeAlbumAction();
                doTakePhotoAction();

            }
        });

    }

    public void doTakeAlbumAction() // 앨범에서 이미지 가져오기

    {

        // 앨범 호출

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);

    }

    public void doTakePhotoAction() // 카메라 촬영 후 이미지 가져오기

    {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        // 임시로 사용할 파일의 경로를 생성

        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";

        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));


        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

        startActivityForResult(intent, PICK_FROM_CAMERA);

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case PICK_FROM_ALBUM: {

                mImageCaptureUri = data.getData();
                Log.d("SmartWheel", mImageCaptureUri.getPath().toString());

            }


            case PICK_FROM_CAMERA: {


                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                // CROP할 이미지를 200*200 크기로 저장
                intent.putExtra("outputX", 200); // CROP한 이미지의 x축 크기
                intent.putExtra("outputY", 200); // CROP한 이미지의 y축 크기
                intent.putExtra("aspectX", 1); // CROP 박스의 X축 비율
                intent.putExtra("aspectY", 1); // CROP 박스의 Y축 비율
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_IMAGE); // CROP_FROM_CAMERA case문 이동
                break;


            }

            case CROP_FROM_IMAGE: {


                mImageCaptureUri = data.getData();
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.

                if (resultCode != RESULT_OK) {

                    return;

                }


                final Bundle extras = data.getExtras();



                // CROP된 이미지를 저장하기 위한 FILE 경로

                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        "/SmartWheel/" + System.currentTimeMillis() + ".jpg";

                if (extras != null)

                {
                    Bitmap photo = (Bitmap)extras.get("data"); // CROP된 BITMAP
                    ItemImage.setImageBitmap(photo); // 레이아웃의 이미지칸에 CROP된 BITMAP을 보여줌
                    storeCropImage(photo, filePath); // CROP된 이미지를 외부저장소, 앨범에 저장한다.
                    absoultePath = filePath;
                    break;

                }

                // 임시 파일 삭제

                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) {
                    f.delete();
                }
            }

        }

    }

    private void storeCropImage(Bitmap bitmap, String filePath) {

        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmartWheel";

        File directory_SmartWheel = new File(dirPath);

        if (!directory_SmartWheel.exists()) // SmartWheel 디렉터리에 폴더가 없다면 (새로 이미지를 저장할 경우에 속한다.)

            directory_SmartWheel.mkdir();


        File copyFile = new File(filePath);

        BufferedOutputStream out = null;


        try {

            copyFile.createNewFile();

            out = new BufferedOutputStream(new FileOutputStream(copyFile));

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);


            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,

                    Uri.fromFile(copyFile)));


            out.flush();

            out.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}



