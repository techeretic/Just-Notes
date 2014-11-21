
package com.pshetye.justnotes;

import com.pshetye.justnotes.database.DatabaseHelper;
import com.pshetye.justnotes.database.MyNote;
import com.pshetye.justnotes.fab.FloatingActionButton;
import com.pshetye.justnotes.util.NoteAnimator;
import com.pshetye.justnotes.util.StyleAttributes;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class ViewNoteActivity extends BaseActivity {

    private static final String LOG_TAG = "ViewNoteActivity";

    private String noteContent = null;

    private String noteTitle = null;

    private MyNote mNote = null;

    private TextView noteTitleView;

    private TextView noteTextView;

    private boolean updated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Inside onCreate");

        mNote = (MyNote) getIntent().getParcelableExtra("Note");
        noteContent = mNote.getNote();
        noteTitle = mNote.getTitle();

        Log.d(LOG_TAG, "noteTitle == " + noteTitle);

        noteTextView = (TextView) findViewById(R.id.notetextview);
        noteTitleView = (TextView) findViewById(R.id.notetitleview);
        if (noteTitle.isEmpty()) {
            noteTitleView.setVisibility(View.GONE);
        } else {
            noteTitleView.setText(noteTitle);
            noteTitleView.setMovementMethod(new ScrollingMovementMethod());
        }

        ViewCompat.setTransitionName(findViewById(R.id.layout_view), "noteview");

        noteTextView.setText(noteContent);
        noteTextView.setMovementMethod(new ScrollingMovementMethod());
        setActionBarIcon(StyleAttributes.homeButtonViewNote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                deleteNote();
                return true;
            case R.id.action_share:
                shareNote();
                return true;
            case R.id.action_edit:
                InputActivity.launchInput(ViewNoteActivity.this, findViewById(R.id.layout_view), mNote);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResource() {
        // TODO Auto-generated method stub
        return R.layout.activity_view_note;
    }

    public static void launchViewNote(BaseActivity activity, View transitionView, MyNote note) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                transitionView, "noteview");
        Intent intent = new Intent(activity, ViewNoteActivity.class);
        intent.putExtra("Note", note);
        ActivityCompat.startActivityForResult(activity, intent, BaseActivity.VIEW_CODE,
                options.toBundle());
    }

    private void shareNote() {
        Log.d(LOG_TAG, "Inside ShareNote");
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, mNote.getTitle());
        sharingIntent.putExtra(Intent.EXTRA_TEXT, mNote.getNote() + "\n\nShared from JustNotes");
        startActivity(Intent.createChooser(sharingIntent, "Choose option"));
    }

    private void deleteNote() {
        Log.d(LOG_TAG, "Inside DeleteNote");
        DatabaseHelper.getInstance(ViewNoteActivity.this).deleteNote(mNote);
        setResult(RESULT_OK);
        endActivity();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (updated) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == BaseActivity.INPUT_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                mNote = data.getParcelableExtra("Note");
                noteTitle = mNote.getTitle();
                if (noteTitle.isEmpty()) {
                    noteTitleView.setVisibility(View.GONE);
                } else {
                    noteTitleView.setVisibility(View.VISIBLE);
                    noteTitleView.setText(noteTitle);
                    noteTitleView.setMovementMethod(new ScrollingMovementMethod());
                }
                noteTextView.setText(mNote.getNote());
                updated = true;
            } else {
                setResult(RESULT_CANCELED);
            }
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        noteTitleView.setMovementMethod(LinkMovementMethod.getInstance());
        noteTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @TargetApi(Build.VERSION_CODES.L)
    private void endActivity() {
        finishAfterTransition();
    }

    public void setTranstionName(TextView tv) {
        ViewCompat.setTransitionName(tv, "noteview");
    }
}
