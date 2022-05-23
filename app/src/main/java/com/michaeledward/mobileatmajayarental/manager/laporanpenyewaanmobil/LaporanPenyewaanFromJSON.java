package com.michaeledward.mobileatmajayarental.manager.laporanpenyewaanmobil;

import com.google.gson.annotations.SerializedName;

public class LaporanPenyewaanFromJSON {
    @SerializedName("tipe_mobil")
    private String tipe_mobil;

    @SerializedName("nama_mobil")
    private String nama_mobil;

    @SerializedName("jumlah_peminjaman")
    private int jumlah_peminjaman;

    @SerializedName("pendapatan")
    private int pendapatan;

    public LaporanPenyewaanFromJSON(String tipe_mobil, String nama_mobil, int jumlah_peminjaman, int pendapatan) {
        this.tipe_mobil = tipe_mobil;
        this.nama_mobil = nama_mobil;
        this.jumlah_peminjaman = jumlah_peminjaman;
        this.pendapatan = pendapatan;
    }

    public String getTipe_mobil() {
        return tipe_mobil;
    }

    public void setTipe_mobil(String tipe_mobil) {
        this.tipe_mobil = tipe_mobil;
    }

    public String getNama_mobil() {
        return nama_mobil;
    }

    public void setNama_mobil(String nama_mobil) {
        this.nama_mobil = nama_mobil;
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
