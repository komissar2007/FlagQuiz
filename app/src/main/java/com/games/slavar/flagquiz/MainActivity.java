package com.games.slavar.flagquiz;

import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private ImageButton flagImage;
    private Button[] answerButton = new Button[4];
    private StageBuilder stageBuilder;
    private int stage = 0;
    private int numberOfStages = 10;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private ScoreBoard scoreBoard = new ScoreBoard();
    private long timeLeft = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flagImage = (ImageButton) findViewById(R.id.flagImageButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        stageBuilder = StageBuilder.getInstance();
        stageBuilder.populateFlag(getApplicationContext());
        stageBuilder.populateQuestions();
        assignAnswers();
        try {
            stageBuilder.prepareStage(stage, flagImage, answerButton, this);
            setTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void assignAnswers() {
        for (int i = 0; i < answerButton.length; i++) {
            answerButton[i] = (Button) findViewById(getResources().getIdentifier("resultButton" + i, "id", getPackageName()));
            answerButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    try {
                        checkResult(clickedButton.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private void checkResult(String s) throws IOException {
        countDownTimer.cancel();
        if (flagImage.getContentDescription().equals(s)) {
            Toast.makeText(this, "Correct answer!", LENGTH_SHORT).show();
            scoreBoard.updateScore(timeLeft);
        } else {
            Toast.makeText(this, "Wrong answer!", LENGTH_SHORT).show();
        }
        Log.d("Slava","stage: " + stage);
        if (stage==10)
        {
            Toast.makeText(this, "your score is: " + scoreBoard.getScore(), LENGTH_SHORT).show();
            finish();

        }
        stage++;
        setTimer();
        stageBuilder.prepareStage(stage, flagImage, answerButton, this);

    }



    private void setTimer()
    {
        progressBar.setProgress(100);
        countDownTimer = new CountDownTimer(10000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeLeft = millisUntilFinished;
                progressBar.setProgress((int)millisUntilFinished/100);

            }

            @Override
            public void onFinish() {
                try {
                    checkResult("");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Slava:","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Slava:", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Slava:", "onDestroy");
        countDownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Slava:", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Slava:", "onResart");
    }
}
