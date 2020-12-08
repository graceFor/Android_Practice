package com.example.exam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context){
        super(context, "friends", null, 1); // groupDB이름의 데이터베이스 생성
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { // Table 생성
        Log.i("데이터베이스앱실습","MyDBHelper: onCreate");
        sqLiteDatabase.execSQL("CREATE TABLE friends ( name CHAR(30) PRIMARY KEY, phone CHAR(20), birthday CHAR(20) );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("데이터베이스앱실습","MyDBHelper: onUpgrade");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS friends");
        onCreate(sqLiteDatabase);

    }
}