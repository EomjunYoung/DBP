package com.example.ejy77.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejy77.myapplication.DB.DBHelper;

/**
 * Created by ejy77 on 2017-10-04.
 */

public class Login extends AppCompatActivity
{


    Button btn0, btn1;
    EditText et0, et1;
    DBHelper dbHelperUser;
    SQLiteDatabase db;
    static String userid;
    String sql = null;
    Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        et0 = (EditText)findViewById(R.id.et0);
        et1 = (EditText)findViewById(R.id.et1);
        dbHelperUser = new DBHelper(getApplicationContext(), "logindb4.db", null, 1);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = et0.getText().toString();
                String pwd = et1.getText().toString();
                String Cid = null;
                String Cpwd = null;

                if(id.length() == 0 || pwd.length() ==0 )
                {
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 필수 입력 사항입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }


                sql = "SELECT * FROM UsersInfor2 WHERE id="+"'"+id+"'";
                db = dbHelperUser.getReadableDatabase();
                cursor = db.rawQuery(sql, null);
                int count = cursor.getCount();//튜플수

                for(int i=0; i<count; i++) {
                    if (count != 0) {
                        cursor.moveToFirst();
                        Cid = cursor.getString(cursor.getColumnIndex("id"));
                        Cpwd = cursor.getString(cursor.getColumnIndex("pwd"));
                    }

                    Log.d("id", Cid);
                    Log.d("id", Cpwd);
                }

                if ( (id.equals(Cid)) && (pwd.equals(Cpwd)))
                {

                    startActivity(new Intent(getApplicationContext(), MainActivity2.class).putExtra("login", id));
                    Toast.makeText(getApplicationContext(), "로그인 성공!"+id, Toast.LENGTH_LONG).show();


                }

                else
                {
                    Toast.makeText(getApplicationContext(), "정보불일치", Toast.LENGTH_LONG).show();
                }

                cursor.close();


                /*if(cursor.getCount() != 1) // getCount = 레코드 수
                {
                    Toast.makeText(getApplicationContext(), "존재하지 않는 아이디입니다", Toast.LENGTH_LONG).show();
                    return;

                }*/



            }
        });




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MemberRegister.class);
                startActivity(intent);
            }
        });


    }
}
