package com.michaeledward.mobileatmajayarental.manager.laporantopcustomer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanTopCustomerResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<LaporanTopCustomerFromJSON> laporanTopCustomerFromJSONList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanTopCustomerFromJSON> getLaporanTopCustomerFromJSONList() {
        return laporanTopCustomerFromJSONList;
    }

    public void setLaporanTopCustomerFromJSONList(List<LaporanTopCustomerFromJSON> laporanTopCustomerFromJSONList) {
        this.laporanTopCustomerFromJSONList = laporanTopCustomerFromJSONList;
    }

}
