package com.example.jon_thornburg.secondituneshitter.activities.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jon_thornburg on 1/5/17.
 */

public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.SongViewHolder> {

    private Context context;

    public SongsListAdapter(Context context) {

    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {

        public SongViewHolder(View itemView) {
            super(itemView);
        }
    }
}
