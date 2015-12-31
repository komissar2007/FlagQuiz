package com.games.slavar.flagquiz;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlagsMainBuilder flagMainBuilder = new FlagsMainBuilder();
        ArrayList<Flag> flagsArray = flagMainBuilder.buildFlagArray(getApplicationContext());


    }
}
