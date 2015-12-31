package com.games.slavar.flagquiz;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.AutoText;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Slavar on 12/31/2015.
 */
public class FlagsMainBuilder {
    private int counterId = 0;
    private Flag flag = null;

    public ArrayList<Flag> buildFlagArray(Context applicationContext) {
        AssetManager assetManager = null;
        ArrayList<Flag> flagArrayList = new ArrayList<Flag>();

        try {
            assetManager = applicationContext.getAssets();
            String flags[] = assetManager.list("flags");


            for (String flagName : flags) {
                counterId++;
                flag = new Flag();
                flag.setFileName(flagName);
                flagName = flagName.replace("Europe-", "");
                flagName = flagName.replace(".png", "");
                flagName = flagName.replace("_", " ");
                flag.setName(flagName);
                flag.setId(counterId);
                Log.d(getClass().toString(), "id:" + flag.getId() + ", " + "fileName:" + flag.getFileName() + ", " + "Name:" + flag.getName());
                flagArrayList.add(flag);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assetManager.close();
            return flagArrayList;
        }

    }
}
