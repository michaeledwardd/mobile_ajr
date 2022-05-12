package com.michaeledward.mobileatmajayarental.entity;

import androidx.databinding.BaseObservable;
import com.google.gson.annotations.SerializedName;

public class CustomerFromJSON extends BaseObservable{

    @SerializedName("id_customer")
    private String id_customer;

    @SerializedName("email_customer")
    private String email_customer;

    @SerializedName("nama_customer")
    private String nama_customer;

    @SerializedName("alamat_customer")
    private String alamat_customer;

    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;

    @SerializedName("no_telp")
    private String no_telp;

    @SerializedName("status_berkas")
    private String status_berkas;

    @SerializedName("asal_customer")
    private String asal_customer;

    @SerializedName("nomor_kartupengenal")
    private String nomor_kartupengenal;

    @SerializedName("no_sim")
    private String no_sim;

    @SerializedName("password")
    private String password;

    @SerializedName("usia")
    private String usia;

    public CustomerFromJSON(String id_customer, String email_customer, String nama_customer,
                            String alamat_customer, String jenis_kelamin, String no_telp,
                            String status_berkas, String asal_customer, String nomor_kartupengenal,
                            String no_sim, String password, String usia) {
        this.id_customer = id_customer;
        this.email_customer = email_customer;
        this.nama_customer = nama_customer;
        this.alamat_customer = alamat_customer;
        this.jenis_kelamin = jenis_kelamin;
        this.no_telp = no_telp;
        this.status_berkas = status_berkas;
        this.asal_customer = asal_customer;
        this.nomor_kartupengenal = nomor_kartupengenal;
        this.no_sim = no_sim;
        this.password = password;
        this.usia = usia;
    }

    public String getId_customer() {
        return id_customer;
    }

    public void setId_customer(String id_customer) {
        this.id_customer = id_customer;
    }

    public String getEmail_customer() {
        return email_customer;
    }

    public void setEmail_customer(String email_customer) {
        this.email_customer = email_customer;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public String getAlamat_customer() {
        return alamat_customer;
    }

    public void setAlamat_customer(String alamat_customer) {
        this.alamat_customer = alamat_customer;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getStatus_berkas() {
        return status_berkas;
    }

    public void setStatus_berkas(String status_berkas) {
        this.status_berkas = status_berkas;
    }

    public String getAsal_customer() {
        return asal_customer;
    }

    public void setAsal_customer(String asal_customer) {
        this.asal_customer = asal_customer;
    }

    public String getNomor_kartupengenal() {
        return nomor_kartupengenal;
    }

    public void setNomor_kartupengenal(String nomor_kartupengenal) {
        this.nomor_kartupengenal = nomor_kartupengenal;
    }

    public String getNo_sim() {
        return no_sim;
    }

    public void setNo_sim(String no_sim) {
        this.no_sim = no_sim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }
}
