package com.example.ejy77.myapplication.itemManage;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ejy77.myapplication.DB.DBHelper;
import com.example.ejy77.myapplication.MainActivity2;
import com.example.ejy77.myapplication.R;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static android.R.attr.bitmap;
import static android.R.attr.start;
import static android.R.attr.x;
import static com.example.ejy77.myapplication.R.id.ItemType;
import static com.example.ejy77.myapplication.R.id.buttonPurchase;
import static com.example.ejy77.myapplication.R.id.buttonSell;

/**
 * Created by ejy77 on 2017-12-01.
 */

public class ItemSell extends AppCompatActivity {

    private static final int PICK_FROM_CAMERA = 0;//사진촬영 후 찍힌 이미지처리
    private static final int PICK_FROM_ALBUM = 1;//앨범에서 사진고르고 이미지처리
    private static final int CROP_FROM_IMAGE = 2;//이미지를 크롭함
    private EditText ItemName, ItemNation, ItemPrice, ItemNumber;
    private ImageView ItemPicture;
    private String name, nation, price, number, type;
    private Spinner ItemType;


    private String absoultePath;
    private Uri mImageCaptureUri;
    DBHelper dbHelperItem;
    SQLiteDatabase db;
    Button btngallery, btnCheck, btnCancel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);


        dbHelperItem = new DBHelper(getApplicationContext(), "itemdb5.db", null, 1);
        ItemName = (EditText) findViewById(R.id.ItemName);
        ItemNation = (EditText) findViewById(R.id.ItemNation);
        ItemPrice = (EditText)findViewById(R.id.ItemPrice);
        ItemNumber = (EditText)findViewById(R.id.ItemNumber);
        ItemPicture = (ImageView)findViewById(R.id.ItemPicture);
        ItemType = (Spinner)findViewById(R.id.ItemType);
        btngallery = (Button)findViewById(R.id.btngallery);
        btnCheck = (Button)findViewById(R.id.btnCheck);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        name = ItemName.getText().toString();

        /*final ArrayList<String> list = new ArrayList<>();
        list.add("스포츠/레저");
        list.add("화장품/미용");
        list.add("패션의류");
        list.add("가구/인테리어");
        list.add("여행/문화");
        list.add("도서/음반/DVD");*/

        String[] list = new String[8];
        list[0] = "스포츠/레저";
        list[1] = "화장품/미용";
        list[2] = "패션의류";
        list[3] = "가구/인테리어";
        list[4] = "여행/문화";
        list[5] = "도서/음반/DVD";
        list[6] = "컴퓨터/노트북";
        list[7] = "휴대폰/전자기기";


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        ItemType.setAdapter(arrayAdapter);


        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(ItemSell.this);
                LayoutInflater inflater = getLayoutInflater();
                View view2 = inflater.inflate(R.layout.dialog_gallery, null);
                ad.setView(view2);
                final Button buttonGallery = (Button) view2.findViewById(R.id.buttonGallery);
                final Button buttonCamera = (Button) view2.findViewById(R.id.buttonCamera);
                final Button buttonCancel = (Button) view2.findViewById(R.id.buttonCancel);
                final AlertDialog dialog = ad.create();

                buttonGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        doTakeAlbumAction();
                        dialog.dismiss();
                    }
                });

                buttonCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        doTakePhotoAction();
                        dialog.dismiss();

                    }
                });

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                //doTakeAlbumAction();
               // doTakePhotoAction();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String sql = "select _id from Eoms";
                Cursor cursor = dbHelperItem.getReadableDatabase().rawQuery(sql, null);
                int count = cursor.getCount();*/

               // Intent intent = new Intent(ItemSell.this, MainActivity2.class);
               // startActivity(intent);
                finish();

                //String type = (String)ItemType.getItemAtPosition(i);
                //Toast.makeText(getApplicationContext(), count+"", Toast.LENGTH_SHORT).show();
                //dbHelperItem.SKshopImageInsert(name, nation, price, number, bytes, type, (count+1)+"");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

                    String sql = "select _id from Eoms";
                    Cursor cursor = dbHelperItem.getReadableDatabase().rawQuery(sql, null);
                    int count = cursor.getCount();

                    Bitmap photo = (Bitmap)extras.get("data"); // CROP된 BITMAP
                    ItemPicture.setImageBitmap(photo); // 레이아웃의 이미지칸에 CROP된 BITMAP을 보여줌
                    storeCropImage(photo, filePath); // CROP된 이미지를 외부저장소, 앨범에 저장한다.
                    final byte[] bytes = getByteArrayFromDrawable2(photo);

                    name = ItemName.getText().toString();
                    nation = ItemNation.getText().toString();
                    price = ItemPrice.getText().toString();
                    number = ItemNumber.getText().toString();
                    type = ItemType.getSelectedItem().toString();

                    Log.d("eom", name);
                    Log.d("eom", count+"");

                    dbHelperItem.SKshopImageInsert(name, nation, price, number, bytes, type, (count+1)+"");
                    Log.d("eom", "db등록완료");


                    ItemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    Log.d("eom", "디비삽입완료");
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

    public byte[] getByteArrayFromDrawable2(Bitmap b) {
        Bitmap bitmap = b;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();

        return data;
    }


}



