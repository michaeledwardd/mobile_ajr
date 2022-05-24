package com.michaeledward.mobileatmajayarental.manager.laporantopdriverbytransaksi;

import com.google.gson.annotations.SerializedName;
import com.michaeledward.mobileatmajayarental.manager.laporanpenyewaanmobil.LaporanPenyewaanFromJSON;

import java.util.List;

public class LaporanTopDriverbyTransaksiResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<LaporanTopDriverbyTransaksiFromJSON> laporanTopDriverbyTransaksiFromJSONList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanTopDriverbyTransaksiFromJSON> getLaporanTopDriverbyTransaksiFromJSONList() {
        return laporanTopDriverbyTransaksiFromJSONList;
    }

    public void setLaporanPenyewaanFromJSONList(List<LaporanTopDriverbyTransaksiFromJSON> laporanTopDriverbyTransaksiFromJSONList) {
        this.laporanTopDriverbyTransaksiFromJSONList = laporanTopDriverbyTransaksiFromJSONList;
    }
}
