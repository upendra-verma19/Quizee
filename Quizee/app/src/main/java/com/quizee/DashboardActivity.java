package com.quizee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageView infoButton = findViewById(R.id.infobutton);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMyDialog();

            }
        });

////
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("total");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                String total = snapshot.getValue(String.class);
//                Integer total = snapshot.getValue(Integer.class);
//                TextView tv = findViewById(R.id.textView2);
//                tv.setText(total.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        try {
            readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() throws IOException {

        File f = new File(getApplicationContext().getFilesDir(), "name.txt");
        FileInputStream fin;
        fin = openFileInput("name.txt");
        byte[] bytes = new byte[8192];
        String str = null;
        while ((fin.read(bytes)) != -1) {
            str = new String(bytes, StandardCharsets.UTF_8);
        }
        TextView txt = findViewById(R.id.VIEW_NAME);
        txt.setText(str);

    }

    public void questions(View view) {
        if (internetIsConnected()) {
            Intent questions = new Intent(this, QuestionActivity.class);
            startActivity(questions);
        } else {
            Toast.makeText(this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
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

    private void openMyDialog() {
        InfoDialog dialog = new InfoDialog(this);
        dialog.show();
    }
}