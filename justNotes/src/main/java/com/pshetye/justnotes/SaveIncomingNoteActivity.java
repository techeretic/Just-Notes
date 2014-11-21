
package com.pshetye.justnotes;

import com.pshetye.justnotes.database.DatabaseHelper;
import com.pshetye.justnotes.database.MyNote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SaveIncomingNoteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String title = intent.getStringExtra(Intent.EXTRA_SUBJECT);
                if (title == null)
                    title = "";
                MyNote note = new MyNote((int) System.currentTimeMillis(), title,
                        intent.getStringExtra(Intent.EXTRA_TEXT), "");
                DatabaseHelper.getInstance(SaveIncomingNoteActivity.this).addNote(note);
                NoteActivity.sDoUpdate = true;
                Toast.makeText(SaveIncomingNoteActivity.this, "Saved to JustNotes",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
