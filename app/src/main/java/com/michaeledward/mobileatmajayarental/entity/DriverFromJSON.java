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

    @SerializedName("status_tersedia")
    private String status_tersedia;

    @SerializedName("biaya_sewa_driver")
    private String biaya_sewa_driver;

    @SerializedName("no_telp")
    private String no_telp;

    @SerializedName("rerata_rating")
    private String rerata_rating;

    @SerializedName("password")
    private String password;

    public DriverFromJSON(String id_driver, String nama_driver, String jenis_kelamin, String alamat,
                          String email_driver, String status_tersedia,
                          String biaya_sewa_driver, String no_telp, String rerata_rating, String password) {
        this.id_driver = id_driver;
        this.nama_driver = nama_driver;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.email_driver = email_driver;
        this.status_tersedia = status_tersedia;
        this.biaya_sewa_driver = biaya_sewa_driver;
        this.no_telp = no_telp;
        this.rerata_rating = rerata_rating;
        this.password = password;
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


    public String getStatus_tersedia() {
        return status_tersedia;
    }

    public void setStatus_tersedia(String status_tersedia) {
        this.status_tersedia = status_tersedia;
    }

    public String getBiaya_sewa_driver() {
        return biaya_sewa_driver;
    }

    public void setBiaya_sewa_driver(String biaya_sewa_driver) {
        this.biaya_sewa_driver = biaya_sewa_driver;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getRerata_rating() {
        return rerata_rating;
    }

    public void setRerata_rating(String rerata_rating) {
        this.rerata_rating = rerata_rating;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
