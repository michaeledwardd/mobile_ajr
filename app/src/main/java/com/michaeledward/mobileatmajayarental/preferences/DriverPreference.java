package com.michaeledward.mobileatmajayarental.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.michaeledward.mobileatmajayarental.entity.Driver;
import com.michaeledward.mobileatmajayarental.entity.UserLogin;

public class DriverPreference {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context con;

    public static final String IS_LOGIN = "IsLogin";
    public static final String KEY_ID_DRIVER = "id_driver";
    public static final String KEY_EMAIL_DRIVER = "email_driver";
    public static final String KEY_NAMA_DRIVER = "nama_driver";
    public static final String KEY_ALAMAT_DRIVER = "alamat_driver";
    public static final String KEY_JENIS_KELAMIN = "jenis_kelamin";
    public static final String KEY_NO_TELP = "no_telp_driver";
    public static final String KEY_STATUS_BERKAS = "status_berkas";
    public static final String KEY_STATUS_TERSEDIA = "status_tersedia";
    public static final String KEY_BIAYA_SEWA_DRIVER = "biaya_sewa_driver";
    public static final String KEY_RERATA_RATING = "rerata_rating";
    public static final String KEY_PASSWORD = "password";

    public DriverPreference(Context C){
        con = C;
        sharedPreferences = C.getSharedPreferences("driverPreference", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void SetLogin(Driver driver){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID_DRIVER, driver.getId_driver());
        editor.putString(KEY_EMAIL_DRIVER, driver.getEmail_driver());
        editor.putString(KEY_NAMA_DRIVER, driver.getNama_driver());
        editor.putString(KEY_ALAMAT_DRIVER, driver.getAlamat());
        editor.putString(KEY_JENIS_KELAMIN, driver.getJenis_kelamin());
        editor.putString(KEY_NO_TELP, driver.getNo_telp());
        editor.putString(KEY_STATUS_BERKAS, driver.getStatus_tersedia());
        editor.putString(KEY_STATUS_TERSEDIA, driver.getStatus_tersedia());
        editor.putString(KEY_BIAYA_SEWA_DRIVER, driver.getBiaya_sewa_driver());
        editor.putString(KEY_RERATA_RATING, driver.getRerata_rating());
        editor.putString(KEY_PASSWORD, driver.getPassword());

        editor.commit();
    }

    public Driver GetDriverNow(){
        String id_driver;
        String nama_driver;
        String jenis_kelamin;
        String alamat;
        String email_driver;
        String status_tersedia;
        String biaya_sewa_driver;
        String no_telp;
        String rerata_rating;
        String password;

        id_driver = sharedPreferences.getString(KEY_ID_DRIVER, null);
        nama_driver = sharedPreferences.getString(KEY_NAMA_DRIVER, null);
        jenis_kelamin = sharedPreferences.getString(KEY_JENIS_KELAMIN, null);
        alamat = sharedPreferences.getString(KEY_ALAMAT_DRIVER, null);
        email_driver = sharedPreferences.getString(KEY_EMAIL_DRIVER, null);
        status_tersedia = sharedPreferences.getString(KEY_STATUS_TERSEDIA, null);
        biaya_sewa_driver = sharedPreferences.getString(KEY_BIAYA_SEWA_DRIVER, null);
        no_telp = sharedPreferences.getString(KEY_NO_TELP, null);
        rerata_rating = sharedPreferences.getString(KEY_RERATA_RATING, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);

        return new Driver(id_driver, nama_driver,
                jenis_kelamin, alamat, email_driver, status_tersedia, biaya_sewa_driver,
                no_telp, rerata_rating, password);
    }

    public String GetIDDriver()
    {
        return sharedPreferences.getString(KEY_ID_DRIVER, null);
    }

    public String GetStatusDriver(){
        return sharedPreferences.getString(KEY_STATUS_TERSEDIA, null);
    }

    public boolean CheckLogin()
    {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }



    public void Logout()
    {
        editor.clear();
        editor.commit();
    }

}
