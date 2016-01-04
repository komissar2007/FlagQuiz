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

public class MainActivity extends AppCompatActivity {

    private ImageButton flagImage;
    private Button[] answerButton = new Button[4];
    private ArrayList<Stage> stageArrayList = new ArrayList<Stage>() ;
    private int stage = 0;
    private ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.answerProgressBar);
        FlagsMainBuilder flagMainBuilder = new FlagsMainBuilder();
        ArrayList<Flag> flagsArray = flagMainBuilder.buildFlagArray(getApplicationContext());
        StageBuilder stageBuilder = new StageBuilder(flagsArray);
        stageBuilder.populateQuestions();
        stageArrayList = stageBuilder.getStageArrayList();
        flagImage = (ImageButton) findViewById(R.id.flagImageButton);
        assignAnswers();
        try {
            prepareStage();
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

        if (flagImage.getContentDescription().equals(s)) {
            Toast.makeText(this, "Correct answer!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }
        stage++;
        prepareStage();

    }

    public void prepareStage() throws IOException {
        flagImage.setImageBitmap(BitmapFactory.decodeStream(getResources().getAssets().open("flags/" + stageArrayList.get(stage).getQuestion().getFileName())));
        flagImage.setContentDescription(stageArrayList.get(stage).getQuestion().getName());
        Flag answersArray[] = stageArrayList.get(stage).getAnswers();
        for (int i=0;i<answerButton.length;i++)
        {
            answerButton[i].setText(answersArray[i].getName());
        }

        handleProgressBar();
    }

    public void handleProgressBar()
    {


        progressBar.setProgress(100);

        CountDownTimer mCountDownTimer = new CountDownTimer(10000,500) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) (millisUntilFinished/100);
                progressBar.setProgress(progress);

            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "finished",Toast.LENGTH_SHORT).show();
                progressBar.setProgress(0);

            }
        };

        mCountDownTimer.start();
    }

}
