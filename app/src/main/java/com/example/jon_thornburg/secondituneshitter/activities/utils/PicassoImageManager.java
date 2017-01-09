package com.example.jon_thornburg.secondituneshitter.activities.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by jon_thornburg on 1/9/17.
 */

public class PicassoImageManager {

    public final static String TAG = PicassoImageManager.class.getSimpleName();

    public void loadImagePicasso(ImageView imageView, String urlString, Context context) {

        Picasso.with(context)
                .load(urlString)
                .into(imageView);
    }
}
