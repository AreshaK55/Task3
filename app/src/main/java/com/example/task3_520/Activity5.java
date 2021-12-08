package com.example.task3_520;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity5 extends AppCompatActivity {

    TextView tvScore;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        tvScore = findViewById(R.id.showScore);
        btn1 = findViewById(R.id.btn1);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("key");
//actionbar
        ActionBar actionBar = getSupportActionBar();
        //set actionbar title(Optional)
        actionBar.setTitle("Start page");
        //set back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        tvScore.setText(value);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Thank You For Existing...!!!");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });


    }
 //handle onBack pressed(go previous activity)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}