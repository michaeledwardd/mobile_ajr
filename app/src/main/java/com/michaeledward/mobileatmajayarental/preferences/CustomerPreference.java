package com.michaeledward.mobileatmajayarental.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.michaeledward.mobileatmajayarental.entity.Customer;
import com.michaeledward.mobileatmajayarental.entity.UserLogin;

public class CustomerPreference {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context con;

    public static final String IS_LOGIN = "IsLogin";
    public static final String KEY_ID_CUSTOMER = "id_customer";
    public static final String KEY_EMAIL = "email_customer";
    public static final String KEY_NAMA_CUSTOMER = "nama_customer";
    public static final String KEY_ALAMAT_CUSTOMER = "alamat_customer";
    public static final String KEY_JENIS_KELAMIN = "jenis_kelamin";
    public static final String KEY_NO_TELP = "no_telp";
    public static final String KEY_STATUS_BERKAS = "status_berkas";
    public static final String KEY_ASAL_CUSTOMER = "asal_customer";
    public static final String KEY_NOMOR_KARTUPENGENAL = "nomor_kartupengenal";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USIA = "usia";
    public static final String KEY_NO_SIM = "no_sim";

    public CustomerPreference(Context C){
        con = C;
        sharedPreferences = C.getSharedPreferences("customerPreference", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void SetLogin(Customer customer){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID_CUSTOMER, customer.getId_customer());
        editor.putString(KEY_NAMA_CUSTOMER, customer.getNama_customer());
        editor.putString(KEY_EMAIL, customer.getEmail_customer());
        editor.putString(KEY_ALAMAT_CUSTOMER, customer.getAlamat_customer());
        editor.putString(KEY_ASAL_CUSTOMER, customer.getAsal_customer());
        editor.putString(KEY_JENIS_KELAMIN, customer.getJenis_kelamin());
        editor.putString(KEY_NO_TELP, customer.getNo_telp());
        editor.putString(KEY_STATUS_BERKAS, customer.getStatus_berkas());
        editor.putString(KEY_NOMOR_KARTUPENGENAL, customer.getNomor_kartupengenal());
        editor.putString(KEY_USIA, customer.getUsia());
        editor.putString(KEY_NO_SIM, customer.getNo_sim());
        editor.putString(KEY_PASSWORD, customer.getPassword());

        editor.commit();
    }

    public Customer GetCustomerNow(){
        String id_customer;
        String nama_customer;
        String alamat_customer;
        String email_customer;
        String jenis_kelamin;
        String no_telp;
        String status_berkas;
        String asal_customer;
        String nomor_kartupengenal;
        String password;
        String no_sim;
        String usia;

        id_customer = sharedPreferences.getString(KEY_ID_CUSTOMER, null);
        nama_customer = sharedPreferences.getString(KEY_NAMA_CUSTOMER, null);
        alamat_customer = sharedPreferences.getString(KEY_ALAMAT_CUSTOMER, null);
        email_customer = sharedPreferences.getString(KEY_EMAIL, null);
        jenis_kelamin = sharedPreferences.getString(KEY_JENIS_KELAMIN, null);
        no_telp = sharedPreferences.getString(KEY_NO_TELP, null);
        status_berkas = sharedPreferences.getString(KEY_STATUS_BERKAS, null);
        asal_customer = sharedPreferences.getString(KEY_ASAL_CUSTOMER, null);
        nomor_kartupengenal = sharedPreferences.getString(KEY_NOMOR_KARTUPENGENAL, null);
        password = sharedPreferences.getString(KEY_PASSWORD, null);
        no_sim = sharedPreferences.getString(KEY_NO_SIM, null);
        usia = sharedPreferences.getString(KEY_USIA, null);

        return new Customer(id_customer,nama_customer,
                alamat_customer, email_customer,
                jenis_kelamin, no_telp, status_berkas,
                asal_customer, nomor_kartupengenal, password,
                no_sim, usia);
    }

    public boolean CheckLogin() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void Logout(){
        editor.clear();
        editor.commit();
    }
}
