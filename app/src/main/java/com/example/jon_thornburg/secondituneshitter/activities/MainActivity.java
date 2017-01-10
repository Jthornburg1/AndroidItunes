package com.example.jon_thornburg.secondituneshitter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.jon_thornburg.secondituneshitter.R;
import com.example.jon_thornburg.secondituneshitter.activities.fragments.DetailSongFragment;
import com.example.jon_thornburg.secondituneshitter.activities.fragments.SongsListFragment;
import com.example.jon_thornburg.secondituneshitter.activities.models.SongItem;

public class MainActivity extends AppCompatActivity implements INavigation {

    public static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Log.d(TAG, "onCreate: adding fragment");
            Fragment fragment = SongsListFragment.newInstance(12);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_content, fragment, SongsListFragment.TAG);
            ft.commit();
        }
    }

    private void replaceFragment(SongItem song) {
        Log.d(TAG, "replaceFragment: showing detail for " + song.getTrackName());
        Fragment fragment = DetailSongFragment.newInstance(song);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_content, fragment, DetailSongFragment.TAG);
        ft.addToBackStack("Push detail: " + song.getTrackName());
        ft.commit();
    }

    @Override
    public void onSongItemClick(SongItem song) {
        Log.d(TAG, "onSongItemClick" + song.getTrackName());
        Log.d(TAG, "replaceFragment: showing detail for " + song.getTrackName());
        Fragment fragment = DetailSongFragment.newInstance(song);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_content, fragment, DetailSongFragment.TAG);
        ft.addToBackStack("Push detail: " + song.getTrackName());
        ft.commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
        }
        return true;
    }
}
