package com.michaeledward.mobileatmajayarental.customer;

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
import com.michaeledward.mobileatmajayarental.api.CustomerApi;
import com.michaeledward.mobileatmajayarental.entity.Customer;
import com.michaeledward.mobileatmajayarental.preferences.CustomerPreference;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiwayatTransaksiCust extends AppCompatActivity {
    public static final int LAUNCH_ADD_ACTIVITY = 123;
    private SwipeRefreshLayout srRiwayatTransaksi;
    private RiwayatTransaksiAdapter adapter;
    private SearchView svRiwayatTransaksi;
    private LinearLayout layoutLoading;
    private RequestQueue queue;
    CustomerPreference customerPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_transaksi_cust);
        customerPreference = new CustomerPreference(this);

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
        adapter = new RiwayatTransaksiAdapter(new ArrayList<>(), this);
        rvRiwayatTransaksi.setLayoutManager(new LinearLayoutManager(RiwayatTransaksiCust.this,
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
                CustomerApi.SHOWTRANSAKSI_URL + customerPreference.GetIDCustomer(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                RiwayatTransaksiResponse riwayatTransaksiResponse =
                        gson.fromJson(response, RiwayatTransaksiResponse.class);
                adapter.setRiwayatTransaksiList(riwayatTransaksiResponse.getRiwayatTransaksiList());
                adapter.getFilter().filter(svRiwayatTransaksi.getQuery());
                Toast.makeText(RiwayatTransaksiCust.this,
                        riwayatTransaksiResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RiwayatTransaksiCust.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(RiwayatTransaksiCust.this, e.getMessage(),
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