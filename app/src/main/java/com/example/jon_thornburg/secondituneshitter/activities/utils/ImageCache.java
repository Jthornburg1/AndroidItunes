package com.example.jon_thornburg.secondituneshitter.activities.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by jon_thornburg on 1/9/17.
 */

public class ImageCache extends LruCache<String, Bitmap> {
    public ImageCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getByteCount();
    }
}
