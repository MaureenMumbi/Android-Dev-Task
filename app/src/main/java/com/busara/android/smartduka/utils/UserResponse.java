package com.busara.android.smartduka.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mauryn on 6/10/2018.
 */

public class UserResponse {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("first_name")
    @Expose
    private String first_name = "";

    @SerializedName("last_name")
    @Expose
    private String last_name = "";

    @SerializedName("is_active")
    @Expose
    private Boolean is_active = false;

    @SerializedName("is_staff")
    @Expose
    private Boolean is_staff = false;

    @SerializedName("is_superuser")
    @Expose
    private Boolean is_superuser;

    @SerializedName("groups")
    @Expose
    private List<String> groups = null;


    @SerializedName("phone_number")
    @Expose
    private String phone_number = "";

    @SerializedName("is_trainer")
    @Expose
    private Boolean is_trainer = false;

    @SerializedName("trainees")
    @Expose
    private String trainees = "";

    @SerializedName("date_joined")
    @Expose
    private String date_joined;

    @SerializedName("location")
    @Expose
    private Integer location;

    @SerializedName("password")
    @Expose
    private String password = "";


    public UserResponse(String username, String first_name, String last_name, Boolean isactive, Boolean isstaff, Boolean issuperuser, Boolean istrainer,
                        String trainee, List<String> group, String phonenumber, String joindate, Integer location, String pwd) {
        this.email = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_active = isactive;
        this.is_superuser = issuperuser;
        this.is_staff = is_staff;
        this.is_trainer = istrainer;
        this.trainees = trainee;
        this.groups = group;
        this.phone_number = phonenumber;
        this.date_joined = joindate;
        this.location = location;
        this.password = pwd;
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

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
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
    public String toString() {
        return "users{" +
                "email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", is_active=" + is_active +
                ", is_staff=" + is_staff +
                ", is_superuser=" + is_superuser +
                ", groups=" + groups +
                ", phone_number='" + phone_number + '\'' +
                ", is_trainer=" + is_trainer +
                ", trainees='" + trainees + '\'' +
                ", date_joined='" + date_joined + '\'' +
                ", location=" + location +
                ", password='" + password + '\'' +
                '}';
    }


}
