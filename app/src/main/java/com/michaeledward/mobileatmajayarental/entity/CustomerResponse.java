package com.michaeledward.mobileatmajayarental.entity;

import com.google.gson.annotations.SerializedName;

public class CustomerResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private CustomerFromJSON user;

    private String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public CustomerFromJSON getUser() {
        return user;
    }

    public void setUser(CustomerFromJSON user) {
        this.user = user;
    }
}
