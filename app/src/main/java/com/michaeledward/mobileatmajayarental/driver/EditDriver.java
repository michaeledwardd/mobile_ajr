package com.michaeledward.mobileatmajayarental.driver;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.PUT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.michaeledward.mobileatmajayarental.PegawaiActivity;
import com.michaeledward.mobileatmajayarental.R;
import com.michaeledward.mobileatmajayarental.api.DriverApi;
import com.michaeledward.mobileatmajayarental.entity.Driver;
import com.michaeledward.mobileatmajayarental.entity.DriverFromJSON;
import com.michaeledward.mobileatmajayarental.entity.DriverResponse;
import com.michaeledward.mobileatmajayarental.manager.DashboardManager;
import com.michaeledward.mobileatmajayarental.preferences.DriverPreference;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class EditDriver extends AppCompatActivity {
    DriverPreference driverPreference;
    Driver driver;
    DriverResponse driverResponse;
    private RequestQueue queue;
    DriverFromJSON driverLogin;
    private EditText etNama, etalamat, etNomorTelepon, etbiayadriver;
    private Button btnSave, btnCancel;
    private static final String[] JENIS_KELAMIN = new String[]{"laki laki", "perempuan"};
    private AutoCompleteTextView edJenisKelamin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_driver);
        queue = Volley.newRequestQueue(this.getApplicationContext());

        etNama = findViewById(R.id.et_nama);
        etNomorTelepon = findViewById(R.id.et_nomor_telepon);
        etbiayadriver = findViewById(R.id.et_biaya_driver);
        etalamat = findViewById(R.id.et_alamat);
        edJenisKelamin = findViewById(R.id.ed_jenis_kelamin);

        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);
        ArrayAdapter<String> adapterJenisKelamin = new ArrayAdapter<>(this, R.layout.item_list, JENIS_KELAMIN);
        edJenisKelamin.setAdapter(adapterJenisKelamin);
        GetbyID();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(EditDriver.this, DashboardDriver.class);
                startActivity(moveHome);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDriver();
                Intent moveHome = new Intent(EditDriver.this, DashboardDriver.class);
                startActivity(moveHome);
            }
        });
    }

    private void GetbyID() {
        driverPreference = new DriverPreference(EditDriver.this);

        StringRequest stringRequest = new StringRequest(GET, DriverApi.SHOWDRIVER + driverPreference.GetIDDriver(), new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                DriverFromJSON driverModel = driverResponse.getUser();

                etNama.setText(driverModel.getNama_driver());
                etalamat.setText(driverModel.getAlamat());
                etNomorTelepon.setText(driverModel.getNo_telp());
                etbiayadriver.setText(driverModel.getBiaya_sewa_driver());
                edJenisKelamin.setText(driverModel.getJenis_kelamin(), false);
                Toast.makeText(EditDriver.this, "Shown", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(EditDriver.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(EditDriver.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
            // Menambahkan request body berupa object driver
            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                     /* Serialisasi data dari java object driverResponse
                     menjadi JSON string menggunakan Gson */
                String requestBody = gson.toJson(driver);
                return requestBody.getBytes(StandardCharsets.UTF_8);
            }
            // Mendeklarasikan content type dari request body yang ditambahkan
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queue.add(stringRequest);
    }

    private void updateDriver() {
        driverPreference = new DriverPreference(EditDriver.this);
        driver = new Driver(
                etNama.getText().toString(), etalamat.getText().toString(), etNomorTelepon.getText().toString(),
                etbiayadriver.getText().toString(), edJenisKelamin.getText().toString()
        );
        StringRequest stringRequest = new StringRequest(PUT, DriverApi.UPDATEDRIVER_URL + driverPreference.GetIDDriver(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                Toast.makeText(EditDriver.this, driverResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(EditDriver.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(EditDriver.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
            // Menambahkan request body berupa object driver
            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                     /* Serialisasi data dari java object driverResponse
                     menjadi JSON string menggunakan Gson */
                String requestBody = gson.toJson(driver);
                return requestBody.getBytes(StandardCharsets.UTF_8);
            }
            // Mendeklarasikan content type dari request body yang ditambahkan
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queue.add(stringRequest);
    }
}