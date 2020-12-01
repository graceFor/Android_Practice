package com.example.examles;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    SQLiteDatabase sqlDB;
    TextView edtNameResult;
    RatingBar edtNumberResult;
    MyDBHelper myDBHelper;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.result, container, false);

        TextView[] edtNameResult = {
                view.findViewById(R.id.n1), view.findViewById(R.id.n2), view.findViewById(R.id.n3), view.findViewById(R.id.n4),
                view.findViewById(R.id.n5), view.findViewById(R.id.n6), view.findViewById(R.id.n7), view.findViewById(R.id.n8),
                view.findViewById(R.id.n9),};

        RatingBar[] edtNumberResult = {
                view.findViewById(R.id.rbar1), view.findViewById(R.id.rbar2), view.findViewById(R.id.rbar3), view.findViewById(R.id.rbar4),
                view.findViewById(R.id.rbar5), view.findViewById(R.id.rbar6), view.findViewById(R.id.rbar7), view.findViewById(R.id.rbar8),
                view.findViewById(R.id.rbar9)};


        myDBHelper = new MyDBHelper(getContext());

        sqlDB = myDBHelper.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM movieDB;", null);
        int i =0;

        while (cursor.moveToNext()) { // 한행씩 옮겨가면서 데이터 읽어오기
            edtNameResult[i].setText(cursor.getString(0));
            edtNumberResult[i].setRating(cursor.getInt(1));
            i++;
        }

        cursor.close();
        sqlDB.close();

        return view;
    }
}
