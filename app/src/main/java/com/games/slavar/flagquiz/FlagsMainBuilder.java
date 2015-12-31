package com.games.slavar.flagquiz;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Slavar on 12/31/2015.
 */
public class FlagsMainBuilder {
    public FlagsMainBuilder(Context applicationContext) {

        AssetManager assetManager = null;

        try {
            assetManager = applicationContext.getAssets();
            String flags[] = assetManager.list("flags");


        for (String flag: flags)
        {
            Log.d("Slava",flag);
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            assetManager.close();
        }
    }
}
