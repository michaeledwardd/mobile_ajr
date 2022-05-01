package com.michaeledward.mobileatmajayarental.entity;

import com.google.gson.annotations.SerializedName;

public class PegawaiResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private PegawaiFromJSON user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PegawaiFromJSON getUser() {
        return user;
    }

    public void setUser(PegawaiFromJSON user) {
        this.user = user;
    }
}
