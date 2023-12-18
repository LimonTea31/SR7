package com.example.sr7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.HashMap;
import java.util.Map;

public class StartActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {


        private Animation left_in,left_out,right_in,right_out;
        private ViewFlipper viewFlipper;
        GestureDetector detector; // мониторинг жестов
    TextView oglav, soderz, next;

    Map<String, String> textMap1;
    Map<String, String> textMap2;
    Map<String, String> textMap3;
    Map<Integer, Map<String, String>> imageTextMap;
    Handler handler;
    RadioButton redRadioButton, greenRadioButton, blueRadioButton;
    int delay;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
            // Анимационный эффект

            oglav = findViewById(R.id.TV_oglav);
            soderz = findViewById(R.id.TV_Soderz);
            next = findViewById(R.id.TV_next);
            left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
            left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);
            right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
            right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);

            redRadioButton = findViewById(R.id.radio_red);


            greenRadioButton = (RadioButton)findViewById(R.id.radio_green);


            blueRadioButton = findViewById(R.id.radio_blue);


            viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
            detector = new GestureDetector(this);
            start();

            viewFlipper.addView(getImageView(R.drawable.kolbi));
            viewFlipper.addView(getImageView(R.drawable.doctorpatient));
            viewFlipper.addView(getImageView(R.drawable.doctor));

            handler = new Handler();
            delay = 4000;



            startAutoFlipping();

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });



        }
    private void startAutoFlipping() {

        handler.postDelayed(new Runnable() {
            public void run() {
                updateTextViewForImage();
                handler.postDelayed(this, delay);


            }
        }, delay);
    }

        // Событие касания передается жесту
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // TODO Auto-generated method stub
            return this.detector.onTouchEvent(event);
        }

        public ImageView getImageView(int id)
        {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(id);
            return imageView;
        }


        @Override
        public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
        float velocityY) {
            if(e2.getX()-e1.getX()<-120)
            {
                viewFlipper.setInAnimation(left_in);
                viewFlipper.setOutAnimation(left_out);
                viewFlipper.isAutoStart();
                viewFlipper.showNext();
                delay = 4000;
                updateTextViewForImage();
                return true;
            }
            else if(e2.getX()-e1.getX()>120){
                viewFlipper.setInAnimation(right_in);
                viewFlipper.setOutAnimation(right_out);
                viewFlipper.showPrevious();
                viewFlipper.setInAnimation(left_in);
                viewFlipper.setOutAnimation(left_out);
                viewFlipper.isAutoStart();
                delay = 4000;
                updateTextViewForImage();
                return true;
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
        float distanceY) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }
        public void start()
        {
            viewFlipper.setInAnimation(left_in);
            viewFlipper.setOutAnimation(left_out);
            viewFlipper.isAutoStart();
            updateTextViewForImage();
        }

    private void updateTextViewForImage() {
        int currentIndex = viewFlipper.getDisplayedChild();
        if(currentIndex <= 2) {
            switch (currentIndex) {

                case 0:
                    oglav.setText("Анализы");
                    soderz.setText("Экспресс сбор и получение проб");
                    redRadioButton.setChecked(true);
                    // , soderz
                    break;
                case 1: // Индекс изображения 2
                    oglav.setText("Уведомления");
                    soderz.setText("Вы быстро узнаете о результатах");
                    blueRadioButton.setChecked(true);
                    break;
                case 2: // Индекс изображения 3
                    oglav.setText("Мониторинг");
                    soderz.setText("Наши врачи всегда наблюдают за вашими показателями здоровья");
                    greenRadioButton.setChecked(true);
                    break;
                default:
                    break;
            }
        }
        if(currentIndex >= 2)
        { viewFlipper.stopFlipping(); next.setText("Завершить");}


    }




}