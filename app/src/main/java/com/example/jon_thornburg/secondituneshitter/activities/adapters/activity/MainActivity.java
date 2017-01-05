package com.example.jon_thornburg.secondituneshitter.activities.adapters.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jon_thornburg.secondituneshitter.R;
import com.example.jon_thornburg.secondituneshitter.activities.fragments.SongDetailFragment;
import com.example.jon_thornburg.secondituneshitter.activities.fragments.SongListHost;
import com.example.jon_thornburg.secondituneshitter.activities.fragments.SongsListFragment;

public class MainActivity extends AppCompatActivity implements SongListHost {

    public static String TAG = MainActivity.class.getSimpleName();

    SongsListFragment mSongsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate: Adding fragment");
            Fragment fragment = SongsListFragment.newInstance(5);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_content, fragment, SongsListFragment.TAG);
            ft.commit();

        }

    }

    @Override
    public void onSongItemTapped(int songId) {
        Log.d(TAG, "onSongItemTapped" + songId);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SongDetailFragment.TAG);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (fragment == null) {
            fragment = SongDetailFragment.newInstance(songId);
            ft.replace(R.id.main_content, fragment, SongDetailFragment.TAG);
            ft.commit();
        } else {
            ft.replace(R.id.main_content, fragment, SongDetailFragment.TAG);
            ft.commit();
        }
    }

    @Override
    public void onSongsLoaded() {
        Log.d(TAG, "onSongsLoaded");
    }

    @Override
    public void loadList() {
        Log.d(TAG, "loadList");
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SongsListFragment.TAG);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (fragment == null) {
            fragment = SongsListFragment.newInstance();
            ft.replace(R.id.main_content, fragment, SongsListFragment.TAG);
            ft.commit();
        } else {
            ft.replace(R.id.main_content, fragment, SongsListFragment.TAG);
            ft.commit();
        }
    }
}
