
package com.pshetye.justnotes;

import com.pshetye.justnotes.adapters.RecycleAdapter;
import com.pshetye.justnotes.adapters.RecyclerItemClickListener;
import com.pshetye.justnotes.database.DatabaseHelper;
import com.pshetye.justnotes.database.MyNote;

import java.util.ArrayList;
import java.util.List;

import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.View;

public class SearchResultsActivity extends BaseActivity {

    private static final String LOG_TAG = "NoteActivity";

    private List<MyNote> sMyNotes = new ArrayList<MyNote>();

    private RecyclerView mRecyclerView;

    @SuppressWarnings("rawtypes")
    private RecyclerView.Adapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            setTitle(getResources().getString(R.string.search_results) + " for '" + query + "'");
            // use the query to search your data somehow
            sMyNotes = DatabaseHelper.getInstance(SearchResultsActivity.this).searchNotes(query);
            showResults();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_note;
    }

    public void showResults() {
        int orientation = getScreenOrientation();
        mRecyclerView = (RecyclerView) findViewById(R.id.ListView);
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                || orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL));
        } else {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                    StaggeredGridLayoutManager.VERTICAL));
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RecycleAdapter(sMyNotes, this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // TODO Auto-generated method stub
                super.onScrollStateChanged(recyclerView, newState);
                recyclerView.animate();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // TODO Auto-generated method stub
                super.onScrolled(recyclerView, dx, dy);
                recyclerView.animate();
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                SearchResultsActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d(LOG_TAG, "Inside onClick");
                        MyNote note = sMyNotes.get(position);
                        ViewNoteActivity.launchViewNote(SearchResultsActivity.this,
                                view.findViewById(R.id.temp_view), note);
                    }
                }));
    }

    private int getScreenOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int orientation;
        // if the device's natural orientation is portrait:
        if ((rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) && height > width
                || (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270)
                && width > height) {
            switch (rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                default:
                    Log.e(LOG_TAG, "Unknown screen orientation. Defaulting to " + "portrait.");
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }
        }
        // if the device's natural orientation is landscape or if the device
        // is square:
        else {
            switch (rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_180:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                default:
                    Log.e(LOG_TAG, "Unknown screen orientation. Defaulting to " + "landscape.");
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }

        return orientation;
    }

}
