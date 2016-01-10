package com.games.slavar.flagquiz;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Slava on 02-1-16.
 */
public class StageBuilder {
    private ArrayList<Flag> flagArrayList;

    private static StageBuilder stageBuilder = null;

    protected StageBuilder() {
    }

    public static StageBuilder getInstance(){
        if (stageBuilder == null)
        {
            stageBuilder = new StageBuilder();
        }
        return StageBuilder.stageBuilder;
    }



    public ArrayList<Stage> getStageArrayList() {
        return stageArrayList;
    }

    public void setStageArrayList(ArrayList<Stage> stageArrayList) {
        this.stageArrayList = stageArrayList;
    }

    public ArrayList<Flag> getFlagArrayList() {
        return flagArrayList;
    }

    public void setFlagArrayList(ArrayList<Flag> flagArrayList) {
        this.flagArrayList = flagArrayList;
    }

    private ArrayList<Stage> stageArrayList = new ArrayList<>();

    public void populateQuestions() {
        Stage stage;
        stageArrayList.clear();
        for (Flag flag : flagArrayList) {
            stage = new Stage();
            stage.setQuestion(flag);
            populateAnswers(stage);
            stageArrayList.add(stage);
            Log.d(getClass().toString(), "Stage: " + "Question: " + stage.getQuestion().getName());
            for (int i=0;i<stage.getAnswers().length;i++)
            {
                Log.d(getClass().toString(), "Answer " + i + ": " + stage.getAnswers()[i].getName());
            }

        }

    }

    public void populateAnswers(Stage stage)
    {
        HashSet<Flag> answersHashSet = new HashSet<>();
        answersHashSet.add(stage.getQuestion());
        Random rnd = new Random();
        int i;
        while(answersHashSet.size()<4)
        {
            i = rnd.nextInt(flagArrayList.size());
            answersHashSet.add(flagArrayList.get(i));
        }

        stage.setAnswers(answersHashSet.toArray(new Flag[answersHashSet.size()]));
    }

    public void prepareStage(int stage, ImageButton flagImage, Button[] answerButton, SceneFragment mainActivity) throws IOException {
        flagImage.setImageBitmap(BitmapFactory.decodeStream(mainActivity.getResources().getAssets().open("flags/" + mainActivity.getRegion()+ "/" +stageBuilder.getStageArrayList().get(stage).getQuestion().getFileName())));
        flagImage.setContentDescription(stageBuilder.getStageArrayList().get(stage).getQuestion().getName());
        Flag answersArray[] = stageBuilder.getStageArrayList().get(stage).getAnswers();
        for (int i=0;i<answerButton.length;i++)
        {
            answerButton[i].setText(answersArray[i].getName());
        }
    }

    public void populateFlag(Context applicationContext, String region) {
        FlagsMainBuilder flagMainBuilder = new FlagsMainBuilder();
        flagArrayList = flagMainBuilder.buildFlagArray(applicationContext, region);
    }
}
