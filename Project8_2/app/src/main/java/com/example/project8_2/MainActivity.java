package com.example.project8_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    TextView textView;
    MyPictureView myPicture;
    int curNum = 0;
    String imageFname;
    File[] imageFiles;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1000:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    imageFiles = new File("/storage/emulated/0/Pictures").listFiles();
                   // imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures").listFiles();
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    myPicture.invalidate();
                    btnPrev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(curNum <= 0) {
                                curNum = imageFiles.length-1;
                                imageFname = imageFiles[curNum].toString();
                                myPicture.imagePath = imageFname;
                                myPicture.invalidate();

                            }
                            else {
                                curNum--;
                                imageFname = imageFiles[curNum].toString();
                                myPicture.imagePath = imageFname;
                                myPicture.invalidate();
                            }
                            textView.setText(curNum+1 + " / " + imageFiles.length);
                        }
                    });

                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(curNum >= imageFiles.length-1) {
                                curNum = 0; // 마지막 그림 다음 버튼은 첫 번째 그림으로 가
                                imageFname = imageFiles[curNum].toString();
                                myPicture.imagePath = imageFname;
                                myPicture.invalidate();
                            }
                            else {
                                curNum++;
                                imageFname = imageFiles[curNum].toString();
                                myPicture.imagePath = imageFname;
                                myPicture.invalidate();
                            }
                            textView.setText(curNum+1 + " / " + imageFiles.length);

                        }
                    });

                }
                else {
                    Toast.makeText(getApplicationContext(), "퍼미션을 허용해주세요.",Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단한 이미지 뷰어");
        ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);

        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        textView = findViewById(R.id.textView);
        myPicture = findViewById(R.id.myPictureView1);

    }
}