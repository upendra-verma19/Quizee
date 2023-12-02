package com.quizee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class WelcomeInput extends AppCompatActivity {
    String Name = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_input);

    }

    public void setName(View view) {
        EditText inputName = findViewById(R.id.NAME_INPUT);
        Name = inputName.getText().toString();


//        if (Name.equals("null")) {
//            Toast.makeText(this, "Please Enter Your Name First", Toast.LENGTH_SHORT).show();
//        }
//        if (!Name.equals("null")) {
//            Intent intent = new Intent(this, DashboardActivity.class);
//            startActivity(intent);
//        }

        if (TextUtils.isEmpty(inputName.getText())) {
            Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vib.vibrate(100);
            inputName.setError("Name is required!");
        } else {
            writeToFile(Name);
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }


    }

    private void writeToFile(String Name) {
        FileOutputStream fos;
        try {
            fos = openFileOutput("name.txt", MODE_PRIVATE);
            //default mode is PRIVATE, can be APPEND etc.
            fos.write(Name.getBytes());
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}