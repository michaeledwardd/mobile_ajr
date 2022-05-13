package com.michaeledward.mobileatmajayarental.customer;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.GET;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.michaeledward.mobileatmajayarental.R;
import com.michaeledward.mobileatmajayarental.api.CustomerApi;
import com.michaeledward.mobileatmajayarental.customer.Promo;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowPromo extends AppCompatActivity {
    public static final int LAUNCH_ADD_ACTIVITY = 123;
    private SwipeRefreshLayout srMahasiswa;
    private PromoAdapter adapter;
    private SearchView svMahasiswa;
    private LinearLayout layoutLoading;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_promo);

        // Pendeklarasian request queue
        queue = Volley.newRequestQueue(this);
        layoutLoading = findViewById(R.id.layout_loading);
        srMahasiswa = findViewById(R.id.sr_promo);
        svMahasiswa = findViewById(R.id.sv_promo);
        srMahasiswa.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllMahasiswa();
            }
        });
        svMahasiswa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        RecyclerView rvMahasiswa = findViewById(R.id.rv_mahasiswa);
        adapter = new PromoAdapter(new ArrayList<>(), this);
        rvMahasiswa.setLayoutManager(new LinearLayoutManager(ShowPromo.this,
                LinearLayoutManager.VERTICAL, false));
        rvMahasiswa.setAdapter(adapter);
        getAllMahasiswa();
    }

    @Override
    protected void onActivityResult
            (int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_ADD_ACTIVITY && resultCode == Activity.RESULT_OK)
            getAllMahasiswa();
    }

    private void getAllMahasiswa() {
        srMahasiswa.setRefreshing(true);
        StringRequest stringRequest = new StringRequest(GET,
                CustomerApi.SHOWPROMO_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                PromoResponse mahasiswaResponse =
                        gson.fromJson(response, PromoResponse.class);
                adapter.setPromoList(mahasiswaResponse.getPromoList());
                adapter.getFilter().filter(svMahasiswa.getQuery());
                Toast.makeText(ShowPromo.this,
                        mahasiswaResponse.getMessage(), Toast.LENGTH_SHORT).show();
                srMahasiswa.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                srMahasiswa.setRefreshing(false);
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(ShowPromo.this,
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ShowPromo.this, e.getMessage(),
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