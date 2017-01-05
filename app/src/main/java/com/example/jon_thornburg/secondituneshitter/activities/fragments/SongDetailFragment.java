package com.example.jon_thornburg.secondituneshitter.activities.fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jon_thornburg.secondituneshitter.R;

import java.lang.ref.WeakReference;

public class SongDetailFragment extends Fragment {

    public static final  String TAG = SongDetailFragment.class.getSimpleName();
    public static final  String EXTRA_SONG_ID = "SONG_ID";
    private int mSongId;
    private final String TERM = "term";
    private WeakReference<SongListHost> mActivityHost;
    private Button mButton;
    private TextView mSongIdTextView;


    public static SongDetailFragment newInstance(int songId) {
        SongDetailFragment frag = new SongDetailFragment();
        Log.d(TAG, "newInstance " + songId);
        Bundle args = new Bundle();
        args.putInt(EXTRA_SONG_ID, songId);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(getContext());

        try {
            mActivityHost = new WeakReference<SongListHost>((SongListHost ) context);
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement SongListHost ");
        }

        Log.d(TAG, "The fragment has been attached.");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSongId = getArguments().getInt(EXTRA_SONG_ID);
        }

        Log.d(TAG, "songId " + mSongId);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");


        final View view = inflater.inflate(R.layout.song_detail_fragment, container, false);
        mSongIdTextView = (TextView) view.findViewById(R.id.song_id);
        mButton = (Button) view.findViewById(R.id.list_button);
        mSongIdTextView.setText(String.valueOf(mSongId));

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivityHost != null) {
                    mActivityHost.get().loadList();
                }
            }
        });

        return view;
    }
}