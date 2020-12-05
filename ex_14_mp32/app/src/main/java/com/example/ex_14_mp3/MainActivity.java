package com.example.ex_14_mp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewMP3;
    Button btnPlay, btnStop;
    TextView tvMP3;
    SeekBar seekBar;
    TextView tvProgress;

    ArrayList<String> mp3List;
    String selected;

    String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/Music";
    MediaPlayer mediaPlayer;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1000:
                if(grantResults.length>0 &&grantResults[0]== PackageManager.PERMISSION_GRANTED){ // 접근 허용 했을때


                    mp3List = new ArrayList<String>();

                    // 각 mp3 파일 가져옴
                    File[] listFiles = new File(mp3Path).listFiles();

//                    for (File file :listFiles){
//                        file.delete();
//                    }
                    for (File file :listFiles){
                        mp3List.add(file.getName());
                    }
                    listViewMP3 = (ListView)findViewById(R.id.listViewMp3);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_single_choice,
                            mp3List);
                    listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    listViewMP3.setAdapter(arrayAdapter);
                    listViewMP3.setItemChecked(0,true); // default로는 첫번째 파일 선택
                    selected = mp3List.get(0);

                    // 리스트뷰에 있는 아이템들 사용자가 선택했을 때,
                    listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            selected = mp3List.get(i); // 사용자가 선택한 파일

                        }
                    });
                    btnPlay = (Button)findViewById(R.id.btnPlay);
                    btnStop = (Button)findViewById(R.id.btnStop);
                    tvMP3 = (TextView)findViewById(R.id.tvMP3);
                    tvProgress = (TextView)findViewById(R.id.tvTime);
                    seekBar = (SeekBar)findViewById(R.id.pbMP3);


                    btnPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                mediaPlayer = new MediaPlayer();
                                mediaPlayer.setDataSource(mp3Path+"/"+selected);
                                mediaPlayer.prepare();
                                btnPlay.setClickable(false);
                                btnStop.setClickable(true);
                                tvMP3.setText("재생 중인 음악 : " + selected);
                                seekBar.setMax(mediaPlayer.getDuration());
                                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        if(fromUser)  // 사용자가 시크바를 움직이면
                                            mediaPlayer.seekTo(progress);   // 재생위치를 바꿔준다(움직인 곳에서의 음악재생)
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {

                                    }
                                });
                                mediaPlayer.start();
                                new Thread(){
                                    SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                                    @Override
                                    public void run() {

                                        while (mediaPlayer.isPlaying()){ // 재생될 때, seekbar, 시간 Update
                                           try {
                                               Thread.sleep(1000);
                                           }
                                           catch (Exception e){
                                               e.printStackTrace();
                                           }
                                            runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {
                                                    seekBar.setProgress(mediaPlayer.getCurrentPosition()); // 현재까지 재생된 시간을 seekbar로 표현
                                                    tvProgress.setText("진행시간:"+timeFormat.format(mediaPlayer.getCurrentPosition()));
                                                }
                                            });
                                        }

                                    }
                                }.start();

                            }catch (IOException e){

                            }
                        }
                    });

                    btnStop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            btnPlay.setClickable(true);
                            btnStop.setClickable(false);
                            tvMP3.setText("재생 중인 음악 : " );
                        }
                    });

                    btnStop.setClickable(false);


                }
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //SDK 접근
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
    }
}