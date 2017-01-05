package com.example.jon_thornburg.secondituneshitter.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jon_thornburg.secondituneshitter.R;
import com.example.jon_thornburg.secondituneshitter.activities.adapters.SongsListAdapter;

import java.lang.ref.WeakReference;

/**
 * Created by jon_thornburg on 1/5/17.
 */

public class SongsListFragment extends Fragment {

    public static final  String TAG = SongsListFragment.class.getSimpleName();
    public static final  String EXTRA_SONG_ID = "SONG_ID";
    private int mSongId;
    private String term;
    private EditText editText;
    private RecyclerView recyclerView;
    private final String TERM = "term";
    private WeakReference<SongListHost> mActivityHost;
    private Button mButton;


    public  static SongsListFragment newInstance() {
        return new SongsListFragment();
    }

    public static SongsListFragment newInstance(int songId) {
        SongsListFragment frag = new SongsListFragment();
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


        final View view = inflater.inflate(R.layout.songs_list_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_songs);
        mButton = (Button) view.findViewById(R.id.list_button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivityHost != null) {
                    mActivityHost.get().onSongItemTapped(10);
                }
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(new SongsListAdapter(getActivity()));
        if (mActivityHost != null) {
            mActivityHost.get().onSongsLoaded();
        }
        return view;
    }
}
