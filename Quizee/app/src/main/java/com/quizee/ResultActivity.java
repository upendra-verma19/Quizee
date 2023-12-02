package com.quizee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView resultText = findViewById(R.id.resulttext);
        TextView textScore = findViewById(R.id.textscore);

//        CardView resultHome = findViewById(R.id.resulthome);
        Intent intent = getIntent();
        int[] stats = intent.getIntArrayExtra("Stats");
        String result = "Correct: " + stats[0] + "\nWrong: " + (10 - (stats[0] + stats[1])) + "\nSkipped: " + stats[1];
        CircularProgressBar resultProgress = findViewById(R.id.circularProgressBar);
        resultProgress.setProgressWithAnimation(stats[0], 1000L);
        textScore.setText(stats[0] + "/10");
        resultText.setText(result);

//        resultHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });


    }
}