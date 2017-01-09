package com.example.jon_thornburg.secondituneshitter.activities.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jon_thornburg.secondituneshitter.R;
import com.example.jon_thornburg.secondituneshitter.activities.models.SongItem;
import com.example.jon_thornburg.secondituneshitter.activities.utils.PicassoImageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon_thornburg on 1/6/17.
 */

public class SongListLoaderAdapter extends RecyclerView.Adapter<SongListLoaderAdapter.SongViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(SongItem item);
    }

    private Context context;
    private List<SongItem> items = new ArrayList<>();
    private int rowLayout;
    public static final String TAG = SongsListAdapter.class.getSimpleName();
    private final OnItemClickListener listener;

    public SongListLoaderAdapter(Context context, int rowLayout, OnItemClickListener listener) {
        this.context = context;
        this.rowLayout = rowLayout;
        this.listener = listener;
    }

    @Override
    public SongListLoaderAdapter.SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.songTitle.setText(items.get(position).getTrackName());
        holder.albumName.setText(items.get(position).getCollectionName());
        holder.artistName.setText(items.get(position).getArtistName());
        holder.bind(items.get(position), listener);
        /*ImageAsyncTask task = new ImageAsyncTask(holder.songImage, context);
        task.execute(items.get(position).getArtworkUrl60());*/
        PicassoImageManager imageManager = new PicassoImageManager();
        imageManager.loadImagePicasso(holder.songImage, items.get(position).getArtworkUrl60(),context);
    }

    public void swapItems(List<SongItem> newItems) {
        items.clear();
        items.addAll(newItems);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {

        TextView songTitle;
        TextView artistName;
        TextView albumName;
        ImageView songImage;

        public SongViewHolder(View itemView) {
            super(itemView);

            songTitle = (TextView) itemView.findViewById(R.id.title_text);
            artistName = (TextView) itemView.findViewById(R.id.artist_text);
            albumName = (TextView) itemView.findViewById(R.id.album_text);
            songImage = (ImageView) itemView.findViewById(R.id.song_image);
        }

        public void bind(final SongItem item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
