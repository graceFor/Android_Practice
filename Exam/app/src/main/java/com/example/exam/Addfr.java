package com.example.exam;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Addfr extends AppCompatActivity {
    EditText name, phone, birth;
    Button save;
    SQLiteDatabase sqlDB;
    MyDBHelper myDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfr);
        setTitle("친구 목록 관리 앱");

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        birth = findViewById(R.id.birth);
        save= findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("데이터베이스앱실습","MainActivity: btnInsert.onClick");
                myDBHelper = new MyDBHelper(getApplicationContext());
                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO friends VALUES ('"
                        + name.getText().toString() + "', '"
                        + phone.getText().toString() +"', '"+birth.getText().toString()+"');");
                sqlDB.close();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "총 "+"명의 친구가 등록되어 있음",Toast.LENGTH_SHORT).show();


            }
        });



    }
}
