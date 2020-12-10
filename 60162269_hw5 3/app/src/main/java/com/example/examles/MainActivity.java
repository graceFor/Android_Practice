package com.example.examles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqlDB = null;
    MyDBHelper myDBHelper;
    DrawerLayout mDrawerLayout;
    public static FragmentManager fm;
    public static FragmentTransaction ft;
    private String posterName [];
    {posterName = new String[]{"제목1","제목2","제목3","제목4","제목5","제목6","제목7","제목8","제목9"};}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화투표 앱");

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.movie_message:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MovieFragment()).commit();
                        break;
                    case R.id.result:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ResultFragment()).commit();
                        break;
                    case R.id.result_init:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ResultInitFragment()).commit();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        myDBHelper = new MyDBHelper(this);

        try {
            sqlDB = myDBHelper.getReadableDatabase();
            sqlDB.close();

        } catch (NullPointerException e) {
            sqlDB = myDBHelper.getWritableDatabase();
            myDBHelper.onUpgrade(sqlDB, 1, 2); // 조건문으로 해서 처리할 예정 우선은 실행시키려고 해놓음
            for (int i=0; i<posterName.length; i++ ) {
                sqlDB.execSQL("insert into movieDB values ('"
                        + posterName[i]+ "', 0);");
            }
            sqlDB.close();
            return ;
        }
    }


    //프래그먼트 끼리의 화면 전환을 함
    public void replaceFragment(Fragment fragment, Bundle msg){
        fragment.setArguments(msg);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }


    }
}