package com.example.hw4;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {
    private EditText edit_msg;

    public static FragmentA newInstance() {
        return new FragmentA();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
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
