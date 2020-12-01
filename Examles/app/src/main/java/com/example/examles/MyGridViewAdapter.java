package com.example.examles;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyGridViewAdapter  extends BaseAdapter {
    private Context context;
    SQLiteDatabase sqlDB;
    MyDBHelper myDBHelper;

    public MyGridViewAdapter(Context c){
        context = c;
    }

    private Integer [] posterID = {R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,
            R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,
            R.drawable.mov08,R.drawable.mov09};
    private String posterName [];
    {posterName = new String[]{"제목1","제목2","제목3","제목4","제목5","제목6","제목7","제목8","제목9"};}

        @Override
    public int getCount() {
        return posterID.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new GridView.LayoutParams(200,300));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(5,5,5,5);
        imageView.setImageResource(posterID[position]);


        final int pos = position;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = (View)View.inflate(context, R.layout.dialog,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                ImageView ivPoster = (ImageView)dialogView.findViewById(R.id.ivPoster);
                Button button = (Button)dialogView.findViewById(R.id.goos);
                final TextView good = (TextView)dialogView.findViewById(R.id.good);
                TextView ivPosterName = (TextView)dialogView.findViewById(R.id.ivPosterName);
                ivPoster.setImageResource(posterID[pos]);
                myDBHelper = new MyDBHelper(context);
                sqlDB = myDBHelper.getReadableDatabase();
                Cursor cursor = sqlDB.rawQuery("select * from movieDB where gName = '" + posterName[pos]+"'" ,null);
                cursor.moveToFirst();
                ivPosterName.setText(cursor.getString(0));
                good.setText(cursor.getString(1));
                cursor.close();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int voteCount = Integer.parseInt(good.getText().toString());

                            voteCount++;
                            good.setText(voteCount + "");
                            myDBHelper = new MyDBHelper(context);
                            sqlDB = myDBHelper.getWritableDatabase();
                            sqlDB.execSQL("update movieDB set gNumber = '" + voteCount + "' where gName  = '" + posterName[pos] + "'");
                            sqlDB.close();


                    }
                });
                dlg.setView(dialogView);
                dlg.setNegativeButton("닫기",null);
                dlg.show();

            }
        });

        return imageView;
    }


}
