package com.example.cal_9_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button add, sub, mul, div, res;
    TextView textresult;
    String num1, num2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기");

        edit1 = (EditText)findViewById(R.id.Edit1);
        edit2 = (EditText)findViewById(R.id.Edit2);
        add = (Button)findViewById(R.id.bt1);
        sub = (Button)findViewById(R.id.bt2);
        mul = (Button)findViewById(R.id.bt3);
        div = (Button)findViewById(R.id.bt4);
        res = (Button)findViewById(R.id.bt5);
        textresult = (TextView)findViewById((R.id.TextResult));

        add.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent arg1) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();

                if (num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"값이 다 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();

                }
                else {
                    result = Double.parseDouble(num1)+Double.parseDouble(num2);
                    textresult.setText("계산 결과 : "+result.toString());
                }
                return false;
            }
        });


        sub.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent arg1) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();

                if (num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"값이 다 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();

                }
                else {
                    result = Double.parseDouble(num1)-Double.parseDouble(num2);
                    textresult.setText("계산 결과 : "+result.toString());
                }
                return false;
            }
        });

        mul.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent arg1) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();

                if (num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"값이 다 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();

                }
                else {
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                    textresult.setText("계산 결과 : "+result.toString());
                }
                return false;
            }
        });


        div.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent arg1) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();

                if (num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"값이 다 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();

                }
                else {
                    if(num2.trim().equals("0")){
                        Toast.makeText(getApplicationContext(),"0으로 나눌 수 없습니다.",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        result = Double.parseDouble(num1) / Double.parseDouble(num2);
                        textresult.setText("계산 결과 : "+result.toString());
                    }
                }
                return false;
            }
        });

        res.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent arg1) {
                num1=edit1.getText().toString();
                num2=edit2.getText().toString();

                if (num1.trim().equals("") || num2.trim().equals("")){
                    Toast.makeText(getApplicationContext(),"값이 다 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();

                }
                else {
                    if(num2.trim().equals("0")){
                        Toast.makeText(getApplicationContext(),"0으로 나누면 나머지 값을 알 수 없습니다.",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        result = Double.parseDouble(num1) % Double.parseDouble(num2);
                        textresult.setText("계산 결과 : "+result.toString());
                    }
                }
                return false;
            }
        });





    }
}