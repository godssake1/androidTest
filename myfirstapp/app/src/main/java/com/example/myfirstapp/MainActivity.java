package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean reset;
    public TextView textview;
    public String lastChar;

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset  = false;
        textview = (TextView) findViewById(R.id.textView2);
        lastChar = "";
    }

    public void onNumClick(View view) {

        //TextView textview = (TextView) findViewById(R.id.textView2);

        // 리셋하고 난뒤 로직
        if (reset == true) {

            textview.setText("");
            reset = false;
        }

        Button button = (Button) findViewById(view.getId());
        textview.setText(textview.getText().toString()+button.getText().toString());
        lastChar=button.getText().toString();
    }

    public void onOpClick(View view) {

        //TextView textview = (TextView) findViewById(R.id.textView2);


        // 아무것도 없는 상태에서 =을 눌렀을때 아무행동을 하지 않도록 하는 로직
        if (reset == true && !textview.getText().equals("")) {
            reset = false;
        }


        // 연산자가 먼저 들어가지 않도록하는 로직
        if (textview.getText().equals("")) {
            reset = false;
            return;
        }


        // 연산자이면 다른 연산자가 기록되지 않도록하는 로직
        if (lastChar.equals("+")||lastChar.equals("-")||lastChar.equals("*")||lastChar.equals("/")) {
            textview.setText(
                    textview.getText().subSequence(0,textview.getText().length()-1)
            );
        }

        Button button = (Button) findViewById(view.getId());
        textview.setText(textview.getText().toString()+button.getText().toString());
        lastChar=button.getText().toString();
    }

    public void onCalculateClick(View view) {
        String equation;

        // 마지막 문자가 연산자일때 연산자를 무시하도록하는 로직
        if (lastChar.equals("+")||lastChar.equals("-")||lastChar.equals("*")||lastChar.equals("/")) {
            equation = textview.getText().subSequence(0,textview.getText().length()-1).toString();
        }
        else {
            equation = textview.getText().toString();
        }
        //TextView textview = (TextView) findViewById(R.id.textView2);

        // 숫자 어레이
        String[] numStrings= equation.split("\\+|\\-|\\*|\\/");
        // 연산자 어레이
        String[] opStrings= equation.split("[0-9]|[0-9]+.+[0-9]");
        // 리턴값
        Double d = Double.parseDouble(numStrings[0]);

        for (String s:opStrings) {
            int i = 1;
            switch (s) {
                case "+": d += Double.parseDouble(numStrings[i]);
                    break;
                case "-": d -= Double.parseDouble(numStrings[i]);
                    break;
                case "*": d *= Double.parseDouble(numStrings[i]);
                    break;
                case "/": d /= Double.parseDouble(numStrings[i]);
                    break;
                default:
                    break;
            }
            i++;
        }
        textview.setText(d.toString());
        reset = true;
    }

    public void onClear(View view) {
        reset = false;
        //TextView textview = (TextView) findViewById(R.id.textView2);
        textview.setText("");
    }

}