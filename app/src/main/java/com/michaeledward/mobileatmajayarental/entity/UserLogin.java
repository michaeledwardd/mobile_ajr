package com.michaeledward.mobileatmajayarental.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.michaeledward.mobileatmajayarental.BR;

public class UserLogin extends BaseObservable{
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;


    public UserLogin(){

    }

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
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
}
