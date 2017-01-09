package com.example.jon_thornburg.secondituneshitter.activities.utils;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by jon_thornburg on 1/6/17.
 */

public class PhotoAsyncTaskLoader extends AsyncTaskLoader<Bitmap> {

    public PhotoAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public Bitmap loadInBackground() {
        return null;
    }



}
