
package com.pshetye.justnotes;

import com.pshetye.justnotes.database.DatabaseHelper;
import com.pshetye.justnotes.database.MyNote;
import com.pshetye.justnotes.fab.FloatingActionButton;
import com.pshetye.justnotes.util.NoteAnimator;
import com.pshetye.justnotes.util.StyleAttributes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class InputActivity extends BaseActivity {

    private static final String LOG_TAG = "InputActivity";
/*
    private FloatingActionButton fab_save_btn = null;
*/
    private MyNote mNote = null;

    private String noteContent = null;

    private String noteTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Inside onCreate");

        mNote = (MyNote) getIntent().getParcelableExtra("Note");
        if (mNote != null) {
            noteContent = mNote.getNote();
            noteTitle = mNote.getTitle();
    		setActionBarIcon(StyleAttributes.editButton);
        } else {
    		setActionBarIcon(StyleAttributes.addButton);
        }
/*
        // Add Button - Holder Fragment
        fab_save_btn = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_action_save_light))
                .withButtonColor(getResources().getColor(R.color.accent_blue))
                .withGravity(Gravity.TOP | Gravity.END).withMargins(15, 15, 0, 0).create();
        NoteAnimator.animateFAB(getApplicationContext(), fab_save_btn, NoteAnimator.IN,
                NoteAnimator.TOP);
        fab_save_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
*/
        if (mNote != null) {
            EditText textView0 = (EditText) findViewById(R.id.editText0);
            EditText textView1 = (EditText) findViewById(R.id.editText1);

            textView0.setText(noteTitle);
            textView1.setText(noteContent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notesave, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveNote();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResource() {
        // TODO Auto-generated method stub
        return R.layout.activity_input;
    }

    public static void launchInput(BaseActivity activity, View transitionView, String url) {
        /*
         * ActivityOptionsCompat options =
         * ActivityOptionsCompat.makeSceneTransitionAnimation( activity,
         * transitionView, LOG_TAG);
         */
        ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(transitionView,
                (int) transitionView.getTranslationX(), (int) transitionView.getTranslationY(),
                transitionView.getWidth(), transitionView.getHeight());
        Intent intent = new Intent(activity, InputActivity.class);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public static void launchInput(BaseActivity activity, View transitionView, MyNote note) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(transitionView,
                (int) transitionView.getTranslationX(), (int) transitionView.getTranslationY(),
                transitionView.getWidth(), transitionView.getHeight());
        Intent intent = new Intent(activity, InputActivity.class);
        intent.putExtra("Note", note);
        ActivityCompat.startActivityForResult(activity, intent, BaseActivity.INPUT_CODE,
                options.toBundle());
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    private void saveNote() {
        // TODO Auto-generated method stub
        EditText textView0 = (EditText) findViewById(R.id.editText0);
        EditText textView1 = (EditText) findViewById(R.id.editText1);
        if (mNote == null) {
            if (!textView0.getText().toString().isEmpty()
                    || !textView1.getText().toString().isEmpty()) {
                MyNote note = new MyNote((int) System.currentTimeMillis(), textView0
                        .getText().toString(), textView1.getText().toString(), "");
                DatabaseHelper.getInstance(InputActivity.this).addNote(note);
                finish();
                setResult(RESULT_OK);
            }
        } else {
            Log.d(LOG_TAG, "mNote != null");
            if (textView0.getText().toString().isEmpty()
                    && textView1.getText().toString().isEmpty()) {
                Log.d(LOG_TAG, "EVERYTHING BLANK!!!");
                setResult(RESULT_CANCELED);
                finish();
            } else {
                mNote.setPNote(textView1.getText().toString());
                mNote.setPTitle(textView0.getText().toString());
                DatabaseHelper.getInstance(InputActivity.this).updateNote(mNote);
                Intent i = new Intent();
                i.putExtra("Note", mNote);
                setResult(RESULT_OK, i);
                finish();
            }
        }
    }
}
