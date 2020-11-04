package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnRed, btnGreen, btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRed=(Button)findViewById(R.id.btnRed);
        btnGreen=(Button)findViewById(R.id.btnGreen);
        btnBlue=(Button)findViewById(R.id.btnBlue);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MyColorFragment fragWhite = new MyColorFragment();
        fragmentTransaction.add(R.id.frame_layout_1, fragWhite);
        fragmentTransaction.commit();

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putString("ColorSelected", "red");
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                MyColorFragment fragRed = new MyColorFragment();
                fragRed.setArguments(data);
                fragmentTransaction.add(R.id.frame_layout_1, fragRed);
                fragmentTransaction.commit();
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putString("ColorSelected", "green");
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                MyColorFragment fragGreen = new MyColorFragment();
                fragGreen.setArguments(data);
                fragmentTransaction.add(R.id.frame_layout_1, fragGreen);
                fragmentTransaction.commit();
            }
        });


        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data = new Bundle();
                data.putString("ColorSelected", "blue");
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                MyColorFragment fragBlue = new MyColorFragment();
                fragBlue.setArguments(data);
                fragmentTransaction.add(R.id.frame_layout_1, fragBlue);
                fragmentTransaction.commit();
            }
        });





    }
}