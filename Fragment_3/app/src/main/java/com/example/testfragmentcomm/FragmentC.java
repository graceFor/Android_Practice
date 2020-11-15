package com.example.testfragmentcomm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentC extends Fragment {
    private EditText editText;
    public static FragmentC newInstance() { return new FragmentC(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_c, container, false);
        editText =v.findViewById(R.id.frag_c_edit);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = this.getArguments();
        editText.setText(bundle.getString("message"));}

}
