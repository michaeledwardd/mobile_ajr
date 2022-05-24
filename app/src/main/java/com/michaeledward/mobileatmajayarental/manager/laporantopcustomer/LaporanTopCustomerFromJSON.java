package com.michaeledward.mobileatmajayarental.manager.laporantopcustomer;


import com.google.gson.annotations.SerializedName;

public class LaporanTopCustomerFromJSON {
    @SerializedName("nama_customer")
    private String nama_customer;

    @SerializedName("jumlah_transaksi")
    private int jumlah_transaksi;

    public LaporanTopCustomerFromJSON(String nama_customer, int jumlah_transaksi) {
        this.nama_customer = nama_customer;
        this.jumlah_transaksi = jumlah_transaksi;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public int getJumlah_transaksi() {
        return jumlah_transaksi;
    }

    public void setJumlah_transaksi(int jumlah_transaksi) {
        this.jumlah_transaksi = jumlah_transaksi;
    }
}
