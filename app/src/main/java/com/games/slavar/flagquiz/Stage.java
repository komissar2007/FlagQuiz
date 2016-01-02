package com.games.slavar.flagquiz;

import java.util.ArrayList;

/**
 * Created by Slava on 02-1-16.
 */
public class Stage {
    final int numberOfAnswers = 4;
    private Flag question;
    private Flag[] answers = new Flag[4];

    public Flag getQuestion() {
        return question;
    }

    public void setQuestion(Flag question) {
        this.question = question;
    }

    public Flag[] getAnswers() {
        return answers;
    }

    public void setAnswers(Flag[] answers) {
        this.answers = answers;
    }
}
