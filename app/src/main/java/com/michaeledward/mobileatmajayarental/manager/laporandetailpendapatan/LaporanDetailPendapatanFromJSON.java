package com.michaeledward.mobileatmajayarental.manager.laporandetailpendapatan;

import com.google.gson.annotations.SerializedName;

public class LaporanDetailPendapatanFromJSON {
    @SerializedName("nama_customer")
    private String nama_customer;

    @SerializedName("nama_mobil")
    private String nama_mobil;

    @SerializedName("jenis_transaksi")
    private String jenis_transaksi;

    @SerializedName("jumlah_peminjaman")
    private int jumlah_peminjaman;

    @SerializedName("pendapatan")
    private int pendapatan;

    public LaporanDetailPendapatanFromJSON(String nama_customer, String nama_mobil, String jenis_transaksi, int jumlah_peminjaman, int pendapatan) {
        this.nama_customer = nama_customer;
        this.nama_mobil = nama_mobil;
        this.jenis_transaksi = jenis_transaksi;
        this.jumlah_peminjaman = jumlah_peminjaman;
        this.pendapatan = pendapatan;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public String getNama_mobil() {
        return nama_mobil;
    }

    public void setNama_mobil(String nama_mobil) {
        this.nama_mobil = nama_mobil;
    }

    public String getJenis_transaksi() {
        return jenis_transaksi;
    }

    public void setJenis_transaksi(String jenis_transaksi) {
        this.jenis_transaksi = jenis_transaksi;
    }

    public int getJumlah_peminjaman() {
        return jumlah_peminjaman;
    }

    public void setJumlah_peminjaman(int jumlah_peminjaman) {
        this.jumlah_peminjaman = jumlah_peminjaman;
    }

    public int getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(int pendapatan) {
        this.pendapatan = pendapatan;
    }
}
