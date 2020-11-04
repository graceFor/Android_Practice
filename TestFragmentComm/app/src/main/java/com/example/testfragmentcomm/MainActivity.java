package com.example.testfragmentcomm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAlister, FragmentB.FragmentBlister{
    private FragmentA fragmentA;
    private
    FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cotainer_a, fragmentA);
        fragmentTransaction.replace(R.id.cotainer_b, fragmentB);
        fragmentTransaction.commit();


    }

    @Override
    public void onInputASent(CharSequence input) {
        fragmentB.updateEditTextA(input);
    }

    @Override
    public void onInputBSent(CharSequence input) {
        fragmentA.updateEditTextA(input);
    }
}