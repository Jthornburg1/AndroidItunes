package com.example.jon_thornburg.secondituneshitter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jon_thornburg.secondituneshitter.R;
import com.example.jon_thornburg.secondituneshitter.activities.fragments.SongsListFragment;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();

    SongsListFragment mSongsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSongsListFragment = (SongsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_songs);

    }
}
