package com.example.ex_10_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    Button button;
    RadioButton radioButton1, radioButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioButton1 = (RadioButton)findViewById(R.id.Rdi_second);
        radioButton2 = (RadioButton)findViewById(R.id.Rdi_third);
        button = (Button)findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton1.isChecked()){
                    Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                    String name = "John";
                    Integer age = 25;
                    intent.putExtra("Name", name);
                    intent.putExtra("Age", age);
                    startActivity(intent);

                }
                else if(radioButton2.isChecked()){
                    Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
                    String name = "Alice";
                    Integer age = 33;
                    intent.putExtra("Name", name);
                    intent.putExtra("Age", age);
                    startActivity(intent);
                }

            }
        });



    }
}