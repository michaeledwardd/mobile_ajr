package com.michaeledward.mobileatmajayarental;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.michaeledward.mobileatmajayarental.databinding.ActivityDriverBinding;
import com.michaeledward.mobileatmajayarental.preferences.DriverPreference;

public class DriverActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityDriverBinding binding;
    private DriverPreference driverpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_driver);

        driverpref = new DriverPreference(this);

        binding.idDriver.setText(driverpref.GetDriverNow().getId_driver());
        binding.namaDriver.setText(driverpref.GetDriverNow().getNama_driver());
        binding.jenisKelaminDriver.setText(driverpref.GetDriverNow().getJenis_kelamin());
        binding.alamatDriver.setText(driverpref.GetDriverNow().getAlamat());
        binding.emailDriver.setText(driverpref.GetDriverNow().getEmail_driver());
        binding.statusTersedia.setText(driverpref.GetDriverNow().getStatus_tersedia());
        binding.biayaSewaDriver.setText(driverpref.GetDriverNow().getBiaya_sewa_driver());
        binding.noTelpDriver.setText(driverpref.GetDriverNow().getNo_telp());
        binding.rerataRating.setText(driverpref.GetDriverNow().getRerata_rating());

        binding.btnLogout.setOnClickListener(this);
        CheckLogin();
    }

    private void CheckLogin()
    {
        if(!driverpref.CheckLogin())
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "Selamat Datang Driver", Toast.LENGTH_SHORT).show();
        }
    }

    private void Logout()
    {
        driverpref.Logout();
        Intent move = new Intent(DriverActivity.this, LoginActivity.class);
        startActivity(move);
        Toast.makeText(this, "Logout, sampai jumpa kembali..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogout){
            Logout();
        }
    }

}