package com.michaeledward.mobileatmajayarental.entity;

import com.google.gson.annotations.SerializedName;

public class DriverResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private DriverFromJSON user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DriverFromJSON getUser() {
        return user;
    }

    public void setUser(DriverFromJSON user) {
        this.user = user;
    }
}
