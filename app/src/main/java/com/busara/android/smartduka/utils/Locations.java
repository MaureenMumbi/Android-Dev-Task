package com.busara.android.smartduka.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


/**
 * Created by Mauryn on 6/7/2018.
 */


public class Locations implements Parcelable {

    public static final Creator<Locations> CREATOR = new Creator<Locations>() {
        @Override
        public Locations createFromParcel(Parcel in) {
            return new Locations(in);
        }

        @Override
        public Locations[] newArray(int size) {
            return new Locations[size];
        }
    };
    String name = "";
    private Boolean is_active = true;
    private int loc_order = 0;
    private int id = 0;
    private int count = 0;
    private ArrayList<Results> results = null;

    protected Locations(Parcel in) {
        name = in.readString();
        byte tmpIs_active = in.readByte();
        is_active = tmpIs_active == 0 ? null : tmpIs_active == 1;
        loc_order = in.readInt();
        id = in.readInt();
        count = in.readInt();
        if (in.readByte() == 0x01) {
            results = new ArrayList<>();
            in.readList(results, Results.class.getClassLoader());
        } else {
            results = null;
        }


    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeByte((byte) (is_active == null ? 0 : is_active ? 1 : 2));
        parcel.writeInt(loc_order);
        parcel.writeInt(id);
        parcel.writeInt(count);
        if (results == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeList(results);
        }
    }
}
