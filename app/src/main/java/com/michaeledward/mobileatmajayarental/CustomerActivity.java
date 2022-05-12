package com.michaeledward.mobileatmajayarental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.michaeledward.mobileatmajayarental.databinding.ActivityCustomerBinding;
import com.michaeledward.mobileatmajayarental.entity.Customer;
import com.michaeledward.mobileatmajayarental.preferences.CustomerPreference;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityCustomerBinding binding;
    private CustomerPreference customerPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer);

        customerPreference = new CustomerPreference(this);

        binding.idCustomer.setText(customerPreference.GetCustomerNow().getId_customer());
        binding.namaCustomer.setText(customerPreference.GetCustomerNow().getNama_customer());
        binding.alamatCustomer.setText(customerPreference.GetCustomerNow().getAlamat_customer());
        binding.emailCustomer.setText(customerPreference.GetCustomerNow().getEmail_customer());
        binding.jenisKelamin.setText(customerPreference.GetCustomerNow().getJenis_kelamin());
        binding.asalCustomer.setText(customerPreference.GetCustomerNow().getAsal_customer());
        binding.noSim.setText(customerPreference.GetCustomerNow().getNo_sim());
        binding.nomorKartupengenal.setText(customerPreference.GetCustomerNow().getNomor_kartupengenal());
        binding.usia.setText(customerPreference.GetCustomerNow().getUsia());

        binding.btnLogout.setOnClickListener(this);

        CheckLogin();
    }

    private void CheckLogin()
    {
        if(!customerPreference.CheckLogin())
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "Selamat Datang Customer", Toast.LENGTH_SHORT).show();
        }
    }

    private void Logout()
    {
        customerPreference.Logout();
        Intent move = new Intent(CustomerActivity.this, LoginActivity.class);
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