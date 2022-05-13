package com.michaeledward.mobileatmajayarental.customer;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class PromoFromJSON extends BaseObservable {
    @SerializedName("jenis_promo")
    private String jenisPromo;

    @SerializedName("kode_promo")
    private String kodePromo;

    @SerializedName("jumlah_potongan")
    private int jumlahpotongan;

    @SerializedName("status_promo")
    private String status_promo;

    @SerializedName("keterangan")
    private String keterangan;

    public PromoFromJSON(String jenisPromo, String kodePromo, int jumlahpotongan, String status_promo, String keterangan) {
        this.jenisPromo = jenisPromo;
        this.kodePromo = kodePromo;
        this.jumlahpotongan = jumlahpotongan;
        this.status_promo = status_promo;
        this.keterangan = keterangan;
    }

    public String getJenisPromo() {
        return jenisPromo;
    }

    public void setJenisPromo(String jenisPromo) {
        this.jenisPromo = jenisPromo;
    }

    public String getKodePromo() {
        return kodePromo;
    }

    public void setKodePromo(String kodePromo) {
        this.kodePromo = kodePromo;
    }

    public int getJumlahpotongan() {
        return jumlahpotongan;
    }

    public void setJumlahpotongan(int jumlahpotongan) {
        this.jumlahpotongan = jumlahpotongan;
    }

    public String getStatus_promo() {
        return status_promo;
    }

    public void setStatus_promo(String status_promo) {
        this.status_promo = status_promo;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
