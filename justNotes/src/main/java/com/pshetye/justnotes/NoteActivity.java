
package com.pshetye.justnotes;

import com.nispok.snackbar.Snackbar;
import com.pshetye.justnotes.adapters.RecycleAdapter;
import com.pshetye.justnotes.adapters.RecyclerItemClickListener;
import com.pshetye.justnotes.database.DatabaseHelper;
import com.pshetye.justnotes.database.MyNote;
import com.pshetye.justnotes.fab.FloatingActionButton;
import com.pshetye.justnotes.util.NoteAnimator;
import com.pshetye.justnotes.util.StyleAttributes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

public class NoteActivity extends BaseActivity {

    private static final String LOG_TAG = "NoteActivity";
    public static final String SHARED_PREF_APP_DATA = "APP_DATA";

    private FloatingActionButton mFAddButton = null;

    private static List<MyNote> sMyNotes;

    static {
        sMyNotes = new ArrayList<MyNote>();
    }

    public static boolean sDoUpdate = false;

    private RecyclerView mRecyclerView;

    private SharedPreferences mPrefs;

    private static final int sLinear = 1;
    private static final int sStaggeredGrid = 2;

    private static int mCurrentLayout = 2;

    @SuppressWarnings("rawtypes")
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Inside onCreate");
        mRecyclerView = (RecyclerView) findViewById(R.id.ListView);
        mPrefs = getSharedPreferences(SHARED_PREF_APP_DATA, MODE_PRIVATE);
        mCurrentLayout = mPrefs.getInt(BaseActivity.SHARED_PREF_KEY_LAYOUT, sStaggeredGrid);
        Log.d(LOG_TAG, "mCurrentLayout = " + mCurrentLayout);
        displayNotes(mCurrentLayout);

