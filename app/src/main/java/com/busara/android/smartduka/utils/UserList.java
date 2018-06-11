package com.busara.android.smartduka.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * Created by Mauryn on 6/7/2018.
 */

public class UserList implements Parcelable {


    public static final Creator<UserList> CREATOR = new Creator<UserList>() {
        @Override
        public UserList createFromParcel(Parcel in) {
            return new UserList(in);
        }

        @Override
        public UserList[] newArray(int size) {
            return new UserList[size];
        }
    };
    private String email = "";
    private String first_name = "";
    private String last_name = "";
    private Boolean is_active = false;
    private Boolean is_staff = false;
    private Boolean is_superuser;
    private List<String> groups = null;
    private String phone_number = "";
    private Boolean is_trainer = false;
    private String trainees = "";
    private Date date_joined;
    private String location = "";
    private String password = "";

    protected UserList(Parcel in) {
        email = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        byte tmpIs_active = in.readByte();
        is_active = tmpIs_active == 0 ? null : tmpIs_active == 1;
        byte tmpIs_staff = in.readByte();
        is_staff = tmpIs_staff == 0 ? null : tmpIs_staff == 1;
        byte tmpIs_superuser = in.readByte();
        is_superuser = tmpIs_superuser == 0 ? null : tmpIs_superuser == 1;
        groups = in.createStringArrayList();
        phone_number = in.readString();
        byte tmpIs_trainer = in.readByte();
        is_trainer = tmpIs_trainer == 0 ? null : tmpIs_trainer == 1;
        trainees = in.readString();
        location = in.readString();
        password = in.readString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTrainees() {
        return trainees;
    }

    public void setTrainees(String trainees) {
        this.trainees = trainees;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Boolean getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(Boolean is_staff) {
        this.is_staff = is_staff;
    }

    public Boolean getIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(Boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public Boolean getIs_trainer() {
        return is_trainer;
    }

    public void setIs_trainer(Boolean is_trainer) {
        this.is_trainer = is_trainer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeByte((byte) (is_active == null ? 0 : is_active ? 1 : 2));
        dest.writeByte((byte) (is_staff == null ? 0 : is_staff ? 1 : 2));
        dest.writeByte((byte) (is_superuser == null ? 0 : is_superuser ? 1 : 2));
        dest.writeStringList(groups);
        dest.writeString(phone_number);
        dest.writeByte((byte) (is_trainer == null ? 0 : is_trainer ? 1 : 2));
        dest.writeString(trainees);
        dest.writeString(location);
        dest.writeString(password);
    }
}
