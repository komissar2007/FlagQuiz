package com.games.slavar.flagquiz;

import android.content.Context;
import android.content.res.AssetManager;
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

    public ArrayList<Flag> buildFlagArray(Context applicationContext, String region) {
        AssetManager assetManager = null;
        ArrayList<Flag> flagArrayList = new ArrayList<Flag>();

        try {
            assetManager = applicationContext.getAssets();
            String flags[] = assetManager.list("flags/" + region);


            for (String flagName : flags) {
                counterId++;
                flag = new Flag();
                flag.setFileName(flagName);
                flagName = flagName.replace("_", " ");
                flagName = flagName.replace(region + "-", "");
                flagName = flagName.replace(".png", "");

                flag.setName(flagName);
                flag.setId(counterId);
                Log.d(getClass().toString(), "id:" + flag.getId() + ", " + "fileName:" + flag.getFileName() + ", " + "Name:" + flag.getName());
                flagArrayList.add(flag);
            }

            shuffleArray(flagArrayList);
            

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return flagArrayList;
        }

    }

    private void shuffleArray(ArrayList<Flag> flagArrayList) {

        Random rnd = new Random();
        for (int i = flagArrayList.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Flag a = flagArrayList.get(index);
            flagArrayList.set(index, flagArrayList.get(i));
            flagArrayList.set(i,a);
        }
        for (Flag flagId:flagArrayList)
        {
            System.out.println(flagId.getId());

            Log.d(getClass().toString(), String.valueOf(flagId.getId()));
        }
    }


}
