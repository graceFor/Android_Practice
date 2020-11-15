package com.example.testfragmentcomm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class FragmentMain extends Fragment {
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private EditText edittext;
    private Button btnfragA, btnfragB, btnfragC;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentC = new FragmentC();

        btnfragA = v.findViewById(R.id.btnFraga);
        btnfragB = v.findViewById(R.id.btnFragb);
        btnfragC = v.findViewById(R.id.btnFragc);
        edittext = v.findViewById(R.id.edittext);


        btnfragA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("message",edittext.getText().toString());
                fragmentA.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frag_main, fragmentA);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        btnfragB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("message",edittext.getText().toString());
                fragmentB.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frag_main, fragmentB);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        btnfragC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("message",edittext.getText().toString());
                fragmentC.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frag_main, fragmentC);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return v;
    }
}
