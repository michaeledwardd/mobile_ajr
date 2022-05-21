package com.michaeledward.mobileatmajayarental.driver;

import static com.android.volley.Request.Method.GET;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.michaeledward.mobileatmajayarental.R;
import com.michaeledward.mobileatmajayarental.api.DriverApi;
import com.michaeledward.mobileatmajayarental.preferences.DriverPreference;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiwayatTransaksiDrv extends AppCompatActivity {
    public static final int LAUNCH_ADD_ACTIVITY = 123;
    private SwipeRefreshLayout srRiwayatTransaksi;
    private RiwayatTransaksiDrvAdapter adapter;
    private SearchView svRiwayatTransaksi;
    private LinearLayout layoutLoading;
    private RequestQueue queue;
    DriverPreference driverPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_transaksi_drv);
        driverPreference = new DriverPreference(this);

        // Pendeklarasian request queue
        queue = Volley.newRequestQueue(this);
        layoutLoading = findViewById(R.id.layout_loading);
        srRiwayatTransaksi = findViewById(R.id.sr_riwayattransaksi);
        svRiwayatTransaksi = findViewById(R.id.sv_riwayattransaksi);
        srRiwayatTransaksi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllRiwayatTransaksi();
            }
        });
        svRiwayatTransaksi.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        RecyclerView rvRiwayatTransaksi = findViewById(R.id.rv_riwayat_transaksi);
        adapter = new RiwayatTransaksiDrvAdapter(new ArrayList<>(), this);
        rvRiwayatTransaksi.setLayoutManager(new LinearLayoutManager(RiwayatTransaksiDrv.this,
                LinearLayoutManager.VERTICAL, false));
        rvRiwayatTransaksi.setAdapter(adapter);
        getAllRiwayatTransaksi();
    }

    @Override
    protected void onActivityResult
            (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_ADD_ACTIVITY && resultCode == Activity.RESULT_OK)
            getAllRiwayatTransaksi();
    }

    private void getAllRiwayatTransaksi() {
        srRiwayatTransaksi.setRefreshing(true);
        StringRequest stringRequest = new StringRequest(GET,
                DriverApi.SHOWTRANSAKSI_URL + driverPreference.GetIDDriver(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                RiwayatTransaksiDrvResponse riwayatTransaksiDrvResponse =
                        gson.fromJson(response, RiwayatTransaksiDrvResponse.class);
                adapter.setRiwayatTransaksiDrvList(riwayatTransaksiDrvResponse.getRiwayatTransaksiList());
                adapter.getFilter().filter(svRiwayatTransaksi.getQuery());
                Toast.makeText(RiwayatTransaksiDrv.this,
                        riwayatTransaksiDrvResponse.getMessage(), Toast.LENGTH_SHORT).show();
                srRiwayatTransaksi.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                srRiwayatTransaksi.setRefreshing(false);
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(RiwayatTransaksiDrv.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(RiwayatTransaksiDrv.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            // Menambahkan header pada request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    // Fungsi untuk menampilkan layout loading
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.GONE);
        }
    }
}