package com.example.exercise7_6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton rdDog, rdCat,rdRabbit;
    Button bt1;
    ImageView dialogImg;
    TextView toastText;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdDog=(RadioButton) findViewById(R.id.dog);
        rdCat=(RadioButton) findViewById(R.id.cat);
        rdRabbit=(RadioButton) findViewById(R.id.rabbit);
        bt1 = (Button) findViewById(R.id.button1);

        bt1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder((MainActivity.this));

                dlg.setIcon(R.drawable.ic_baseline_group_24);
                dialogImg = dialogView.findViewById(R.id.DialogImg);
                dlg.setView(dialogView);

                if(rdDog.isChecked()){
                    dialogImg.setImageResource(R.drawable.dog);
                    dlg.setTitle("강아지");
                }
                else if(rdCat.isChecked()){
                    dialogImg.setImageResource(R.drawable.cat);
                    dlg.setTitle("고양이");
                }
                else if(rdRabbit.isChecked()){
                    dialogImg.setImageResource(R.drawable.rabbit);
                    dlg.setTitle("토끼");
                }


                dlg.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(MainActivity.this);
                        toastView = (View) View.inflate(MainActivity.this, R.layout.toast1, null);
                        toastText = (TextView)toastView.findViewById(R.id.toastText1);
                        toastText.setText("대화상자를 닫았습니다");
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });
    }
}