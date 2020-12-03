package com.example.project12_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;
    MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtNumberResult = findViewById(R.id.edtNumberResult);

        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);

        myDBHelper = new MyDBHelper(MainActivity.this);

        //초기화 버튼 - DB table 리셋
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("데이터베이스앱실습","MainActivity: btnInit.onClick");
                sqlDB = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        //삽입 버튼
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("데이터베이스앱실습","MainActivity: btnInsert.onClick");
                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ('"
                        + edtName.getText().toString() + "', "
                        + edtNumber.getText().toString() +");");
                sqlDB.close();
            }
        });

        //조회 버튼
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("데이터베이스앱실습","MainActivity: btnSelect.onClick");
                sqlDB = myDBHelper.getReadableDatabase();

                Cursor cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;",null);

                String strNames = "그룹이름" + "\r\n" + "-------------" + "\r\n";
                String strNumbers = "인원" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()){ // 한행씩 옮겨가면서 데이터 읽어오기
                    strNames += cursor.getString(0) + "\r\n"; // 0은 그룹이름 column
                    strNumbers += cursor.getString(1) + "\r\n"; // 1은 그룹인원수 column
                }

                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });


    }
    public class MyDBHelper extends SQLiteOpenHelper{

        public MyDBHelper(Context context){
            super(context, "groupDB", null, 1); // groupDB이름의 데이터베이스 ㅐㅇ성
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) { // Table 생성
            Log.i("데이터베이스앱실습","MyDBHelper: onCreate");
            sqLiteDatabase.execSQL("CREATE TABLE groupTBL ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER );");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.i("데이터베이스앱실습","MyDBHelper: onUpgrade");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(sqLiteDatabase);

        }
    }
}