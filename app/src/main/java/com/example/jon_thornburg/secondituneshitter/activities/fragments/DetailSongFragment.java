package com.example.jon_thornburg.secondituneshitter.activities.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jon_thornburg.secondituneshitter.R;
import com.example.jon_thornburg.secondituneshitter.activities.models.SongItem;
import com.example.jon_thornburg.secondituneshitter.activities.utils.DateConverter;
import com.example.jon_thornburg.secondituneshitter.activities.utils.PicassoImageManager;

/**
 * Created by jon_thornburg on 1/9/17.
 */

public class DetailSongFragment extends Fragment {

    public final static String TAG = DetailSongFragment.class.getSimpleName();
    private final static String EXTRA_ITUNES_ITEM = "extra_itunes_item";

    private View mView;
    private TextView mTitle;
    private TextView mAlbumName;
    private TextView mArtistName;
    private TextView mReleaseDate;
    private ImageView mImage;
    private Button urlButton;
    private SongItem mSong;

    public static DetailSongFragment newInstance(SongItem item) {
        Log.d(TAG, "Creating new DetailSongFragment.");
        DetailSongFragment fragment = new DetailSongFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ITUNES_ITEM, item);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.song_detail_layout, container, false);
        mTitle = (TextView) mView.findViewById(R.id.song_name);
        mAlbumName = (TextView) mView.findViewById(R.id.collection_name);
        mArtistName = (TextView) mView.findViewById(R.id.artist_name);
        mReleaseDate = (TextView) mView.findViewById(R.id.release_date);
        mImage = (ImageView) mView.findViewById(R.id.detail_image);
        urlButton = (Button) mView.findViewById(R.id.url_button);

        if (getArguments() != null) {
            mSong = (SongItem) getArguments().getSerializable(EXTRA_ITUNES_ITEM);
            mTitle.setText(mSong.getTrackName());
            mAlbumName.setText(mSong.getCollectionName());
            mArtistName.setText(mSong.getArtistName());
            String date = DateConverter.makeDate(mSong.getReleaseDate());
            mReleaseDate.setText(date);
            Log.d(TAG, "Here's the big picture url " + mSong.getArtworkUrl100());
            String url = mSong.getArtworkUrl100();
            PicassoImageManager manager = new PicassoImageManager();
            manager.loadImagePicasso(mImage, url, getContext());
            urlButton.setText("See " + mSong.getArtistName() + "'s web page");
            openURL();
        }



        return mView;
    }

    public void openURL() {
        urlButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = mSong.getArtistViewUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
