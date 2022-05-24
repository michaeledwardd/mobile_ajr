package com.michaeledward.mobileatmajayarental.manager.laporantopdriverbyrating;

import com.google.gson.annotations.SerializedName;
import com.michaeledward.mobileatmajayarental.manager.laporanpenyewaanmobil.LaporanPenyewaanFromJSON;

import java.util.List;

public class LaporanDriverbyRatingResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<LaporanDriverbyRatingFromJSON> laporanDriverbyRatingFromJSONList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanDriverbyRatingFromJSON> getLaporanDriverbyRatingFromJSONList() {
        return laporanDriverbyRatingFromJSONList;
    }

    public void setLaporanDriverbyRatingFromJSONList(List<LaporanDriverbyRatingFromJSON> laporanDriverbyRatingFromJSONList) {
        this.laporanDriverbyRatingFromJSONList = laporanDriverbyRatingFromJSONList;
    }
}
