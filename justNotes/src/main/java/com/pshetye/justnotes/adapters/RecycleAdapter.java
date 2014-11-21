
package com.pshetye.justnotes.adapters;

import com.pshetye.justnotes.R;
import com.pshetye.justnotes.R.id;
import com.pshetye.justnotes.R.layout;
import com.pshetye.justnotes.database.MyNote;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecycleAdapter extends
		RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements
		View.OnLongClickListener {
    private final static String LOG_TAG = "RecycleAdapter";

    private static List<MyNote> mNotes = new ArrayList<MyNote>();
    
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView noteTitle;

        public TextView noteText;

        public TextView noteDate;

        public ViewHolder(View view) {
            super(view);
            noteTitle = (TextView) view.findViewById(R.id.textView0);
            noteText = (TextView) view.findViewById(R.id.textView1);
            noteDate = (TextView) view.findViewById(R.id.textView2);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleAdapter(List<MyNote> objects, Context context) {
    	Log.d(LOG_TAG, "Inside Constructor");
    	mContext = context;
        mNotes = objects;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent,
                false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (mNotes.get(position).getTitle().isEmpty()) {
            holder.noteTitle.setVisibility(View.GONE);
            holder.noteText.setTextSize(20);
        } else {
            holder.noteTitle.setVisibility(View.VISIBLE);
            holder.noteTitle.setText(mNotes.get(position).getTitle());
        }
        holder.noteText.setText(mNotes.get(position).getNote());
        holder.noteDate.setText(mNotes.get(position).getDate());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mNotes.size();
    }

	@Override
	public boolean onLongClick(View view) {
		view = LayoutInflater.from(mContext).inflate(0, null);
		return true;
	}
}
