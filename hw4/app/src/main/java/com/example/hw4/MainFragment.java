package com.example.hw4;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {
    private Button btnFragA, btnFragB, btnFragC;
    private EditText edit_msg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfragment, container, false);

        btnFragA = view.findViewById(R.id.btnFragA);
        btnFragB = view.findViewById(R.id.btnFragB);
        btnFragC = view.findViewById(R.id.btnFragC);
        edit_msg = view.findViewById(R.id.edit_msg);

        btnFragA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle msg = new Bundle();
                msg.putString("MSG",edit_msg.getText().toString());
                Log.d("Tag","a 버튼 클릭"+ String.valueOf(msg));
                ((MainActivity)getActivity()).replaceFragment(FragmentA.newInstance(),msg);
            }
        });
        btnFragB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle msg = new Bundle();
                msg.putString("MSG",edit_msg.getText().toString());
                Log.d("Tag", "b 버튼 클릭"+String.valueOf(msg));
                ((MainActivity)getActivity()).replaceFragment(FragmentB.newInstance(),msg);
            }
        });
        btnFragC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle msg = new Bundle();
                msg.putString("MSG",edit_msg.getText().toString());
                Log.d("Tag", "c 버튼 클릭"+ String.valueOf(msg));
                ((MainActivity)getActivity()).replaceFragment(FragmentC.newInstance(),msg);
            }
        });
        return view;
    }

}
