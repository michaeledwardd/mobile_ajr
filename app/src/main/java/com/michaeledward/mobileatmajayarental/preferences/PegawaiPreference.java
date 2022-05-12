package com.michaeledward.mobileatmajayarental.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.michaeledward.mobileatmajayarental.entity.Pegawai;
import com.michaeledward.mobileatmajayarental.entity.UserLogin;

public class PegawaiPreference {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context con;

    public static final String IS_LOGIN = "IsLogin";
    public static final String KEY_ID_PEGAWAI = "id_pegawai";
    public static final String KEY_ID_ROLE = "id_role";
    public static final String KEY_NAMA_PEGAWAI = "nama_pegawai";
    public static final String KEY_FOTO_PEGAWAI = "foto_pegawai";
    public static final String KEY_JENIS_KELAMIN = "jenis_kelamin";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_IS_AKTIF = "is_aktif";

    public PegawaiPreference(Context C){
        con = C;
        sharedPreferences = C.getSharedPreferences("pegawaiPreference", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void SetLogin(Pegawai pegawai){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID_PEGAWAI, pegawai.getId_pegawai());
        editor.putString(KEY_ID_ROLE, pegawai.getId_role());
        editor.putString(KEY_NAMA_PEGAWAI, pegawai.getNama_pegawai());
        editor.putString(KEY_FOTO_PEGAWAI, pegawai.getFoto_pegawai());
        editor.putString(KEY_JENIS_KELAMIN, pegawai.getJenis_kelamin());
        editor.putString(KEY_ALAMAT, pegawai.getAlamat());
        editor.putString(KEY_EMAIL, pegawai.getEmail());
        editor.putString(KEY_PASSWORD, pegawai.getPassword());
        editor.putString(KEY_IS_AKTIF, pegawai.getIs_aktif());

        editor.commit();
    }

    public Pegawai GetPegawaiNow(){
        String id_pegawai;
        String id_role;
        String nama_pegawai;
        String foto_pegawai;
        String jenis_kelamin;
        String alamat;
        String email;
        String password;
        String is_aktif;

        id_pegawai = sharedPreferences.getString(KEY_ID_PEGAWAI, null);
        id_role = sharedPreferences.getString(KEY_ID_ROLE, null);
        nama_pegawai = sharedPreferences.getString(KEY_NAMA_PEGAWAI, null);
        foto_pegawai = sharedPreferences.getString(KEY_FOTO_PEGAWAI, null);
        jenis_kelamin = sharedPreferences.getString(KEY_JENIS_KELAMIN, null);
        alamat = sharedPreferences.getString(KEY_ALAMAT, null);
        email = sharedPreferences.getString(KEY_EMAIL, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);
        is_aktif = sharedPreferences.getString(KEY_IS_AKTIF, null);

        return new Pegawai(id_pegawai, id_role,
                nama_pegawai, foto_pegawai,
                jenis_kelamin, alamat, email,
                password, is_aktif);
    }

    public boolean CheckLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void Logout(){
        editor.clear();
        editor.commit();
    }
}
