package com.example.login_60162269;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    Button end;
    EditText num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000000);
        for(int i = 60140000; i<=60180000; i++ ){
            String is = Integer.toString(i);
            bloomFilter.put(is);
        }

        num = findViewById(R.id.num);
        end = findViewById(R.id.end);
        final String string = num.getText().toString();

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bloomFilter.mightContain(string)){
                    Toast toast = Toast.makeText(getApplicationContext(),"등록된 ID입니다.",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"등록되어 있지 않는 ID입니다.",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });








    }
}