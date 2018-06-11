package com.busara.android.smartduka.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mauryn on 6/11/2018.
 */

public class CategoryResult implements Parcelable {

    public static final Creator<CategoryResult> CREATOR = new Creator<CategoryResult>() {
        @Override
        public CategoryResult createFromParcel(Parcel in) {
            return new CategoryResult(in);
        }

        @Override
        public CategoryResult[] newArray(int size) {
            return new CategoryResult[size];
        }
    };
    private Integer id;
    private String name = "";
    private String description = "";
    private Boolean is_active;
    private String created;


    protected CategoryResult(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        description = in.readString();
        byte tmpIs_active = in.readByte();
        is_active = tmpIs_active == 0 ? null : tmpIs_active == 1;
        created = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeByte((byte) (is_active == null ? 0 : is_active ? 1 : 2));
        parcel.writeString(created);
    }
}
