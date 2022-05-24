package com.michaeledward.mobileatmajayarental.api;


public class ManagerApi {
    public static final String BASE_URL = "http://192.168.100.8:8000/";
    public static final String API_URL = BASE_URL + "api/";

    public static final String LAPORANPENYEWAAN_URL = API_URL + "cetakLaporanPenyewaanMobil/";
    public static final String LAPORANDETAILPENDAPATAN_URL = API_URL + "cetakLaporanDetailPendapatan/";
    public static final String LAPORANTOPDRIVERBYTRANSAKSI = API_URL + "cetakLaporanTopDriver/";
    public static final String LAPORANTOPDRIVERBYRATING = API_URL + "cetakTopDriverbyRating/";
    public static final String LAPORANTOPCUSTOMER = API_URL + "cetakLaporanTopCustomer/";
}
