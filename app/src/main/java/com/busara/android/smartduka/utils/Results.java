package com.busara.android.smartduka.utils;

/**
 * Created by Mauryn on 6/9/2018.
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mauryn on 6/7/2018.
 */


public class Results implements Parcelable {

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
    private String name = "";
    private Boolean is_active = true;
    private int loc_order = 0;
    private int id = 0;

    protected Results(Parcel in) {
        name = in.readString();
        byte tmpIs_active = in.readByte();
        is_active = tmpIs_active == 0 ? null : tmpIs_active == 1;
        loc_order = in.readInt();
        id = in.readInt();


    }

    public String getname() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    }
}
