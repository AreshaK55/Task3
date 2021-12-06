package com.example.task3_520;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Activity3 extends AppCompatActivity {

    private TextView tvQuestion,tvScore,tvQuestionNo;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4;
    private Button btnNext;

    int totalQuestions;
    int qCounter=0;
    int score;

    ColorStateList dfRbColor;
    boolean answered;

    private QuestionModel currentQuestion;
    private List<QuestionModel> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        questionsList = new ArrayList<>();
        tvQuestion= findViewById(R.id.textQuestion);
        tvScore= findViewById(R.id.textScore);
        tvQuestionNo= findViewById(R.id.textQuestionNo);

        radioGroup= findViewById(R.id.radioGroup);
        rb1= findViewById(R.id.rb1);
        rb2= findViewById(R.id.rb2);
        rb3= findViewById(R.id.rb3);
        rb4= findViewById(R.id.rb4);
        btnNext= findViewById(R.id.btnNext);

        dfRbColor = rb1.getTextColors();

        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered == false){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()  || rb4.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(Activity3.this, "Please Select an option", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) +1;
        if(answerNo == currentQuestion.getCorrectAnsNo()){
            score++;
            tvScore.setText("Score: " + score);
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getCorrectAnsNo()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }
        if (qCounter < totalQuestions){
            btnNext.setText("Next");
        }else{
            btnNext.setText("Finish");
        }
    }

    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);
        rb4.setTextColor(dfRbColor);

        if(qCounter < totalQuestions){
            currentQuestion = questionsList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            qCounter++;

            btnNext.setText("Submit");
            tvQuestionNo.setText("Question: " + qCounter + "/" + totalQuestions);
            answered=false;
        }else{
            finish();
            String s = Integer.toString(score);
            String value= s;
            Intent i = new Intent(getApplicationContext(), Activity5.class);
            i.putExtra("key",value);
            startActivity(i);
        }
    }

    private void addQuestions() {
        questionsList.add(new QuestionModel(" The Arabic Letters 'أہ' Sounds Produced from?","End of Throat","Middle of Throat","Start of the Throat","None",1));
        questionsList.add(new QuestionModel(" The Arabic Letters 'غ خ' Sounds Produced from?","Middle of Throat","Start of the Throat","End of Throat","None",2));
        questionsList.add(new QuestionModel(" The Arabic Letters 'ق' Sounds Produced from?","Middle of Throat","End of Throat","Base of Tongue","None",3));
        questionsList.add(new QuestionModel(" The Arabic Letters 'باَ' Sounds Produced from?","End of Throat","Middle of Throat","Base of Tongue","Mouth empty space",4));
    }
}
