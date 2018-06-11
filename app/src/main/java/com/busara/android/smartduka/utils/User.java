package com.busara.android.smartduka.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mauryn on 6/7/2018.
 */

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private String username = "";
    private String password = "";
    private String grantType = "";
    private String clientID = "";
    private String clientSecret = "";

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
        grantType = in.readString();
        clientID = in.readString();
        clientSecret = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(grantType);
        dest.writeString(clientID);
        dest.writeString(clientSecret);
    }


//    @SerializedName("id")
//    int mId;
//
//    @SerializedName("name")
//    String mName;
//
//    public User(int id, String name ) {
//        this.mId = id;
//        this.mName = name;
//    }

}
