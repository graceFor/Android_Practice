package com.example.example_10_16;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("계산기");



        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radiogroup);

        Button result = (Button)findViewById(R.id.btnresult);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtNum1 = (EditText)findViewById(R.id.edtNum1);
                EditText edtNum2 = (EditText)findViewById(R.id.edtNum2);
                Intent intent;
                switch (radioGroup.getCheckedRadioButtonId()){
                    case  R.id.add:
                        intent = new Intent(MainActivity.this,AddActivity.class);
                        intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));
                        intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));
                        startActivityForResult(intent,1000);
                }

                switch (radioGroup.getCheckedRadioButtonId()){
                    case  R.id.sub:
                        intent = new Intent(MainActivity.this,SubActivity.class);
                        intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));
                        intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));
                        startActivityForResult(intent,1001);
                }
                switch (radioGroup.getCheckedRadioButtonId()){
                    case  R.id.mul:
                        intent = new Intent(MainActivity.this,MulActivity.class);
                        intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));
                        intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));
                        startActivityForResult(intent,1002);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText edtResult = (EditText)findViewById(R.id.edtresult);
        switch (requestCode){
            case 1000:
                if(resultCode==RESULT_OK){
                    int result = data.getExtras().getInt("AddResult");
                    edtResult.setText(""+result);
                }
                break;
            case 1001:
                if(resultCode==RESULT_OK){
                    int result = data.getExtras().getInt("SubResult");
                    edtResult.setText(""+result);
                }
                break;
            case 1002:
                if(resultCode==RESULT_OK){
                    int result = data.getExtras().getInt("MulResult");
                    edtResult.setText(""+result);
                }
                break;
        }

    }
}