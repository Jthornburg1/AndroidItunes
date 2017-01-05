package com.example.jon_thornburg.secondituneshitter.activities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jon_thornburg.secondituneshitter.R;
import com.example.jon_thornburg.secondituneshitter.activities.adapters.SongsListAdapter;

/**
 * Created by jon_thornburg on 1/5/17.
 */

public class SongsListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.songs_list_fragment, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_songs);
        final Activity activity = getActivity();
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        recyclerView.setAdapter(new SongsListAdapter(activity));
        return view;
    }
}
