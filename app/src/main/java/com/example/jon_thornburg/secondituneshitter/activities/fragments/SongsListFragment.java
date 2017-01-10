package com.example.jon_thornburg.secondituneshitter.activities.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jon_thornburg.secondituneshitter.R;
import com.example.jon_thornburg.secondituneshitter.activities.INavigation;
import com.example.jon_thornburg.secondituneshitter.activities.adapters.OnItemClickListener;
import com.example.jon_thornburg.secondituneshitter.activities.adapters.SongListLoaderAdapter;
import com.example.jon_thornburg.secondituneshitter.activities.models.ItunesResponse;
import com.example.jon_thornburg.secondituneshitter.activities.models.SongItem;
import com.example.jon_thornburg.secondituneshitter.activities.utils.ItunesHttpClient;
import com.example.jon_thornburg.secondituneshitter.activities.utils.ItunesInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by jon_thornburg on 1/5/17.
 */

public class SongsListFragment extends Fragment implements OnItemClickListener<SongItem>{

    public static final String TAG = SongsListFragment.class.getSimpleName();
    public static final String OBJID = "object_id";

    private EditText mEditText;
    private View mView;
    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private String term;
    private SongListLoaderAdapter mLoaderAdapter;
    private INavigation mNavigationActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.songs_list_fragment, container, false);
        mActivity = getActivity();

        mEditText = (EditText) mView.findViewById(R.id.textedit);

        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = mEditText.getText().toString();
                    term = text;
                    InputMethodManager imm = (InputMethodManager)mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
                    Log.d(TAG, "Hiding Keyboard");
                    reload(false);
                }
                return false;
            }
        });

        mLoaderAdapter = new SongListLoaderAdapter(getActivity(),R.layout.song_list_item, this);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview_songs);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mLoaderAdapter);
        return mView;
    }

    @Override
    public void onItemClick(SongItem item) {
            Log.d(TAG, "onItemClick" + item.getTrackName());
        mNavigationActivity.onSongItemClick(item);

    }

    public static SongsListFragment newInstance(int numToPass/*Object to load into bundle*/) {

        Log.d(TAG, "Creating new songslistfragment");
        SongsListFragment newFrag = new SongsListFragment();
        Bundle args = new Bundle();
        args.putInt(OBJID, numToPass/*or other object type*/);
        newFrag.setArguments(args);

        return newFrag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            if (context instanceof INavigation){
                this.mNavigationActivity = (INavigation) context;
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, " - onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        reload(true);

    }

    private void reload(boolean init) {
        if (init) {
            getLoaderManager().initLoader(ALL_SONGS_LOADER, null, mLoaderCallback);
        } else {
            getLoaderManager().restartLoader(ALL_SONGS_LOADER, null, mLoaderCallback);
        }

    }

    protected static final int ALL_SONGS_LOADER = 0;
    private LoaderManager.LoaderCallbacks<List<SongItem>> mLoaderCallback = new LoaderManager.LoaderCallbacks<List<SongItem>>() {

        @Override
        public Loader<List<SongItem>> onCreateLoader(int loaderId, Bundle args) {
            Log.d(TAG, "[onCreateLoader]");
            switch (loaderId) {
                case ALL_SONGS_LOADER:
                    SongListLoader loader = new SongListLoader(getActivity(), "the stooges");
                    Log.d(TAG, "[onCreateLoader] ");

                    if (mEditText.getText() != null) {
                        loader = new SongListLoader(getActivity(), mEditText.getText().toString());
                    }

                    return loader;
            }
            return null;

        }

        @Override
        public void onLoadFinished(Loader<List<SongItem>> loader, List<SongItem> result) {
            Log.d(TAG, "onLoadFinished " + result.size());
            List<SongItem> items = result;
            mLoaderAdapter.swapItems(items);
            mLoaderAdapter.notifyDataSetChanged();
            //onCursorLoaderFinished(result);
        }

        @Override
        public void onLoaderReset(Loader<List<SongItem>> loader) {
            mLoaderAdapter.swapItems(null);
        }

    };

    public static class SongListLoader extends AsyncTaskLoader<List<SongItem>> {

        List<SongItem> mItems;
        String term;
        public SongListLoader(Context context, String term) {
            super(context);
            this.term = term;
        }

        /**
         * This is where the bulk of our work is done.  This function is
         * called in a background thread and should generate a new set of
         * data to be published by the loader.
         */
        @Override public List<SongItem> loadInBackground() {


            final Context context = getContext();
            ItunesInterface mInterface =ItunesHttpClient.getClient().create(ItunesInterface.class);

            Call<ItunesResponse> call = mInterface.getItunesItems(term);
            List<SongItem> items = new ArrayList<>();
            try {
                items = call.execute().body().getResults();
            } catch (IOException exception) {
                Log.e(TAG, "Failed", exception);
            }


            // Done!
            return items;
        }

        /**
         * Called when there is new data to deliver to the client.  The
         * super class will take care of delivering it; the implementation
         * here just adds a little more logic.
         */
        @Override public void deliverResult(List<SongItem> apps) {
            if (isReset()) {
                // An async query came in while the loader is stopped.  We
                // don't need the result.
                if (apps != null) {
                    onReleaseResources(apps);
                }
            }
            List<SongItem> oldApps = mItems;
            mItems = apps;

            if (isStarted()) {
                // If the Loader is currently started, we can immediately
                // deliver its results.
                super.deliverResult(apps);
            }

            // At this point we can release the resources associated with
            // 'oldApps' if needed; now that the new result is delivered we
            // know that it is no longer in use.
            if (oldApps != null) {
                onReleaseResources(oldApps);
            }
        }

        /**
         * Handles a request to start the Loader.
         */
        @Override protected void onStartLoading() {
            if (mItems != null) {
                // If we currently have a result available, deliver it
                // immediately.
                deliverResult(mItems);
            }


            if (takeContentChanged() || mItems == null) {
                // If the data has changed since the last time it was loaded
                // or is not currently available, start a load.
                forceLoad();
            }
        }

        /**
         * Handles a request to stop the Loader.
         */
        @Override protected void onStopLoading() {
            // Attempt to cancel the current load task if possible.
            cancelLoad();
        }

        /**
         * Handles a request to cancel a load.
         */
        @Override public void onCanceled(List<SongItem> apps) {
            super.onCanceled(apps);

            // At this point we can release the resources associated with 'apps'
            // if needed.
            onReleaseResources(apps);
        }

        /**
         * Handles a request to completely reset the Loader.
         */
        @Override protected void onReset() {
            super.onReset();

            // Ensure the loader is stopped
            onStopLoading();

        }

        /**
         * Helper function to take care of releasing resources associated
         * with an actively loaded data set.
         */
        protected void onReleaseResources(List<SongItem> apps) {
            // For a simple List<> there is nothing to do.  For something
            // like a Cursor, we would close it here.
        }
    }
}
