package com.michaeledward.mobileatmajayarental.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.michaeledward.mobileatmajayarental.R;

public class InputTanggal extends AppCompatActivity {
    private static final String[] BULAN_LIST = new String[]{"Januari", "Februari", "Maret", "April", "Mei","Juni",
    "Juli","Agustus","September","Oktober","November","Desember"};
    private static final String[] TAHUN_LIST = new String[110];
    private AutoCompleteTextView edBulan, edTahun;
    private Button btnconfirm, btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tanggal);

        for(int i=0; i<110; i++){
            TAHUN_LIST[i] = String.valueOf(i+1990);
        }
        btnconfirm = findViewById(R.id.btnconfirm);
        btncancel = findViewById(R.id.btn_cancel);
        edBulan = findViewById(R.id.ed_bulan);
        edTahun = findViewById(R.id.ed_tahun);

        ArrayAdapter<String> adapterBulan =
                new ArrayAdapter<>(this, R.layout.item_list, BULAN_LIST);
        edBulan.setAdapter(adapterBulan);

        ArrayAdapter<String> adapterTahun =
                new ArrayAdapter<>(this, R.layout.item_list, TAHUN_LIST);
        edTahun.setAdapter(adapterTahun);

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}