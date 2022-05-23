package com.michaeledward.mobileatmajayarental.manager.laporanpenyewaanmobil;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanPenyewaanResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<LaporanPenyewaanFromJSON> laporanPenyewaanFromJSONList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanPenyewaanFromJSON> getLaporanPenyewaanFromJSONList() {
        return laporanPenyewaanFromJSONList;
    }

    public void setLaporanPenyewaanFromJSONList(List<LaporanPenyewaanFromJSON> laporanPenyewaanFromJSONList) {
        this.laporanPenyewaanFromJSONList = laporanPenyewaanFromJSONList;
    }
}
