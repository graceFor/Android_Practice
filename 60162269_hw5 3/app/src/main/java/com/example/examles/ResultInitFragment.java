package com.example.examles;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultInitFragment extends Fragment {
    SQLiteDatabase sqlDB;
    TextView edtNameResult, edtNumberResult;
    MyDBHelper myDBHelper;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.resultinit,container,false);
        TextView[] edtNameResult = {
                view.findViewById(R.id.n1), view.findViewById(R.id.n2), view.findViewById(R.id.n3), view.findViewById(R.id.n4),
                view.findViewById(R.id.n5), view.findViewById(R.id.n6), view.findViewById(R.id.n7), view.findViewById(R.id.n8),
                view.findViewById(R.id.n9),};
        TextView[] edtNumberResult = {
                view.findViewById(R.id.g1), view.findViewById(R.id.g2), view.findViewById(R.id.g3), view.findViewById(R.id.g4),
                view.findViewById(R.id.g5), view.findViewById(R.id.g6), view.findViewById(R.id.g7), view.findViewById(R.id.g8),
                view.findViewById(R.id.g9)};


        myDBHelper = new MyDBHelper(getContext());
        Log.i("데이터베이스앱실습","MainActivity: btnSelect.onClick");
        sqlDB = myDBHelper.getWritableDatabase();
        sqlDB.execSQL("update movieDB set gNumber = 0");
        sqlDB = myDBHelper.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM movieDB;",null);

        int i = 0, temp;
        while (cursor.moveToNext()) {
            edtNameResult[i].setText(cursor.getString(0));
            edtNumberResult[i].setText(Integer.toString(cursor.getInt(1)));
            i++;
        }

        cursor.close();
        sqlDB.close();


        return view;
    }
}
