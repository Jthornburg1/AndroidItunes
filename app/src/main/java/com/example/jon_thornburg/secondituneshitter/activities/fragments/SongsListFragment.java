package com.example.jon_thornburg.secondituneshitter.activities.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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

/**
 * Created by jon_thornburg on 1/5/17.
 */

public class SongsListFragment extends Fragment {

    public static final String TAG = SongsListFragment.class.getSimpleName();
    public static final String OBJID = "object_id";

    private EditText mEditText;
    private View mView;
    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private String term;

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
                    calliTunes();
                }
                return false;
            }
        });
        return mView;
    }

    public static SongsListFragment newInstance(int numToPass/*Object to load into bundle*/) {

        Log.d(TAG, "Creating new songslistfragment");
        SongsListFragment newFrag = new SongsListFragment();
        Bundle args = new Bundle();
        args.putInt(OBJID, numToPass/*or other object type*/);
        newFrag.setArguments(args);

        return newFrag;
    }

    private void calliTunes() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview_songs);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        //mRecyclerView.setAdapter(new SongsListAdapter(mActivity));
    }
}
