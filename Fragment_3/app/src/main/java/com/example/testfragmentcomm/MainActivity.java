package com.example.testfragmentcomm;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity{

    private FragmentMain fragmentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMain = new FragmentMain();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.frag_main, fragmentMain);
        fragmentTransaction.commit();

    }
}