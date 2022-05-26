package com.michaeledward.mobileatmajayarental.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.michaeledward.mobileatmajayarental.CustomerActivity;
import com.michaeledward.mobileatmajayarental.R;

public class DashboardCustomer extends AppCompatActivity {
    private FloatingActionButton btnshowmobil;
    private FloatingActionButton btnshowpromo;
    private FloatingActionButton btnprofile;
    private FloatingActionButton btnriwayatTransaksiCustomer;


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