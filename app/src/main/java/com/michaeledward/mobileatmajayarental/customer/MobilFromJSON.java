package com.michaeledward.mobileatmajayarental.customer;

import com.google.gson.annotations.SerializedName;

public class MobilFromJSON {
    @SerializedName("nama_mobil")
    private String nama_mobil;

    @SerializedName("plat_nomor")
    private String plat_nomor;

    @SerializedName("status_ketersediaan")
    private String status_ketersediaan;

    @SerializedName("biaya_sewa")
    private double biaya_sewa;

    @SerializedName("foto_mobil")
    private String foto_mobil;

    public MobilFromJSON(String nama_mobil, String plat_nomor, String status_ketersediaan, double biaya_sewa
    ,String foto_mobil) {
        this.nama_mobil = nama_mobil;
        this.plat_nomor = plat_nomor;
        this.status_ketersediaan = status_ketersediaan;
        this.biaya_sewa = biaya_sewa;
        this.foto_mobil = foto_mobil;
    }

    public String getNama_mobil() {
        return nama_mobil;
    }

    public void setNama_mobil(String nama_mobil) {
        this.nama_mobil = nama_mobil;
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }

    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }

    public String getStatus_ketersediaan() {
        return status_ketersediaan;
    }

    public void setStatus_ketersediaan(String status_ketersediaan) {
        this.status_ketersediaan = status_ketersediaan;
    }

    public double getBiaya_sewa() {
        return biaya_sewa;
    }

    public void setBiaya_sewa(double biaya_sewa) {
        this.biaya_sewa = biaya_sewa;
    }

    public String getFoto_mobil() {
        return foto_mobil;
    }

    public void setFoto_mobil(String foto_mobil) {
        this.foto_mobil = foto_mobil;
    }
}
