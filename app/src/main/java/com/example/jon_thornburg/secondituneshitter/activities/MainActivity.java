package com.example.jon_thornburg.secondituneshitter.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jon_thornburg.secondituneshitter.R;
import com.example.jon_thornburg.secondituneshitter.activities.fragments.SongsListFragment;

public class MainActivity extends AppCompatActivity {

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
}
