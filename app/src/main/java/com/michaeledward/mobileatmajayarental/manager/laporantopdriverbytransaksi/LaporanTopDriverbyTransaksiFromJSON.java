package com.michaeledward.mobileatmajayarental.manager.laporantopdriverbytransaksi;

import com.google.gson.annotations.SerializedName;

public class LaporanTopDriverbyTransaksiFromJSON {
    @SerializedName("id_driver")
    private String id_driver;

    @SerializedName("nama_driver")
    private String nama_driver;

    @SerializedName("jumlah_transaksi")
    private String jumlah_transaksi;

    public LaporanTopDriverbyTransaksiFromJSON(String id_driver, String nama_driver, String jumlah_transaksi) {
        this.id_driver = id_driver;
        this.nama_driver = nama_driver;
        this.jumlah_transaksi = jumlah_transaksi;
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

    public String getJumlah_transaksi() {
        return jumlah_transaksi;
    }

    public void setJumlah_transaksi(String jumlah_transaksi) {
        this.jumlah_transaksi = jumlah_transaksi;
    }
}