        // Add Button - Holder Fragment
        mFAddButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(StyleAttributes.fabAddButton))
                .withButtonColor(getResources().getColor(StyleAttributes.fab_color))
                .withGravity(Gravity.BOTTOM | Gravity.END).withMargins(0, 0, 15, 15).create();
        NoteAnimator.animateFAB(getApplicationContext(), mFAddButton, NoteAnimator.IN,
                NoteAnimator.BOTTOM);
        mFAddButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                InputActivity.launchInput(NoteActivity.this, v, LOG_TAG);
            }
        });

        mRecyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // TODO Auto-generated method stub
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // special handler to avoid displaying half elements
                    // recyclerView.scrollTo(rec, y);
                    NoteAnimator.animateFAB(getApplicationContext(), mFAddButton, NoteAnimator.IN,
                            NoteAnimator.BOTTOM);
                } else {
                    NoteAnimator.animateFAB(getApplicationContext(), mFAddButton, NoteAnimator.OUT,
                            NoteAnimator.BOTTOM);
                }
                recyclerView.animate();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // TODO Auto-generated method stub
                super.onScrolled(recyclerView, dx, dy);
                recyclerView.animate();
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(NoteActivity.this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d(LOG_TAG, "Inside onClick");
                        if (mFAddButton.getVisibility() == View.VISIBLE) {
                            NoteAnimator.animateFAB(getApplicationContext(), mFAddButton,
                                    NoteAnimator.OUT, NoteAnimator.BOTTOM);
                        }
                        MyNote note = sMyNotes.get(position);
                        ViewNoteActivity.launchViewNote(NoteActivity.this,
                                view.findViewById(R.id.temp_view), note);
                    }
                }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notesearch, menu);
        // Associate searchable configuration with the SearchView
        MenuItem searchItem = menu.findItem(R.id.search);
        if (searchItem != null) {
            Log.d(LOG_TAG, "searchItem is NOT null");
        } else {
            Log.d(LOG_TAG, "searchItem is null");
        }
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            LinearLayout linearLayout1 = (LinearLayout) searchView.getChildAt(0);
            LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getChildAt(2);
            LinearLayout linearLayout3 = (LinearLayout) linearLayout2.getChildAt(1);
            AutoCompleteTextView autoComplete = (AutoCompleteTextView) linearLayout3.getChildAt(0);
            autoComplete.setOnFocusChangeListener(new OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    // TODO Auto-generated method stub
                    if (hasFocus) {
                        NoteAnimator.animateFAB(getApplicationContext(), mFAddButton,
                                NoteAnimator.OUT, NoteAnimator.BOTTOM);
                    } else {
                        NoteAnimator.animateFAB(getApplicationContext(), mFAddButton,
                                NoteAnimator.IN, NoteAnimator.BOTTOM);
                    }
                }
            });
        } else {
            Log.d(LOG_TAG, "searchView is null");
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mCurrentLayout == sStaggeredGrid) {
                    mCurrentLayout = sLinear;
                    displayNotes(mCurrentLayout);
                } else {
                    mCurrentLayout = sStaggeredGrid;
                    displayNotes(mCurrentLayout);
                }
                Log.d(LOG_TAG, "IN onOptionsItemSelected | mCurrentLayout = " + mCurrentLayout);
                Editor editor = mPrefs.edit();
                editor.putInt(BaseActivity.SHARED_PREF_KEY_LAYOUT, mCurrentLayout);
                editor.commit();
                return true;
            case R.id.theme:
                createDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        if (sDoUpdate) {
            Log.d(LOG_TAG, "sDoUpdate is TRUE");
            displayNotes(mPrefs.getInt(BaseActivity.SHARED_PREF_KEY_LAYOUT, sStaggeredGrid));
        } else {
            Log.d(LOG_TAG, "sDoUpdate is FALSE");
        }
        sDoUpdate = true;
        NoteAnimator.animateFAB(getApplicationContext(), mFAddButton, NoteAnimator.IN,
                NoteAnimator.BOTTOM);
    }

    @Override
    protected int getLayoutResource() {
        // TODO Auto-generated method stub
        return R.layout.activity_note;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == BaseActivity.VIEW_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Log.d(LOG_TAG, "Gonna Update the Notes List");
                sDoUpdate = true;
            } else {
                Log.d(LOG_TAG, "NOT Gonna Update the Notes List");
                sDoUpdate = false;
            }
        }
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

    private void displayNotes(int layout) {
        int orientation = getScreenOrientation();
        sMyNotes = DatabaseHelper.getInstance(NoteActivity.this).getAllNotes();
        mAdapter = new RecycleAdapter(sMyNotes, this);
        mRecyclerView.setAdapter(mAdapter);
        if (layout == sLinear) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(NoteActivity.this));
            setActionBarIcon(StyleAttributes.homeButtonNotesGrid);
        } else {
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                    || orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL));
            } else {
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                        StaggeredGridLayoutManager.VERTICAL));
            }
            setActionBarIcon(StyleAttributes.homeButtonNotesLinear);
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void createDialog() {
        /** Options for user to select */
        String choose[] = {
                "Dark", "Light", "Dark Red", "Light Red", "Dark Blue", "Light Blue"
        };

        AlertDialog.Builder b = new AlertDialog.Builder(this);

        /** Setting a title for the window */
        b.setTitle("Choose your Application Theme");

        int existing_theme = mPrefs.getInt(BaseActivity.SHARED_PREF_KEY_THEME, 0);
        Log.d(LOG_TAG, "Theme info | existing_theme = " + existing_theme);

        /** Setting items to the alert dialog */
        b.setSingleChoiceItems(choose, existing_theme, null);

        /** Setting a positive button and its listener */
        b.setPositiveButton("OK", new DialogClickListener());

        /** Setting a positive button and its listener */
        b.setNegativeButton("Cancel", null);

        /** Creating the alert dialog window using the builder class */
        AlertDialog d = b.create();

        /** show dialog */
        d.show();
    }

    private class DialogClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Log.d(LOG_TAG, "which = " + which);
            Editor editor = mPrefs.edit();
            editor.putInt(BaseActivity.SHARED_PREF_KEY_THEME,
                    ((AlertDialog) dialog).getListView().getCheckedItemPosition());
            editor.commit();
            Intent intent = new Intent(getBaseContext(), NoteActivity.class);

            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 500,
                    PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT
                            | PendingIntent.FLAG_CANCEL_CURRENT));
            finish();
        }

    }
}
