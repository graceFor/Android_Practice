package com.example.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.jar.Attributes;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqlDB = null;
    MyDBHelper myDBHelper;
    DrawerLayout mDrawerLayout;
    public static FragmentManager fm;
    public static FragmentTransaction ft;
    LinearLayout baseLayout;
    Context context;
    private ArrayList<String> array_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("친구 목록 관리 앱");

        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        myDBHelper = new MyDBHelper(this);

        try {
            sqlDB = myDBHelper.getReadableDatabase();
            sqlDB.close();

        } catch (NullPointerException e) {
            sqlDB = myDBHelper.getWritableDatabase();
            myDBHelper.onUpgrade(sqlDB, 1, 2); // 조건문으로 해서 처리할 예정 우선은 실행시키려고 해놓음
            sqlDB.close();
            Toast.makeText(getApplicationContext(), "총 0명의 친구가 등록되어 있음",Toast.LENGTH_SHORT).show();
            return ;
        }
        context = getApplicationContext().getApplicationContext();
        ListView listView = (ListView)findViewById(R.id.fr);
        array_name = new ArrayList<>();
        myDBHelper = new MyDBHelper(context);
        sqlDB = myDBHelper.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT name FROM friends;",null);
        while (cursor.moveToNext()){
            array_name.add(cursor.getString(0));
        }
        cursor.close();
        sqlDB.close();
        NameAdapter nameAdapter = new NameAdapter(context, array_name);
        listView.setAdapter(nameAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addfr:
                Intent intent = new Intent(getApplicationContext(), Addfr.class);
                startActivity(intent);
                return true;

        }
        return false;
    }
}

    //    //프래그먼트 끼리의 화면 전환을 함
//    public void replaceFragment(Fragment fragment, Bundle msg){
//        fragment.setArguments(msg);
//        fm = getSupportFragmentManager();
//        ft = fm.beginTransaction();
//        ft.replace(R.id.fragment_container,fragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
//            mDrawerLayout.closeDrawer(GravityCompat.START);
//        }else {
//            super.onBackPressed();
//        }
//
//
//    }
