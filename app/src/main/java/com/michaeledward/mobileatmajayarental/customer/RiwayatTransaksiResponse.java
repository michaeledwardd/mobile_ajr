package com.michaeledward.mobileatmajayarental.customer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiwayatTransaksiResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<RiwayatTransaksiFromJSON> riwayatTransaksiList;

    public String getMessage(){
        return message;
    }

    public List<RiwayatTransaksiFromJSON> getRiwayatTransaksiList() {
        return riwayatTransaksiList;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
