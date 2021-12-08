package com.example.task3_520;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {
    Button practice,quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        practice= findViewById(R.id.practice);
        quiz = findViewById(R.id.quiz);
        ActionBar actionBar = getSupportActionBar();
        //set actionbar title(Optional)
        actionBar.setTitle("Start page");
        //set back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });
    }

    private void openActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    private void openActivity4() {
        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);

    }
    //handle onBack pressed(go previous activity)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
//actionbar

}