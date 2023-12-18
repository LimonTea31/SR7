package com.example.sr7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    int nober = 0;
    TextView TVtimer;
    EditText edNom1, edNom2, edNom3, edNom4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TVtimer = findViewById(R.id.tv_timer);
        edNom1 = findViewById(R.id.editTextNumber2);
        edNom2 = findViewById(R.id.editTextNumber3);
        edNom3 = findViewById(R.id.editTextNumber4);
        edNom4 = findViewById(R.id.editTextNumber);

        Timer();

        addAutoMoveToNextEditText(edNom1, edNom2, edNom1);
        addAutoMoveToNextEditText(edNom2, edNom3, edNom1);
        addAutoMoveToNextEditText(edNom3, edNom4, edNom2);
        addAutoMoveToNextEditText(edNom4, edNom4, edNom3);

        TVtimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TVtimer.getText() == "Можете запросить новый код")
                {
                    Timer();
                }
            }
        });

    }
    public void Timer()
    {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Обновление отображаемого времени
                //Отправить код повторно можно будет через 59 секунд
                TVtimer.setText("Отправить код повторно можно будет через " + millisUntilFinished / 1000 + " секунд");
            }

            public void onFinish() {
                // Действие по истечении времени
                TVtimer.setText("Можете запросить новый код");
                // Дополнительные действия, если необходимо
            }
        }.start();
    }
    private void addAutoMoveToNextEditText(final EditText current, final EditText next, final EditText Forerunner) {
        current.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1) {
                    next.requestFocus();
                    nober++;// Переход на следующий EditText
                    generatePassword(nober);
                }else{ Forerunner.requestFocus(); nober--;}
                generatePassword(nober);
            }
        });
    }
    public void generatePassword(int nober)
    {
        String PassNow = "2341";
        if(nober == 4)
        {
            String pass = edNom1.getText().toString() + edNom2.getText().toString() +
                    edNom3.getText().toString() + edNom4.getText().toString();
            if(PassNow.equals(pass))
            {
                Intent intent = new Intent(MainActivity2.this, EndActivity.class);
                startActivity(intent);
            }else{Toast.makeText(this, "Ты ошибся проваливай" + nober, Toast.LENGTH_SHORT).show();}
        }

    }

}