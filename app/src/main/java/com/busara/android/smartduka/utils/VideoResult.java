package com.busara.android.smartduka.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mauryn on 6/9/2018.
 */

public class VideoResult implements Parcelable {

    public static final Creator<VideoResult> CREATOR = new Creator<VideoResult>() {
        @Override
        public VideoResult createFromParcel(Parcel in) {
            return new VideoResult(in);
        }

        @Override
        public VideoResult[] newArray(int size) {
            return new VideoResult[size];
        }
    };
    private int id;
    private String name = "";
    private String file_path = "";
    private String description = "";
    private int category = 0;
    private String category_name = "";
    private Boolean is_active;
    private Boolean has_pre_questions;
    private Boolean has_post_questions;
    private String created = "";
    private int no_times_watched = 0;


    //"results":[{"id":4,"name":"Apply merchandising techniques",
    // "file_path":"http://api.smartduka.busaracenterlab.org/media/TNS_SmartDuka-Module_1_Part_A_EvUHzHl.m4v",
    //            "description":"Product range and availability, Product display, Price competitiveness, Point-of-sale attractiveness & Best use of available space.","category":2,
//            "category_name":"Merchandising","is_active":true,"has_pre_questions":false,"has_post_questions":true,"created":"2018-03-15T11:36:35.872759+03:00","no_times_watched":0,"order":1}
    private int order = 0;

    protected VideoResult(Parcel in) {
        id = in.readInt();
        name = in.readString();
        file_path = in.readString();
        description = in.readString();
        category = in.readInt();
        category_name = in.readString();
        byte tmpIs_active = in.readByte();
        is_active = tmpIs_active == 0 ? null : tmpIs_active == 1;
        byte tmpHas_pre_questions = in.readByte();
        is_active = tmpHas_pre_questions == 0 ? null : tmpHas_pre_questions == 1;
        byte tmpHas_post_questions = in.readByte();
        is_active = tmpHas_post_questions == 0 ? null : tmpHas_post_questions == 1;
        created = in.readString();
        no_times_watched = in.readInt();
        order = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getNo_times_watched() {
        return no_times_watched;
    }

    public void setNo_times_watched(int no_times_watched) {
        this.no_times_watched = no_times_watched;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Boolean getHas_pre_questions() {
        return has_pre_questions;
    }

    public void setHas_pre_questions(Boolean has_pre_questions) {
        this.has_pre_questions = has_pre_questions;
    }

    public Boolean getHas_post_questions() {
        return has_post_questions;
    }

    public void setHas_post_questions(Boolean has_post_questions) {
        this.has_post_questions = has_post_questions;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(file_path);
        dest.writeString(description);
        dest.writeInt(category);
        dest.writeString(category_name);
        dest.writeByte((byte) (is_active == null ? 0 : is_active ? 1 : 2));
        dest.writeByte((byte) (has_post_questions == null ? 0 : has_post_questions ? 1 : 2));
        dest.writeByte((byte) (has_pre_questions == null ? 0 : has_pre_questions ? 1 : 2));
        dest.writeString(created);
        dest.writeInt(no_times_watched);
        dest.writeInt(order);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
