package com.games.slavar.flagquiz;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.games.slavar.flagquiz.menu.MenuFragment;

import java.io.IOException;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class SceneFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);
        flagImage = (ImageButton) view.findViewById(R.id.flagImageButton);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        stageBuilder = StageBuilder.getInstance();
        stageBuilder.populateFlag(getActivity());
        stageBuilder.populateQuestions();
        assignAnswers(view);
        try {
            stageBuilder.prepareStage(stage, flagImage, answerButton, this);
            setTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }

/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flagImage = (ImageButton) findViewById(R.id.flagImageButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        stageBuilder = StageBuilder.getInstance();
        stageBuilder.populateFlag(getActivity());
        stageBuilder.populateQuestions();
        assignAnswers();
        try {
            stageBuilder.prepareStage(stage, flagImage, answerButton, this);
            setTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private void assignAnswers(View view) {
        for (int i = 0; i < answerButton.length; i++) {
            answerButton[i] = (Button) view.findViewById(getResources().getIdentifier("resultButton" + i, "id", getActivity().getPackageName()));
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
            scoreBoard.updateScore(timeLeft,(TextView)getView().findViewById(R.id.scoreTextView));
        } else {
            /*TODO Wrong answer*/
        }
        Log.d("Slava","stage: " + stage);
        if (stage==10)
        {
            /*TODO game finished */
            new AlertDialog.Builder(getActivity())
                    .setTitle("Your Score is: ")
                    .setMessage(String.valueOf(scoreBoard.getScore()))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            getFragmentManager().beginTransaction().replace(R.id.fragmentConntainer, new MenuFragment()).commit();
                        }
                    })
                    .show();
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
    public void onPause() {
        super.onPause();
        Log.d("Slava:","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Slava:", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Slava:", "onDestroy");
        countDownTimer.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Slava:", "onResume");
    }

}
