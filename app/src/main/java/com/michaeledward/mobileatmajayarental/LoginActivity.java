package com.michaeledward.mobileatmajayarental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.michaeledward.mobileatmajayarental.api.UserApi;

import com.michaeledward.mobileatmajayarental.databinding.ActivityLoginBinding;
import com.michaeledward.mobileatmajayarental.entity.Customer;
import com.michaeledward.mobileatmajayarental.entity.CustomerFromJSON;
import com.michaeledward.mobileatmajayarental.entity.CustomerResponse;
import com.michaeledward.mobileatmajayarental.entity.Pegawai;
import com.michaeledward.mobileatmajayarental.entity.PegawaiFromJSON;
import com.michaeledward.mobileatmajayarental.entity.PegawaiResponse;
import com.michaeledward.mobileatmajayarental.entity.UserLogin;
import com.michaeledward.mobileatmajayarental.preferences.CustomerPreference;

import com.michaeledward.mobileatmajayarental.preferences.PegawaiPreference;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityLoginBinding binding;
    private UserLogin loginData;

    private CustomerPreference customerPreference;
    private PegawaiPreference pegawaiPreference;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        queue = Volley.newRequestQueue(this.getApplicationContext());

        loginData = new UserLogin();

        binding.setLogin(loginData);
        binding.btnLogin.setOnClickListener(this);

        customerPreference = new CustomerPreference(this);
        pegawaiPreference = new PegawaiPreference(this);
        CheckLogin();
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.btnLogin){
            if(CekKosong()){
                String email = loginData.getEmail();
                String password = loginData.getPassword();

                Login(new UserLogin(email, password));
            }
            else
            {
                Toast.makeText(this, "Inputan tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean CekKosong(){
        if(binding.inputLayoutEmail.getEditText().getText().toString().isEmpty()){
            return false;
        } else if(binding.inputLayoutPassword.getEditText().getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    private void CheckLogin(){
        if(customerPreference.CheckLogin()){
            Intent move = new Intent(this, CustomerActivity.class);
            startActivity(move);
            finish();
        }
        else if(pegawaiPreference.CheckLogin()){
            Intent move = new Intent(this, PegawaiActivity.class);
            startActivity(move);
            finish();
        }
    }

    public void Login(UserLogin user){
        setLoading(true);

        final StringRequest stringRequest = new StringRequest(POST, UserApi.LOGIN_URL, new Response.Listener<String>()  {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                CustomerResponse customerResponse = gson.fromJson(response, CustomerResponse.class);
                CustomerFromJSON customerLogin = customerResponse.getUser();

                PegawaiResponse pegawaiResponse = gson.fromJson(response, PegawaiResponse.class);
                PegawaiFromJSON pegawaiLogin = pegawaiResponse.getUser();

                if (customerLogin.getId_customer() != null) {
//                    Toast.makeText(LoginActivity.this, customerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);

                    Customer forPrefCustomer = new Customer(customerLogin.getId_customer(),
                            customerLogin.getEmail_customer(),
                            customerLogin.getNama_customer(),
                            customerLogin.getAlamat_customer(),
                            customerLogin.getJenis_kelamin(),
                            customerLogin.getNo_telp(),
                            customerLogin.getStatus_berkas(),
                            customerLogin.getAsal_customer(),
                            customerLogin.getNomor_kartupengenal(),
                            customerLogin.getPassword(),
                            customerLogin.getNo_sim(),
                            customerLogin.getUsia());

                    LoginActivity.this.customerPreference.SetLogin(forPrefCustomer);
                    CheckLogin();
                }
                //hanya bisa manager yang bisa login
                else if(pegawaiLogin.getId_pegawai() != 0 && pegawaiLogin.getId_role() != 1){
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);

                    Pegawai forPrefPegawai = new Pegawai(pegawaiLogin.getId_pegawai(),
                            pegawaiLogin.getId_role(),
                            pegawaiLogin.getNama_pegawai(),
                            pegawaiLogin.getFoto_pegawai(),
                            pegawaiLogin.getJenis_kelamin(),
                            pegawaiLogin.getAlamat(),
                            pegawaiLogin.getEmail(),
                            pegawaiLogin.getPassword(),
                            pegawaiLogin.getIs_aktif());
                    LoginActivity.this.pegawaiPreference.SetLogin(forPrefPegawai);
                    CheckLogin();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login hanya bisa manageer saja", Toast.LENGTH_SHORT).show();
                    setLoading(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setLoading(false);

                try{
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Log.d("responseBody :)", responseBody);

                    Toast.makeText(LoginActivity.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Log.d("responseBody Exception :)", e.getMessage());

                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String requestBody = gson.toJson(user);

                Log.d("public byte[] :)", requestBody);

                return requestBody.getBytes(StandardCharsets.UTF_8);
            }
        };
        queue.add(stringRequest);
    }

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            binding.layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            binding.layoutLoading.setVisibility(View.GONE);
        }
    }

}