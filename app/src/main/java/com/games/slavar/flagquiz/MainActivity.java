package com.games.slavar.flagquiz;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton flagImage;
    private Button[] answerButton = new Button[4];
    private ArrayList<Stage> stageArrayList = new ArrayList<Stage>() ;
    int stage = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlagsMainBuilder flagMainBuilder = new FlagsMainBuilder();
        ArrayList<Flag> flagsArray = flagMainBuilder.buildFlagArray(getApplicationContext());
        StageBuilder stageBuilder = new StageBuilder(flagsArray);
        stageBuilder.populateQuestions();
        stageArrayList = stageBuilder.getStageArrayList();
        flagImage = (ImageButton) findViewById(R.id.flagImageButton);
        answerButton[0] = (Button) findViewById(R.id.resultButtonA);
        answerButton[1] = (Button) findViewById(R.id.resultButtonB);
        answerButton[2] = (Button) findViewById(R.id.resultButtonC);
        answerButton[3] = (Button) findViewById(R.id.resultButtonD);
        try {
            prepareStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prepareStage() throws IOException {
        flagImage.setImageBitmap(BitmapFactory.decodeStream(getResources().getAssets().open("flags/" + stageArrayList.get(stage).getQuestion().getFileName())));
        Flag answersArray[] = stageArrayList.get(stage).getAnswers();
        for (int i=0;i<answerButton.length;i++)
        {
            answerButton[i].setText(answersArray[i].getName());
        }
    }
}
