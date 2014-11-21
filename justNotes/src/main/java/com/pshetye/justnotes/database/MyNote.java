
package com.pshetye.justnotes.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MyNote implements Parcelable {

    private static final String LOG_TAG = "MyNote";
    
    private static final String DATE_FORMAT = "dd/MM/yyyy kk:mm:ss";

    long _id;
    
    String pTitle;

    String pNote;

    String pDate;

    static public SimpleDateFormat NoteDateFormat;

    public MyNote() {
        if (NoteDateFormat == null)
            NoteDateFormat = new SimpleDateFormat(DATE_FORMAT);
        pDate = NoteDateFormat.format(new Date());
    }

    public MyNote(long _id, String pNote) {
        if (NoteDateFormat == null)
            NoteDateFormat = new SimpleDateFormat(DATE_FORMAT);
        this._id = _id;
        this.pTitle = "";
        this.pNote = pNote;
        this.pDate = NoteDateFormat.format(new Date());
    }

    public MyNote(long _id, String pNote, String pDate) {
        if (NoteDateFormat == null)
            NoteDateFormat = new SimpleDateFormat(DATE_FORMAT);
        this._id = _id;
        this.pTitle = "";
        this.pNote = pNote;
        this.pDate = pDate;
    }

    public MyNote(long _id, String pTitle, String pNote, String pDate) {
        if (NoteDateFormat == null)
            NoteDateFormat = new SimpleDateFormat(DATE_FORMAT);
        this._id = _id;
        this.pTitle = pTitle;
        this.pNote = pNote;
        if (pDate.isEmpty()) {
            this.pDate = NoteDateFormat.format(new Date());
        } else {
            this.pDate = pDate;
        }
    }

    public MyNote(String pNote) {
        if (NoteDateFormat == null)
            NoteDateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.pTitle = "";
        this.pNote = pNote;
        this.pDate = NoteDateFormat.format(new Date());
    }

    public long getID() {
        return this._id;
    }

    public String getTitle() {
        return this.pTitle;
    }

    public String getNote() {
        return this.pNote;
    }

    public String getDate() {
        return this.pDate;
    }

    public void setID(long _id) {
        this._id = _id;
    }

    public void setPTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public void setPNote(String pNote) {
        this.pNote = pNote;
    }

    public void setDate(String pDate) {
        this.pDate = pDate;
    }

    // Parcelling part
    public MyNote(Parcel in) {
        String[] data = new String[4];

        in.readStringArray(data);
        
        this._id = Long.valueOf(data[0]);
        this.pTitle = data[1];
        this.pNote = data[2];
        this.pDate = data[3];
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeStringArray(new String[] {
                Objects.toString(this._id, null), this.pTitle, this.pNote, this.pDate
        });
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MyNote createFromParcel(Parcel in) {
            if (in == null)
                Log.d(LOG_TAG, "Parcel in is NULL");
            return new MyNote(in);
        }

        public MyNote[] newArray(int size) {
            return new MyNote[size];
        }
    };
}
