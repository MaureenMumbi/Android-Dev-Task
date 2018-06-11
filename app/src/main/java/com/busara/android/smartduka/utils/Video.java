package com.busara.android.smartduka.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Mauryn on 6/9/2018.
 */

public class Video implements Parcelable {

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
    private int end_index = 0;
    private int start_index = 0;
    private int total_pages = 0;
    private int page_size = 0;
    private int current_page = 0;
    private int count = 0;
    private ArrayList<VideoResult> results = null;

    protected Video(Parcel in) {
        end_index = in.readInt();
        start_index = in.readInt();
        total_pages = in.readInt();
        page_size = in.readInt();
        current_page = in.readInt();
        count = in.readInt();
        if (in.readByte() == 0x01) {
            results = new ArrayList<>();
            in.readList(results, VideoResult.class.getClassLoader());
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


    //{"count":9,"next":null,"previous":null,"page_size":100,"current_page":1,"total_pages":1,"start_index":1,"end_index":9,
//"results":[{"id":4,"name":"Apply merchandising techniques","file_path":"http://api.smartduka.busaracenterlab.org/media/TNS_SmartDuka-Module_1_Part_A_EvUHzHl.m4v",

    public ArrayList<VideoResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<VideoResult> results) {
        this.results = results;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(end_index);
        dest.writeInt(start_index);
        dest.writeInt(total_pages);
        dest.writeInt(page_size);
        dest.writeInt(current_page);
        dest.writeInt(count);
        if (results == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(results);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
