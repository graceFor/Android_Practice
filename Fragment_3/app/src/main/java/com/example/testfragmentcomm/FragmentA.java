package com.example.testfragmentcomm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {
    private EditText frag_a_edit;
    public static FragmentA newInstance() {
        return new FragmentA();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        frag_a_edit = (EditText)v.findViewById(R.id.frag_a_edit);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = this.getArguments();
        frag_a_edit.setText(bundle.getString("message"));
    }
}
