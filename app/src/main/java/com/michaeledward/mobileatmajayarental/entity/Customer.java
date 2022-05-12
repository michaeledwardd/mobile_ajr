package com.michaeledward.mobileatmajayarental.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.michaeledward.mobileatmajayarental.BR;

public class Customer extends BaseObservable {
    private String id_customer;
    private String nama_customer;
    private String alamat_customer;
    private String email_customer;
    private String jenis_kelamin;
    private String no_telp;
    private String status_berkas;
    private String asal_customer;
    private String nomor_kartupengenal;
    private String password;
    private String no_sim;
    private String usia;

    public Customer() {
    }

    public Customer(String id_customer, String nama_customer, String alamat_customer,
                    String email_customer, String jenis_kelamin, String no_telp, String status_berkas,
                    String asal_customer, String nomor_kartupengenal, String password, String no_sim,
                    String usia) {
        this.id_customer = id_customer;
        this.nama_customer = nama_customer;
        this.alamat_customer = alamat_customer;
        this.email_customer = email_customer;
        this.jenis_kelamin = jenis_kelamin;
        this.no_telp = no_telp;
        this.status_berkas = status_berkas;
        this.asal_customer = asal_customer;
        this.nomor_kartupengenal = nomor_kartupengenal;
        this.password = password;
        this.no_sim = no_sim;
        this.usia = usia;
    }

    @Bindable
    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
        notifyPropertyChanged(BR.id_driver);
    }

    @Bindable
    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
        notifyPropertyChanged(BR.nama_customer);
    }

    @Bindable
    public String getAlamat_customer() {
        return alamat_customer;
    }

    public void setAlamat_customer(String alamat_customer) {
        this.alamat_customer = alamat_customer;
        notifyPropertyChanged(BR.alamat_customer);
    }

    @Bindable
    public String getEmail_customer() {
        return email_customer;
    }

    public void setEmail_customer(String email_customer) {
        this.email_customer = email_customer;
        notifyPropertyChanged(BR.email_customer);
    }

    @Bindable
    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
        notifyPropertyChanged(BR.jenis_kelamin);
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
    public String getStatus_berkas() {
        return status_berkas;
    }

    public void setStatus_berkas(String status_berkas) {
        this.status_berkas = status_berkas;
        notifyPropertyChanged(BR.status_berkas);
    }

    @Bindable
    public String getAsal_customer() {
        return asal_customer;
    }

    public void setAsal_customer(String asal_customer) {
        this.asal_customer = asal_customer;
        notifyPropertyChanged(BR.asal_customer);
    }

    @Bindable
    public String getNomor_kartupengenal() {
        return nomor_kartupengenal;
    }

    public void setNomor_kartupengenal(String nomor_kartupengenal) {
        this.nomor_kartupengenal = nomor_kartupengenal;
        notifyPropertyChanged(BR.nomor_kartupengenal);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getNo_sim() {
        return no_sim;
    }

    public void setNo_sim(String no_sim) {
        this.no_sim = no_sim;
        notifyPropertyChanged(BR.no_sim);
    }

    @Bindable
    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
        notifyPropertyChanged(BR.usia);
    }
}