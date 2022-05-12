package com.michaeledward.mobileatmajayarental.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.michaeledward.mobileatmajayarental.BR;

public class Pegawai extends BaseObservable{
    private String id_pegawai;
    private String id_role;
    private String nama_pegawai;
    private String foto_pegawai;
    private String jenis_kelamin;
    private String alamat;
    private String email;
    private String password;
    private String is_aktif;

    public Pegawai(String id_pegawai, String id_role, String nama_pegawai, String foto_pegawai,
                   String jenis_kelamin, String alamat, String email, String password, String is_aktif) {
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

    @Bindable
    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
        notifyPropertyChanged(BR.id_pegawai);

    }

    @Bindable
    public String getId_role() {
        return id_role;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
        notifyPropertyChanged(BR.id_role);
    }

    @Bindable
    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
        notifyPropertyChanged(BR.nama_pegawai);
    }

    @Bindable
    public String getFoto_pegawai() {
        return foto_pegawai;
    }

    public void setFoto_pegawai(String foto_pegawai) {
        this.foto_pegawai = foto_pegawai;
        notifyPropertyChanged(BR.foto_pegawai);
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
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
        notifyPropertyChanged(BR.alamat);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
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
    public String getIs_aktif() {
        return is_aktif;
    }

    public void setIs_aktif(String is_aktif) {
        this.is_aktif = is_aktif;
        notifyPropertyChanged(BR.is_aktif);
    }
}
