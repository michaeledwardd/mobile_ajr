package com.michaeledward.mobileatmajayarental.manager.laporandetailpendapatan;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LaporanDetailPendapatanResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<LaporanDetailPendapatanFromJSON> laporanDetailPendapatanFromJSONList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanDetailPendapatanFromJSON> getLaporanDetailPendapatanFromJSONList() {
        return laporanDetailPendapatanFromJSONList;
    }

    public void setLaporanPenyewaanFromJSONList(List<LaporanDetailPendapatanFromJSON> laporanDetailPendapatanFromJSONList) {
        this.laporanDetailPendapatanFromJSONList = laporanDetailPendapatanFromJSONList;
    }
}
