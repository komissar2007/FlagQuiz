package com.games.slavar.flagquiz;

import android.content.res.AssetManager;
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
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlagsMainBuilder flagMainBuilder = new FlagsMainBuilder();
        ArrayList<Flag> flagsArray = flagMainBuilder.buildFlagArray(getApplicationContext());
        StageBuilder stageArrayList = new StageBuilder(flagsArray);
        stageArrayList.populateQuestions();

        flagImage = (ImageButton) findViewById(R.id.flagImageButton);
    }
}
