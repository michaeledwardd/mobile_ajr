package com.michaeledward.mobileatmajayarental;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.michaeledward.mobileatmajayarental.preferences.PegawaiPreference;
import com.michaeledward.mobileatmajayarental.databinding.ActivityPegawaiBinding;

public class PegawaiActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityPegawaiBinding binding;
    private PegawaiPreference pegawaipref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pegawai);

        pegawaipref = new PegawaiPreference(this);

        binding.idPegawai.setText(pegawaipref.GetPegawaiNow().getId_pegawai());
        binding.idRole.setText(pegawaipref.GetPegawaiNow().getId_role());
        binding.namaPegawai.setText(pegawaipref.GetPegawaiNow().getNama_pegawai());
        binding.jkPegawai.setText(pegawaipref.GetPegawaiNow().getJenis_kelamin());
        binding.alamatPegawai.setText(pegawaipref.GetPegawaiNow().getAlamat());
        binding.emailPegawai.setText(pegawaipref.GetPegawaiNow().getEmail());
        binding.btnLogout.setOnClickListener(this);

        CheckLogin();
    }

    private void CheckLogin()
    {
        if(!pegawaipref.CheckLogin())
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "Selamat Datang Pegawai", Toast.LENGTH_SHORT).show();
        }
    }

    private void Logout()
    {
        pegawaipref.Logout();
        Intent move = new Intent(PegawaiActivity.this, LoginActivity.class);
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