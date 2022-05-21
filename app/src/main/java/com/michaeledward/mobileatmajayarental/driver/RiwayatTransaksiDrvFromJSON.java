package com.michaeledward.mobileatmajayarental.driver;

import com.google.gson.annotations.SerializedName;

public class RiwayatTransaksiDrvFromJSON {
    @SerializedName("nama_customer")
    private String nama_customer;

    @SerializedName("nama_driver")
    private String nama_driver;

    @SerializedName("nama_mobil")
    private String nama_mobil;

    @SerializedName("nama_pegawai")
    private String nama_pegawai;

    @SerializedName("id_transaksi")
    private String id_transaksi;

    @SerializedName("jenis_promo")
    private String jenis_promo;

    @SerializedName("subtotal_all")
    private double subtotal_all;

    @SerializedName("status_transaksi")
    private String status_transaksi;

    public RiwayatTransaksiDrvFromJSON(String nama_customer, String nama_driver, String nama_mobil, String nama_pegawai, String id_transaksi, String jenis_promo, double subtotal_all, String status_transaksi) {
        this.nama_customer = nama_customer;
        this.nama_driver = nama_driver;
        this.nama_mobil = nama_mobil;
        this.nama_pegawai = nama_pegawai;
        this.id_transaksi = id_transaksi;
        this.jenis_promo = jenis_promo;
        this.subtotal_all = subtotal_all;
        this.status_transaksi = status_transaksi;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public String getNama_driver() {
        return nama_driver;
    }

    public void setNama_driver(String nama_driver) {
        this.nama_driver = nama_driver;
    }

    public String getNama_mobil() {
        return nama_mobil;
    }

    public void setNama_mobil(String nama_mobil) {
        this.nama_mobil = nama_mobil;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getJenis_promo() {
        return jenis_promo;
    }

    public void setJenis_promo(String jenis_promo) {
        this.jenis_promo = jenis_promo;
    }

    public double getSubtotal_all() {
        return subtotal_all;
    }

    public void setSubtotal_all(double subtotal_all) {
        this.subtotal_all = subtotal_all;
    }

    public String getStatus_transaksi() {
        return status_transaksi;
    }

    public void setStatus_transaksi(String status_transaksi) {
        this.status_transaksi = status_transaksi;
    }
}
