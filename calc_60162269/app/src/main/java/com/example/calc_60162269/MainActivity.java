package com.example.calc_60162269;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.ref.SoftReference;
import java.util.Stack;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    EditText result;
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            String value = button.getText().toString();

            if("=".equals(value)){
                result.setText( Calc (result.getText().toString()));
            }
            else {
                result.setText(result.getText().toString() + value);
            }
        }

    };
    private String Calc(String formulaStr){
        StringTokenizer st_num  =new StringTokenizer(formulaStr,"+-/* ");
        StringTokenizer st_oper =new StringTokenizer(formulaStr,"1234567890 ");

        Stack<Integer> valueStack =new Stack <Integer>();
        valueStack.push(Integer.parseInt(st_num.nextToken()));
        while(st_num.hasMoreTokens()){
            String operator = st_oper.nextToken();
            String num = st_num.nextToken();
            int a;

            if ("*".equals(operator)){
                a = valueStack.pop();
                valueStack.push( a * Integer.parseInt(num) );
            }
            else if ("/".equals(operator)){
                a = valueStack.pop();
                valueStack.push( a / Integer.parseInt(num) );
            }
            else if ("+".equals(operator)){
                valueStack.push(Integer.parseInt(num));
            }
            else if ("-".equals(operator)){
                valueStack.push(-1 * (Integer.parseInt(num)));
            }
        }

        int tot =0;
        while(!valueStack.isEmpty()){
            tot += valueStack.pop();
        }

        return Integer.toString(tot);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);





        String[] chars = {"7","8","9","*", "4","5","6","-","1","2","3","+","0","=","/"};

        LinearLayout parent = (LinearLayout)result.getParent();
        LinearLayout layout = null;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight =1;
        for(int i=0; i<chars.length; i++) {
            if (i % 4 == 0) {
                layout = new LinearLayout(this);
                layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.setOrientation(LinearLayout.HORIZONTAL);
                parent.addView(layout);
            }
            Button button = new Button(this);
            button.setText(chars[i]);
            button.setLayoutParams(params);
            button.setOnClickListener(mClickListener);
            layout.addView(button);
        }
    }



}