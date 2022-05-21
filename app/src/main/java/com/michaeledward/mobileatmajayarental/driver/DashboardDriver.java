package com.michaeledward.mobileatmajayarental.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.michaeledward.mobileatmajayarental.DriverActivity;
import com.michaeledward.mobileatmajayarental.R;


public class DashboardDriver extends AppCompatActivity {

    private Button btnprofile;
    private Button btnriwayatTransaksiDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_driver);

        btnriwayatTransaksiDriver = findViewById(R.id.btnriwayatTransaksiDriver);
        btnprofile = findViewById(R.id.btnprofile);

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(DashboardDriver.this, DriverActivity.class);
                startActivity(moveHome);
            }
        });

        btnriwayatTransaksiDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(DashboardDriver.this, RiwayatTransaksiDrv.class);
                startActivity(moveHome);
            }
        });
    }
}