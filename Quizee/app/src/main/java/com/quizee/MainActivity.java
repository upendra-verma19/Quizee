package com.quizee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.cardview.widget.CardView splashLogo = findViewById(R.id.splashlogo);
        ImageView splashText = findViewById(R.id.splashtext);
        LinearLayout splashTextLayout = findViewById(R.id.splashtextlayout);
        LinearLayout splashLogoLayout = findViewById(R.id.splashlogolayout);

        Animation animationScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_animation);
        Animation animationSlideLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_animation_left);
        Animation animationSlideRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_animation_right);
        Animation animationFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);


        splashLogo.startAnimation(animationScale);
        splashText.startAnimation(animationSlideRight);
//        splashLogo.startAnimation(animationSlideLeft);

//        splashLogo.startAnimation(animationFadeOut);

        new CountDownTimer(2200, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
//                NumberFormat f = new DecimalFormat("00");
//                long hour = (millisUntilFinished / 3600000) % 24;
//                long min = (millisUntilFinished / 60000) % 60;
//                long sec = (millisUntilFinished / 1000) % 60;
//                tv.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
//                tv.setText("Time: " + f.format(sec));
            }

            @Override
            public void onFinish() {
                splashLogoLayout.setMinimumHeight(100);
                splashLogoLayout.setMinimumWidth(100);
                splashTextLayout.setVisibility(View.VISIBLE);
            }
        }.start();

//        splashText.startAnimation(animationSlideRight);
//        tv.setText("Welcome");
//        check();

//        while (true) {
//            if (internetIsConnected()) {
//                check();
//                break;
//            }
//        }

//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        while (true) {
//            if (internetIsConnected()) {
//                check();
//                break;
//            }
//        }


        //Timer
        new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
//                tv.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
//                tv.setText("Time: " + f.format(sec));
            }

            @Override
            public void onFinish() {
                check();
            }
        }.start();
//
    }

    public void del() {
        while (true) {
            if (internetIsConnected()) {
                check();
                break;
            }
        }
    }

    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

    public void check() {
        File f = new File(getApplicationContext().getFilesDir(), "name.txt");
        if (f.exists()) {
//            Toast.makeText(this, "File Exist", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
//            Toast.makeText(this, "File Does Not Exist", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WelcomeInput.class);
            startActivity(intent);
            finish();
        }
    }


}