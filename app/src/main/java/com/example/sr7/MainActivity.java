package com.example.sr7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton = findViewById(R.id.brn_next);
        EmailValidator validator = new EmailValidator();

        myButton.setEnabled(false);
        myButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C9D4FB")));

        EditText editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myButton.setEnabled(false);
                myButton.getBackground();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                myButton.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputText = editable.toString();
                if (validator.validate(inputText)) {
                    myButton.setEnabled(true);
                    myButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1A6FEE")));
                    myButton.setTextColor(ColorStateList.valueOf(Color.WHITE));
                } else {
                    myButton.setEnabled(false);
                    myButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C9D4FB")));
                }

            }
        });


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}