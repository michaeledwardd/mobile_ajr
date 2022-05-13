package com.michaeledward.mobileatmajayarental.customer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MobilResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<MobilFromJSON> mobilList;

    public String getMessage(){
        return message;
    }

    public List<MobilFromJSON> getMobilList() {
        return mobilList;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
