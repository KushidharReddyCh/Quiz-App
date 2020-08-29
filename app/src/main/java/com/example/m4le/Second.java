package com.example.m4le;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Second extends AppCompatActivity {
    Boolean check;
    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score =0;
    int numberOfQuestions = 0;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("!");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        check = true;
        new CountDownTimer(31000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) +"s");
            }

            @Override
            public void onFinish() {
                check = false;
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                float s2 = (float)score;
                float res = (s2/numberOfQuestions)*100;
                res = Math.round(res*100)/100;
                if( numberOfQuestions>0 && res >= 35) {
                    resultTextView.setText("PASS 😍");
                }
                else{
                    resultTextView.setText("FAIL 😟 ");
                }
                Toast.makeText(Second.this, "Your Percentage is "+res+"%", Toast.LENGTH_LONG).show();
            }
        }.start();

    }

    public void generateQuestion(){

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        int inCorrectAnswer;

        for(int i=0;i<4;i++){
            if(i== locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                inCorrectAnswer = rand.nextInt(41);
                while(inCorrectAnswer == (a+b)){
                    inCorrectAnswer = rand.nextInt(41);
                }
                answers.add(inCorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {
       if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
          score++;
          if(check == true) {
              resultTextView.setText("Correct");
          }
       }
       else{
           if(check == true) {
               resultTextView.setText("Incorrect");
           }
       }

       if(check == true) {
           numberOfQuestions++;
           pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
           answers.clear();
           generateQuestion();
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView  = (TextView)findViewById(R.id.timerTextView);
        sumTextView    = (TextView)findViewById(R.id.sumTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        playAgain(findViewById(R.id.playAgainButton));

    }



}