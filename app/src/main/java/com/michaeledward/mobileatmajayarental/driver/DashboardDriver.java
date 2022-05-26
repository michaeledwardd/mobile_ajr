package com.michaeledward.mobileatmajayarental.driver;

import static com.android.volley.Request.Method.PUT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.michaeledward.mobileatmajayarental.DriverActivity;
import com.michaeledward.mobileatmajayarental.R;
import com.michaeledward.mobileatmajayarental.api.DriverApi;
import com.michaeledward.mobileatmajayarental.entity.Driver;
import com.michaeledward.mobileatmajayarental.entity.DriverFromJSON;
import com.michaeledward.mobileatmajayarental.entity.DriverResponse;
import com.michaeledward.mobileatmajayarental.preferences.DriverPreference;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class DashboardDriver extends AppCompatActivity {

    private Button btnprofile;
    private Button btnriwayatTransaksiDriver, btntersedia, btntidaktersedia, btneditprofile;
    DriverPreference driverPreference;
    Driver driver;
    DriverResponse driverResponse;
    private RequestQueue queue;
    DriverFromJSON driverLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_driver);
        queue = Volley.newRequestQueue(this.getApplicationContext());
        btnriwayatTransaksiDriver = findViewById(R.id.btnriwayatTransaksiDriver);
        btntersedia = findViewById(R.id.btntersedia);
        btntidaktersedia = findViewById(R.id.btntidaktersedia);
        btneditprofile = findViewById(R.id.btneditprofile);
        btnprofile = findViewById(R.id.btnprofile);

        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(DashboardDriver.this, EditDriver.class);
                startActivity(moveHome);
            }
        });

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

        btntersedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "tersedia";
                driverPreference = new DriverPreference(DashboardDriver.this);
                driver = new Driver(status);

                StringRequest stringRequest = new StringRequest(PUT, DriverApi.UPDATESTATUSDRIVER_URL + driverPreference.GetIDDriver(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                        Toast.makeText(DashboardDriver.this, driverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                            JSONObject errors = new JSONObject(responseBody);
                            Toast.makeText(DashboardDriver.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(DashboardDriver.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
        });

        btntidaktersedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "tidak tersedia";

                driverPreference = new DriverPreference(DashboardDriver.this);
                driver = new Driver(status);

                StringRequest stringRequest = new StringRequest(PUT, DriverApi.UPDATESTATUSDRIVER_URL + driverPreference.GetIDDriver(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DriverResponse driverResponse = gson.fromJson(response, DriverResponse.class);
                        Toast.makeText(DashboardDriver.this, driverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                            JSONObject errors = new JSONObject(responseBody);
                            Toast.makeText(DashboardDriver.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(DashboardDriver.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
        });
    }


}