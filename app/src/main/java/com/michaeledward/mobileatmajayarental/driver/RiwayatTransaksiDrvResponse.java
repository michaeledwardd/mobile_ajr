package com.michaeledward.mobileatmajayarental.driver;

import com.google.gson.annotations.SerializedName;
import com.michaeledward.mobileatmajayarental.customer.PromoFromJSON;

import java.util.List;

public class RiwayatTransaksiDrvResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<RiwayatTransaksiDrvFromJSON> riwayattransaksidrvList;

    public String getMessage(){
        return message;
    }

    public List<RiwayatTransaksiDrvFromJSON> getRiwayatTransaksiList() {
        return riwayattransaksidrvList;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
