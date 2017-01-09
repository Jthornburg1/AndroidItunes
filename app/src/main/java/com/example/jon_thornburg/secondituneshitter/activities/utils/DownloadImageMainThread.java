package com.example.jon_thornburg.secondituneshitter.activities.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jon_thornburg on 1/6/17.
 */

public class DownloadImageMainThread {

    public final static String TAG = DownloadImageMainThread.class.getSimpleName();

    public static Bitmap getBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(urlString);
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w(TAG, "Error downloading image from " + urlString);
        }
        return null;
    }
}
