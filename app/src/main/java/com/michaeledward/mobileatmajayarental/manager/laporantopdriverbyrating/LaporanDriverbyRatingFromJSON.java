package com.michaeledward.mobileatmajayarental.manager.laporantopdriverbyrating;

import com.google.gson.annotations.SerializedName;

public class LaporanDriverbyRatingFromJSON {
    @SerializedName("id_driver")
    private String id_driver;

    @SerializedName("nama_driver")
    private String nama_driver;

    @SerializedName("jumlah_transaksi")
    private int jumlah_transaksi;

    @SerializedName("rerata_rating_drv")
    private float rerata_rating_drv;

    public LaporanDriverbyRatingFromJSON(String id_driver, String nama_driver, int jumlah_transaksi, float rerata_rating_drv) {
        this.id_driver = id_driver;
        this.nama_driver = nama_driver;
        this.jumlah_transaksi = jumlah_transaksi;
        this.rerata_rating_drv = rerata_rating_drv;
    }

    public String getId_driver() {
        return id_driver;
    }

    public void setId_driver(String id_driver) {
        this.id_driver = id_driver;
    }

    public String getNama_driver() {
        return nama_driver;
    }

    public void setNama_driver(String nama_driver) {
        this.nama_driver = nama_driver;
    }

    public int getJumlah_transaksi() {
        return jumlah_transaksi;
    }

    public void setJumlah_transaksi(int jumlah_transaksi) {
        this.jumlah_transaksi = jumlah_transaksi;
    }

    public float getRerata_rating_drv() {
        return rerata_rating_drv;
    }

    public void setRerata_rating_drv(float rerata_rating_drv) {
        this.rerata_rating_drv = rerata_rating_drv;
    }
}
