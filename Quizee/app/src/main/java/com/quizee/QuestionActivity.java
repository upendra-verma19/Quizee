package com.quizee;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Arrays;
import java.util.Collections;

public class QuestionActivity extends AppCompatActivity {

    int timer = 21, index = 0, responded = 1, max;
    //Responded is for flag
    CountDownTimer ct;
    QuestionLayout questionLayout = new QuestionLayout();
    TextView question, optionA, optionB, optionC, optionD, questionNumber;
    CardView cardA, cardB, cardC, cardD;
    ImageView nextBtn;
    //RecordData dataRecord = new RecordData();
    String answer;
    DatabaseReference reference;
    //    Integer total;
//    Integer[] collection = new Integer[17];
    Integer[] collection = new Integer[17];
    int[] stats = {0, 0};
    /*
    stat[0] : correct
    stat[1] : unanswered
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getID();
        ProgressBar quizTimer = findViewById(R.id.quiztimer);

        genRandom();
        updateQuestions();
        Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        ct = new CountDownTimer(21000, 1000) {
            @Override
            public void onTick(long l) {
                timer -= 1;
                quizTimer.setProgress(timer);
            }

            @Override
            public void onFinish() {
                updateQuestions();

            }
        };

        cardA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responded = 1;
                if (optionA.getText().equals(answer)) {
                    cardA.setCardBackgroundColor(Color.parseColor("#0dbb62"));
                    vib.vibrate(30);
                    increaseCorrect();
                } else {
                    cardA.setCardBackgroundColor(Color.parseColor("#ff1c47"));
                    vib.vibrate(150);
                    showCorrect();
                }
                disableButton();
            }
        });
        cardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responded = 1;
                if (optionB.getText().equals(answer)) {
                    cardB.setCardBackgroundColor(Color.parseColor("#0dbb62"));
                    vib.vibrate(30);
                    increaseCorrect();
                } else {
                    cardB.setCardBackgroundColor(Color.parseColor("#ff1c47"));
                    vib.vibrate(150);
                    showCorrect();
                }
                disableButton();
            }
        });
        cardC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responded = 1;
                if (optionC.getText().equals(answer)) {
                    cardC.setCardBackgroundColor(Color.parseColor("#0dbb62"));
                    vib.vibrate(30);
                    increaseCorrect();
                } else {
                    cardC.setCardBackgroundColor(Color.parseColor("#ff1c47"));
                    vib.vibrate(150);
                    showCorrect();
                }
                disableButton();
            }
        });
        cardD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responded = 1;
                if (optionD.getText().equals(answer)) {
                    cardD.setCardBackgroundColor(Color.parseColor("#0dbb62"));
                    vib.vibrate(30);
                    increaseCorrect();
                } else {
                    cardD.setCardBackgroundColor(Color.parseColor("#ff1c47"));
                    vib.vibrate(150);
                    showCorrect();
                }
                disableButton();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuestions();
                enableButton();
                resetLayout();
            }
        });


    }

    public void updateQuestions() {
        if (responded == 0)
            ++stats[1];

        if (index > 10) {
            showResult();
            return;
        }


        responded = 0;
        reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(collection[index]));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                QuestionLayout questionlayout = snapshot.getValue(QuestionLayout.class);
                String questionLabel = "Question " + "".format("%02d", index) + "/10";
                questionNumber.setText(questionLabel);
                assert questionlayout != null;
                question.setText(questionlayout.getQuestion());
                optionA.setText(questionlayout.getOptionA());
                optionB.setText(questionlayout.getOptionB());
                optionC.setText(questionlayout.getOptionC());
                optionD.setText(questionlayout.getOptionD());
                answer = questionlayout.getAnswer();
                loading();
                ct.cancel();
                timer = 21;
                ct.start();
                index++;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void genRandom() {

//        Integer[] data;
//
//        DatabaseReference fetchTotal = FirebaseDatabase.getInstance().getReference().child("total");
//        fetchTotal.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                Integer total = snapshot.getValue(Integer.class);
////                max = total;
////                String[] temp = new String[max];
////                for (int i = 0; i < temp.length; i++) {
////                    temp[i] = String.valueOf((i + 1));
////                }
//                Integer total = snapshot.getValue(Integer.class);
//                Integer[] temp = new Integer[17];
//                for (int i = 0; i < temp.length; i++) {
//                    temp[i] = (i + 1);
//                    Collections.shuffle(Arrays.asList(temp));
//                }
//                collection = temp;
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(QuestionActivity.this, "data failed", Toast.LENGTH_SHORT).show();
//            }
//        });


        //Working Code
        for (int i = 0; i < collection.length; i++) {
            collection[i] = (i + 1);
        }
        Collections.shuffle(Arrays.asList(collection));

//        for (int i = 0; i < collection.length; i++) {
//            collectionIndex[i] = collection[i].toString();
//        }
    }

    public void assignValues(Integer[] indices) {
        collection = indices;
        Toast.makeText(this, "" + collection.length, Toast.LENGTH_SHORT).show();
    }

    public void showResult() {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("Stats", stats);
        startActivity(intent);
        ct.cancel();
        finish();
    }

    private void disableButton() {
        cardA.setClickable(false);
        cardB.setClickable(false);
        cardC.setClickable(false);
        cardD.setClickable(false);
        ct.cancel();
    }

    private void enableButton() {
        cardA.setClickable(true);
        cardB.setClickable(true);
        cardC.setClickable(true);
        cardD.setClickable(true);
    }

    private void resetLayout() {
        cardA.setCardBackgroundColor(Color.parseColor("#35326e"));
        cardB.setCardBackgroundColor(Color.parseColor("#35326e"));
        cardC.setCardBackgroundColor(Color.parseColor("#35326e"));
        cardD.setCardBackgroundColor(Color.parseColor("#35326e"));
        question.setText("");
        optionA.setText("");
        optionB.setText("");
        optionC.setText("");
        optionD.setText("");
//        ProgressBar questionLoadingProgressbar = findViewById(R.id.questionloadingprogressbar);
//        questionLoadingProgressbar.setVisibility(View.VISIBLE);
        LinearLayout questionLoadingProgressbarLayout = findViewById(R.id.questionloadingprogressbarlayout);
        questionLoadingProgressbarLayout.setVisibility(View.VISIBLE);
    }

    private void getID() {
        question = findViewById(R.id.question);
        optionA = findViewById(R.id.optiona);
        optionB = findViewById(R.id.optionb);
        optionC = findViewById(R.id.optionc);
        optionD = findViewById(R.id.optiond);

        cardA = findViewById(R.id.cardoptiona);
        cardB = findViewById(R.id.cardoptionb);
        cardC = findViewById(R.id.cardoptionc);
        cardD = findViewById(R.id.cardoptiond);

        questionNumber = findViewById(R.id.questionnumber);

        nextBtn = findViewById(R.id.nextbtn);

    }

    private void increaseCorrect() {
        ++stats[0];
    }

    private void showCorrect() {
        if (optionA.getText().equals(answer))
            cardA.setCardBackgroundColor(Color.parseColor("#0dbb62"));
        else if (optionB.getText().equals(answer))
            cardB.setCardBackgroundColor(Color.parseColor("#0dbb62"));
        else if (optionC.getText().equals(answer))
            cardC.setCardBackgroundColor(Color.parseColor("#0dbb62"));
        else if (optionD.getText().equals(answer))
            cardD.setCardBackgroundColor(Color.parseColor("#0dbb62"));

    }

    private void loading() {
//        ProgressBar questionLoadingProgressbar = findViewById(R.id.questionloadingprogressbar);
//        questionLoadingProgressbar.setVisibility(View.GONE);
        LinearLayout questionLoadingProgressbarLayout = findViewById(R.id.questionloadingprogressbarlayout);
        questionLoadingProgressbarLayout.setVisibility(View.GONE);

    }

    private void setAllData() {
        question.setText(questionLayout.getQuestion());
        optionA.setText(questionLayout.getOptionA());
        optionB.setText(questionLayout.getOptionB());
        optionC.setText(questionLayout.getOptionC());
        optionD.setText(questionLayout.getOptionD());
    }

}