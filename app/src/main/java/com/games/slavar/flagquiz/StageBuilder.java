package com.games.slavar.flagquiz;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Slava on 02-1-16.
 */
public class StageBuilder {

    private ArrayList<Flag> flagArrayList;

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

    private ArrayList<Stage> stageArrayList = new ArrayList<Stage>();


    public StageBuilder(ArrayList<Flag> flagArrayList) {
        this.flagArrayList = flagArrayList;
    }

    public void populateQuestions() {
        Stage stage;
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
        HashSet<Flag> answersHashSet = new HashSet<Flag>();
        answersHashSet.add(stage.getQuestion());
        Random rnd = new Random();
        int i=0;
        while(answersHashSet.size()<4)
        {
            i = rnd.nextInt(flagArrayList.size());
            answersHashSet.add(flagArrayList.get(i));
        }

        stage.setAnswers(answersHashSet.toArray(new Flag[answersHashSet.size()]));
    }
}
