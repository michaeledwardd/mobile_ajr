package com.michaeledward.mobileatmajayarental.entity;

import androidx.databinding.BaseObservable;
import com.google.gson.annotations.SerializedName;

public class DriverFromJSON extends BaseObservable{
    @SerializedName("id_driver")
    private String id_driver;

    @SerializedName("nama_driver")
    private String nama_driver;

    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("email_driver")
    private String email_driver;

    @SerializedName("password_driver")
    private String password_driver;

    @SerializedName("status_tersedia")
    private String status_tersedia;

    @SerializedName("is_aktif")
    private int is_aktif;

    @SerializedName("biaya_sewa_driver")
    private double biaya_sewa_driver;

    @SerializedName("no_telp")
    private String no_telp;

    @SerializedName("rerata_rating")
    private float rerata_rating;

    public DriverFromJSON(String id_driver, String nama_driver, String jenis_kelamin, String alamat, String email_driver, String password_driver, String status_tersedia, int is_aktif, double biaya_sewa_driver, String no_telp, float rerata_rating) {
        this.id_driver = id_driver;
        this.nama_driver = nama_driver;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.email_driver = email_driver;
        this.password_driver = password_driver;
        this.status_tersedia = status_tersedia;
        this.is_aktif = is_aktif;
        this.biaya_sewa_driver = biaya_sewa_driver;
        this.no_telp = no_telp;
        this.rerata_rating = rerata_rating;
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

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail_driver() {
        return email_driver;
    }

    public void setEmail_driver(String email_driver) {
        this.email_driver = email_driver;
    }

    public String getPassword_driver() {
        return password_driver;
    }

    public void setPassword_driver(String password_driver) {
        this.password_driver = password_driver;
    }

    public String getStatus_tersedia() {
        return status_tersedia;
    }

    public void setStatus_tersedia(String status_tersedia) {
        this.status_tersedia = status_tersedia;
    }

    public int getIs_aktif() {
        return is_aktif;
    }

    public void setIs_aktif(int is_aktif) {
        this.is_aktif = is_aktif;
    }

    public double getBiaya_sewa_driver() {
        return biaya_sewa_driver;
    }

    public void setBiaya_sewa_driver(double biaya_sewa_driver) {
        this.biaya_sewa_driver = biaya_sewa_driver;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public float getRerata_rating() {
        return rerata_rating;
    }

    public void setRerata_rating(float rerata_rating) {
        this.rerata_rating = rerata_rating;
    }
}
