package com.example.jon_thornburg.secondituneshitter.activities.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jon_thornburg on 1/9/17.
 */

public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {


    private ImageCache mLruCache;
    private final WeakReference<ImageView> imageViewReference;
    public final static String TAG = ImageAsyncTask.class.getSimpleName();
    private Context context;
    int memoryClassBytes;

    public ImageAsyncTask(ImageView imageView, Context context) {
        imageViewReference = new WeakReference<ImageView>(imageView);
        this.context = context;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        memoryClassBytes = am.getMemoryClass() * 1024 * 1024;
        mLruCache = new ImageCache(memoryClassBytes);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            return downloadBitmap(params[0]);
        } catch (Exception e) {
            Log.e(TAG, "Download Error: ", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;

        }
        ImageView imageView = imageViewReference.get();
        if (imageView != null) {
            Log.d(TAG, "We have a bitmap to set the image with");
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        } else {
            Log.d(TAG, "Sorry, no bitmap.");
        }
    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        Bitmap cachedResult = mLruCache.get(url);
        if (cachedResult != null) {
            return cachedResult;
        }
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            Log.d(TAG, "Imagedownload statuscode = " + statusCode);
            InputStream inputStream = urlConnection.getInputStream();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                Log.w(TAG, "Download error occurred." + url);
            }
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mLruCache.put(url, bitmap);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w(TAG, "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }


}
