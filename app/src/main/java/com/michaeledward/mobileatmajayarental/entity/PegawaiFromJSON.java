package com.michaeledward.mobileatmajayarental.entity;

import androidx.databinding.BaseObservable;
import com.google.gson.annotations.SerializedName;

public class PegawaiFromJSON extends BaseObservable{

    @SerializedName("id_pegawai")
    private int id_pegawai;

    @SerializedName("id_role")
    private int id_role;

    @SerializedName("nama_pegawai")
    private String nama_pegawai;

    @SerializedName("foto_pegawai")
    private String foto_pegawai;

    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("is_aktif")
    private int is_aktif;

    public PegawaiFromJSON(int id_pegawai, int id_role, String nama_pegawai, String foto_pegawai, String jenis_kelamin, String alamat, String email, String password, int is_aktif) {
        this.id_pegawai = id_pegawai;
        this.id_role = id_role;
        this.nama_pegawai = nama_pegawai;
        this.foto_pegawai = foto_pegawai;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
        this.is_aktif = is_aktif;
    }

    public int getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(int id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getFoto_pegawai() {
        return foto_pegawai;
    }

    public void setFoto_pegawai(String foto_pegawai) {
        this.foto_pegawai = foto_pegawai;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_aktif() {
        return is_aktif;
    }

    public void setIs_aktif(int is_aktif) {
        this.is_aktif = is_aktif;
    }
}
