package com.michaeledward.mobileatmajayarental.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.michaeledward.mobileatmajayarental.CustomerActivity;
import com.michaeledward.mobileatmajayarental.R;

public class DashboardCustomer extends AppCompatActivity {
    private Button btnshowmobil;
    private Button btnshowpromo;
    private Button btnprofile;
    private Button btnriwayatTransaksiCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_customer);

        btnshowmobil = findViewById(R.id.btnshowmobil);
        btnshowpromo = findViewById(R.id.btnshowpromo);
        btnprofile = findViewById(R.id.btnprofile);
        btnriwayatTransaksiCustomer = findViewById(R.id.btnriwayatTransaksiCustomer);

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(DashboardCustomer.this, CustomerActivity.class);
                startActivity(moveHome);
            }
        });

        btnshowpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(DashboardCustomer.this, ShowPromo.class);
                startActivity(moveHome);
            }
        });

        btnshowmobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(DashboardCustomer.this, ShowMobil.class);
                startActivity(moveHome);
            }
        });

        btnriwayatTransaksiCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(DashboardCustomer.this, RiwayatTransaksiCust.class);
                startActivity(moveHome);
            }
        });
    }


}