package com.michaeledward.mobileatmajayarental.customer;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<PromoFromJSON> promoList;

    public String getMessage(){
        return message;
    }

    public List<PromoFromJSON> getPromoList() {
        return promoList;
    }

    public void setMessage(String message){
        this.message = message;
    }


}
