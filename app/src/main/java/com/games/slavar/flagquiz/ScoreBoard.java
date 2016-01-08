package com.games.slavar.flagquiz;

import android.widget.TextView;

/**
 * Created by Slavar on 1/6/2016.
 */
public class ScoreBoard {

    private int score = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore(Long timeLeft, TextView scoreTextView)
    {
        score += (int)(timeLeft/100);
        scoreTextView.setText(String.valueOf(score));
    }
}
