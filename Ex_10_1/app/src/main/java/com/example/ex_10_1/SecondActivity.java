package com.example.ex_10_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    Button btn;
    EditText editText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        btn = (Button)findViewById(R.id.btnReturn);
        editText = (EditText)findViewById(R.id.edt1);
        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();

        if(extras != null){
            String name = extras.getString("Name");
            Integer age = extras.getInt("Age");
            editText.setText("Received name: " +name + " , Received age: "+age);

        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}