package com.michaeledward.mobileatmajayarental.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.michaeledward.mobileatmajayarental.R;

public class DashboardManager extends AppCompatActivity {
    private Button btnlaporan1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_manager);

        btnlaporan1 = findViewById(R.id.btnlaporan1);


        btnlaporan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(DashboardManager.this, InputTanggal.class);
                moveHome.putExtra("laporan","LaporanPenyewaanMobil");
                startActivity(moveHome);
            }
        });

    }


}