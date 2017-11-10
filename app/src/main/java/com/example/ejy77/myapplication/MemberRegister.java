package com.example.ejy77.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ejy77.myapplication.DB.DBHelper;

/**
 * Created by ejy77 on 2017-10-08.
 */

public class MemberRegister extends AppCompatActivity
{

    EditText etname, etid, etpwd, etemail;
    RadioGroup rg1;
    Button rgbtn1, rgbtn2;
    DBHelper dbHelperUser;
    DBHelper dbHelperItem;
    SQLiteDatabase db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memregister);

        dbHelperUser = new DBHelper(getApplicationContext(), "logindb.db", null, 1);
        dbHelperItem = new DBHelper(getApplicationContext(), "itemdb.db", null, 1);

        rgbtn1 = (Button)findViewById(R.id.rgbtn1);
        rgbtn2 = (Button)findViewById(R.id.rgbtn2);
        etname = (EditText)findViewById(R.id.etname);
        etid = (EditText)findViewById(R.id.etid);
        etpwd = (EditText)findViewById(R.id.etpwd);
        etemail = (EditText)findViewById(R.id.etemail);
        rg1 = (RadioGroup)findViewById(R.id.rg1);


        rgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        rgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RadioButton rb = (RadioButton)findViewById(rg1.getCheckedRadioButtonId());


                String name = etname.getText().toString();
                String id = etid.getText().toString();
                String pwd = etpwd.getText().toString();
                String email = etemail.getText().toString();
                String sex = rb.getText().toString();

                String idcheck = etid.getText().toString();
                String sql = "SELECT * FROM UsersInfor WHERE id="+"'"+id+"'";
                db = dbHelperUser.getReadableDatabase();
                Cursor cursor = db.rawQuery(sql, null);
                int count = cursor.getCount();
                int i = 0;


                while(i < count)
                {
                    if(id.equals(idcheck))
                    {
                        Toast.makeText(getApplicationContext(),"이미 아이디 있음", Toast.LENGTH_LONG).show();
                        continue;
                    }

                    i++;
                }

                dbHelperUser.UsersInfoInsert(name, id, pwd, email, sex, null);
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);




            }
        });
    }


}
