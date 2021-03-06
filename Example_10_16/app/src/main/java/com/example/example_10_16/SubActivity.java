package com.example.example_10_16;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);


        EditText edtresult = (EditText)findViewById(R.id.result);


        Intent rxintent = getIntent();
        final int result = rxintent.getExtras().getInt("Num1") - rxintent.getExtras().getInt("Num2");
        Integer num1 = rxintent.getExtras().getInt("Num1");
        Integer num2 = rxintent.getExtras().getInt("Num2");

        edtresult.setText(num1+" - "+ num2+" = "+ result);

        Button button = (Button)findViewById(R.id.btnReturn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent respIntent = new Intent(SubActivity.this, MainActivity.class);
                respIntent.putExtra("SubResult", result);
                setResult(RESULT_OK,respIntent);
                finish();

            }
        });
    }
}
