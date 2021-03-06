package com.michaeledward.mobileatmajayarental.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.michaeledward.mobileatmajayarental.BR;

public class Driver extends BaseObservable{
    private String id_driver;
    private String nama_driver;
    private String jenis_kelamin;
    private String alamat;
    private String email_driver;
    private String status_tersedia;
    private String biaya_sewa_driver;
    private String no_telp;
    private String rerata_rating;
    private String password;

    public Driver(String status_tersedia){
        this.status_tersedia = status_tersedia;
    }

    public Driver(String nama_driver, String alamat, String no_telp, String biaya_sewa_driver, String jenis_kelamin){
        this.nama_driver = nama_driver;
        this.jenis_kelamin = jenis_kelamin;
        this.biaya_sewa_driver = biaya_sewa_driver;
        this.no_telp = no_telp;
        this.alamat = alamat;
    }

    public Driver(String id_driver, String nama_driver, String jenis_kelamin, String alamat,
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

    @Bindable
    public String getId_driver() {
        return id_driver;
    }

    public void setId_driver(String id_driver) {
        this.id_driver = id_driver;
        notifyPropertyChanged(BR.id_driver);
    }

    @Bindable
    public String getNama_driver() {
        return nama_driver;
    }

    public void setNama_driver(String nama_driver) {
        this.nama_driver = nama_driver;
        notifyPropertyChanged(BR.nama_driver);
    }

    @Bindable
    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    @Bindable
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
        notifyPropertyChanged(BR.alamat);
    }

    @Bindable
    public String getEmail_driver() {
        return email_driver;
    }

    public void setEmail_driver(String email_driver) {
        this.email_driver = email_driver;
    }

    @Bindable
    public String getStatus_tersedia() {
        return status_tersedia;
    }

    public void setStatus_tersedia(String status_tersedia) {
        this.status_tersedia = status_tersedia;
        notifyPropertyChanged(BR.status_tersedia);
    }

    @Bindable
    public String getBiaya_sewa_driver() {
        return biaya_sewa_driver;
    }

    public void setBiaya_sewa_driver(String biaya_sewa_driver) {
        this.biaya_sewa_driver = biaya_sewa_driver;
        notifyPropertyChanged(BR.biaya_sewa_driver);
    }

    @Bindable
    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
        notifyPropertyChanged(BR.no_telp);
    }

    @Bindable
    public String getRerata_rating() {
        return rerata_rating;
    }

    public void setRerata_rating(String rerata_rating) {
        this.rerata_rating = rerata_rating;
        notifyPropertyChanged(BR.rerata_rating);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
