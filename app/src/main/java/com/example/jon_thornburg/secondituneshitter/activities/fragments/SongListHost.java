package com.example.jon_thornburg.secondituneshitter.activities.fragments;

/**
 * Created by jon_thornburg on 1/5/17.
 */

public interface SongListHost {
    void onSongItemTapped(int songId);
    void onSongsLoaded();
    void loadList();
}
