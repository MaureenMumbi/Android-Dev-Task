package com.busara.android.smartduka.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Mauryn on 6/11/2018.
 */

public class Categories implements Parcelable {


    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };
    private Integer count;
    private Integer page_size;
    private Integer current_page;
    private Integer total_pages;
    private ArrayList<CategoryResult> results = null;

    protected Categories(Parcel in) {
        if (in.readByte() == 0) {
            count = null;
        } else {
            count = in.readInt();
        }
        if (in.readByte() == 0) {
            page_size = null;
        } else {
            page_size = in.readInt();
        }
        if (in.readByte() == 0) {
            current_page = null;
        } else {
            current_page = in.readInt();
        }
        if (in.readByte() == 0) {
            total_pages = null;
        } else {
            total_pages = in.readInt();
        }
        if (in.readByte() == 0x01) {
            results = new ArrayList<>();
            in.readList(results, CategoryResult.class.getClassLoader());
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

    public ArrayList<CategoryResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<CategoryResult> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (count == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(count);
        }
        if (page_size == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(page_size);
        }
        if (current_page == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(current_page);
        }
        if (total_pages == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(total_pages);
        }

        if (results == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeList(results);
        }
    }
}
