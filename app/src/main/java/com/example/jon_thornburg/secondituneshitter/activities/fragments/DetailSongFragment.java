package com.example.jon_thornburg.secondituneshitter.activities.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jon_thornburg.secondituneshitter.activities.models.SongItem;

/**
 * Created by jon_thornburg on 1/9/17.
 */

public class DetailSongFragment extends Fragment {

    public final static String TAG = DetailSongFragment.class.getSimpleName();
    private final static String EXTRA_ITUNES_ITEM = "extra_itunes_item";

    private View mView;
    private Activity mActivity;
    private ImageView mImageView;
    private TextView titleLabel;
    private TextView albumLabel;
    private TextView songLabel;
    private TextView dateLabel;
    private Button urlButton;
    private SongItem item;

    public static Intent createDefaultIntent(Context context, SongItem item, String term) {
        Intent intent = new Intent(context, DetailSongFragment.class);
        Log.d(TAG, "You've clicked on " + item.getTrackName());
        intent.putExtra(DetailSongFragment.EXTRA_ITUNES_ITEM, item);
        return intent;
    }
}
