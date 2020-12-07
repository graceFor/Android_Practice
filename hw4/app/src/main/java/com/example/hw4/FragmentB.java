package com.example.hw4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {
    public static FragmentB newInstance(){return new FragmentB();}
    private EditText edit_msg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        edit_msg = view.findViewById(R.id.edit_msg);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        Bundle message = this.getArguments();
        edit_msg.setText(message.getString("MSG"));
    }
}
