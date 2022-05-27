package com.michaeledward.mobileatmajayarental.manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.michaeledward.mobileatmajayarental.PegawaiActivity;
import com.michaeledward.mobileatmajayarental.R;
import com.michaeledward.mobileatmajayarental.api.ManagerApi;
import com.michaeledward.mobileatmajayarental.customer.DashboardCustomer;
import com.michaeledward.mobileatmajayarental.customer.ShowPromo;
import com.michaeledward.mobileatmajayarental.manager.laporandetailpendapatan.LaporanDetailPendapatanFromJSON;
import com.michaeledward.mobileatmajayarental.manager.laporandetailpendapatan.LaporanDetailPendapatanResponse;
import com.michaeledward.mobileatmajayarental.manager.laporanpenyewaanmobil.LaporanPenyewaanFromJSON;
import com.michaeledward.mobileatmajayarental.manager.laporanpenyewaanmobil.LaporanPenyewaanResponse;
import com.michaeledward.mobileatmajayarental.manager.laporantopcustomer.LaporanTopCustomerFromJSON;
import com.michaeledward.mobileatmajayarental.manager.laporantopcustomer.LaporanTopCustomerResponse;
import com.michaeledward.mobileatmajayarental.manager.laporantopdriverbyrating.LaporanDriverbyRatingFromJSON;
import com.michaeledward.mobileatmajayarental.manager.laporantopdriverbyrating.LaporanDriverbyRatingResponse;
import com.michaeledward.mobileatmajayarental.manager.laporantopdriverbytransaksi.LaporanTopDriverbyTransaksiFromJSON;
import com.michaeledward.mobileatmajayarental.manager.laporantopdriverbytransaksi.LaporanTopDriverbyTransaksiResponse;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DashboardManager extends AppCompatActivity {
    private Button btnlaporan1, btnlaporan2, btnlaporan3, btnlaporan4, btnlaporan5, btnprofile;
    private EditText tanggalawal, tanggalakhir;
    private RequestQueue queue;

    final Calendar calendarku = Calendar.getInstance();
    final Calendar calendarku2 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_manager);
        queue = Volley.newRequestQueue(this.getApplicationContext());
        btnlaporan1 = findViewById(R.id.btnlaporan1);
        btnlaporan2 = findViewById(R.id.btnlaporan2);
        btnlaporan3 = findViewById(R.id.btnlaporan3);
        btnlaporan4 = findViewById(R.id.btnlaporan4);
        btnlaporan5 = findViewById(R.id.btnlaporan5);
        btnprofile = findViewById(R.id.btnprofile);
        tanggalawal = findViewById(R.id.inputtanggalawal);
        tanggalakhir = findViewById(R.id.inputtanggalakhir);

        DatePickerDialog.OnDateSetListener dateTanggalAwal = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendarku.set(Calendar.YEAR, year);
                calendarku.set(Calendar.MONTH,month);
                calendarku.set(Calendar.DAY_OF_MONTH,day);

                String myFormat="yyyy-MM-dd";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                tanggalawal.setText(dateFormat.format(calendarku.getTime()));
            }
        };

        tanggalawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DashboardManager.this, dateTanggalAwal, calendarku.get(Calendar.YEAR),calendarku.get(Calendar.MONTH),calendarku.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener dateTanggalAkhir = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendarku2.set(Calendar.YEAR, year);
                calendarku2.set(Calendar.MONTH,month);
                calendarku2.set(Calendar.DAY_OF_MONTH,day);

                String myFormat="yyyy-MM-dd";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                tanggalakhir.setText(dateFormat.format(calendarku2.getTime()));
            }
        };

        tanggalakhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DashboardManager.this, dateTanggalAkhir, calendarku2.get(Calendar.YEAR),calendarku2.get(Calendar.MONTH),calendarku2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnlaporan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal_awal, tanggal_akhir;

                tanggal_awal = tanggalawal.getText().toString();
                tanggal_akhir = tanggalakhir.getText().toString();
                getResponseLaporanPenyewaanMobil(tanggal_awal, tanggal_akhir);

            }
        });

        btnlaporan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal_awal, tanggal_akhir;

                tanggal_awal = tanggalawal.getText().toString();
                tanggal_akhir = tanggalakhir.getText().toString();
                getResponseLaporanDetailPendapatan(tanggal_awal, tanggal_akhir);
            }
        });

        btnlaporan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal_awal, tanggal_akhir;

                tanggal_awal = tanggalawal.getText().toString();
                tanggal_akhir = tanggalakhir.getText().toString();
                getResponseLaporanTopDriverbyTransaksi(tanggal_awal, tanggal_akhir);
            }
        });

        btnlaporan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal_awal, tanggal_akhir;

                tanggal_awal = tanggalawal.getText().toString();
                tanggal_akhir = tanggalakhir.getText().toString();
                getResponseLaporanTopDriverbyRating(tanggal_awal, tanggal_akhir);
            }
        });

        btnlaporan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal_awal, tanggal_akhir;

                tanggal_awal = tanggalawal.getText().toString();
                tanggal_akhir = tanggalakhir.getText().toString();
                getResponseLaporanTopCustomer(tanggal_awal, tanggal_akhir);
            }
        });

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveHome = new Intent(DashboardManager.this, PegawaiActivity.class);
                startActivity(moveHome);
            }
        });
    }

    private void getResponseLaporanTopCustomer(String tanggal_awal, String tanggal_akhir) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, ManagerApi.LAPORANTOPCUSTOMER + tanggal_awal + '/' + tanggal_akhir, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                LaporanTopCustomerResponse customerPDFResponse = gson.fromJson(response, LaporanTopCustomerResponse.class);
                List<LaporanTopCustomerFromJSON> customerPDFModelList = customerPDFResponse.getLaporanTopCustomerFromJSONList();
                try {
                    cetakPDfLaporanTopCustomer(customerPDFModelList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                Toast.makeText(DashboardManager.this, "Shown", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DashboardManager.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DashboardManager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    private void getResponseLaporanTopDriverbyRating(String tanggal_awal, String tanggal_akhir) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, ManagerApi.LAPORANTOPDRIVERBYRATING + tanggal_awal + '/' + tanggal_akhir, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                LaporanDriverbyRatingResponse customerPDFResponse = gson.fromJson(response, LaporanDriverbyRatingResponse.class);
                List<LaporanDriverbyRatingFromJSON> customerPDFModelList = customerPDFResponse.getLaporanDriverbyRatingFromJSONList();
                try {
                    cetakPDfLaporanTopDriverbyRating(customerPDFModelList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                Toast.makeText(DashboardManager.this, "Shown", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DashboardManager.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DashboardManager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    private void getResponseLaporanTopDriverbyTransaksi(String tanggal_awal, String tanggal_akhir) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, ManagerApi.LAPORANTOPDRIVERBYTRANSAKSI + tanggal_awal + '/' + tanggal_akhir, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                LaporanTopDriverbyTransaksiResponse customerPDFResponse = gson.fromJson(response, LaporanTopDriverbyTransaksiResponse.class);
                List<LaporanTopDriverbyTransaksiFromJSON> customerPDFModelList = customerPDFResponse.getLaporanTopDriverbyTransaksiFromJSONList();
                try {
                    cetakPDfLaporanTopDriverbyTransaksi(customerPDFModelList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                Toast.makeText(DashboardManager.this, "Shown", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DashboardManager.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DashboardManager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    public void getResponseLaporanPenyewaanMobil(String tanggal_awal, String tanggal_akhir){
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, ManagerApi.LAPORANPENYEWAAN_URL + tanggal_awal + '/' + tanggal_akhir, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                LaporanPenyewaanResponse customerPDFResponse = gson.fromJson(response, LaporanPenyewaanResponse.class);
                List<LaporanPenyewaanFromJSON> customerPDFModelList = customerPDFResponse.getLaporanPenyewaanFromJSONList();
                try {
                    cetakPDfLaporanPenyewaan(customerPDFModelList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                Toast.makeText(DashboardManager.this, "Shown", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DashboardManager.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DashboardManager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    public void getResponseLaporanDetailPendapatan(String tanggal_awal, String tanggal_akhir){
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, ManagerApi.LAPORANDETAILPENDAPATAN_URL + tanggal_awal + '/' + tanggal_akhir, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                LaporanDetailPendapatanResponse customerPDFResponse = gson.fromJson(response, LaporanDetailPendapatanResponse.class);
                List<LaporanDetailPendapatanFromJSON> customerPDFModelList = customerPDFResponse.getLaporanDetailPendapatanFromJSONList();
                try {
                    cetakPDfLaporanDetailPendapatan(customerPDFModelList);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                Toast.makeText(DashboardManager.this, "Shown", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(DashboardManager.this, errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(DashboardManager.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    private void cetakPDfLaporanTopCustomer(List<LaporanTopCustomerFromJSON> laporanTopCustomerFromJSONList) throws DocumentException, FileNotFoundException{
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("LAPORAN TOP CUSTOMER \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);
        Paragraph kepada = new Paragraph(
                "Kepada Yth: \n" + "Manager Atma Jaya Rental" + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(kepada);
        tables.addCell(cellSupplier);
        Paragraph NomorTanggal = new Paragraph(
                "Kode Laporan: " + "LAP-AJR-TopCustomer" + "\n\n" +
                        "Tanggal : " + new SimpleDateFormat("dd/MM/yyyy",
                        Locale.getDefault()).format(currentTime) + "\n",
                new
                        com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                        com.itextpdf.text.Font.NORMAL, BaseColor.BLACK));
        NomorTanggal.setPaddingTop(5);
        tables.addCell(NomorTanggal);
        document.add(tables);
        com.itextpdf.text.Font f = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
        Paragraph Pembuka = new Paragraph("\nBerikut merupakan laporan top customer: \n\n", f);
        Pembuka.setIndentationLeft(20);
        document.add(Pembuka);
        PdfPTable tableHeader = new PdfPTable(new float[]{2, 2});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("Nama Customer"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.PINK);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{2, 2});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        // masukan data pegawai jadi baris
        for (LaporanTopCustomerFromJSON P : laporanTopCustomerFromJSONList) {
            tableData.addCell(P.getNama_customer());

            tableData.addCell(String.valueOf(P.getJumlah_transaksi()));


        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_RIGHT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakPDfLaporanTopDriverbyRating(List<LaporanDriverbyRatingFromJSON> laporanDriverbyRatingFromJSONList) throws DocumentException, FileNotFoundException{
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("LAPORAN TOP DRIVER BY RATING \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);
        Paragraph kepada = new Paragraph(
                "Kepada Yth: \n" + "Manager Atma Jaya Rental" + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(kepada);
        tables.addCell(cellSupplier);
        Paragraph NomorTanggal = new Paragraph(
                "Kode Laporan: " + "LAP-AJR-TopDriverbyRating" + "\n\n" +
                        "Tanggal : " + new SimpleDateFormat("dd/MM/yyyy",
                        Locale.getDefault()).format(currentTime) + "\n",
                new
                        com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                        com.itextpdf.text.Font.NORMAL, BaseColor.BLACK));
        NomorTanggal.setPaddingTop(5);
        tables.addCell(NomorTanggal);
        document.add(tables);
        com.itextpdf.text.Font f = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
        Paragraph Pembuka = new Paragraph("\nBerikut merupakan laporan top driver by rating: \n\n", f);
        Pembuka.setIndentationLeft(20);
        document.add(Pembuka);
        PdfPTable tableHeader = new PdfPTable(new float[]{4, 4, 4, 4});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("ID Driver"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Nama Driver"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);
        PdfPCell h4 = new PdfPCell(new Phrase("Rerata Rating"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        tableHeader.addCell(h4);
//        tableHeader.addCell(h5);

        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.PINK);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{4, 4, 4, 4});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        // masukan data pegawai jadi baris
        for (LaporanDriverbyRatingFromJSON P : laporanDriverbyRatingFromJSONList) {
            tableData.addCell(P.getId_driver());
            tableData.addCell(P.getNama_driver());
            tableData.addCell(String.valueOf(P.getJumlah_transaksi()));
            tableData.addCell(String.valueOf(P.getRerata_rating_drv()));

        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_RIGHT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakPDfLaporanTopDriverbyTransaksi(List<LaporanTopDriverbyTransaksiFromJSON> laporanTopDriverbyTransaksiFromJSONList) throws DocumentException, FileNotFoundException{
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("LAPORAN TOP DRIVER BY TRANSAKSI \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);
        Paragraph kepada = new Paragraph(
                "Kepada Yth: \n" + "Manager Atma Jaya Rental" + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(kepada);
        tables.addCell(cellSupplier);
        Paragraph NomorTanggal = new Paragraph(
                "Kode Laporan: " + "LAP1-AJR-TopDriverbyTransaksi" + "\n\n" +
                        "Tanggal : " + new SimpleDateFormat("dd/MM/yyyy",
                        Locale.getDefault()).format(currentTime) + "\n",
                new
                        com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                        com.itextpdf.text.Font.NORMAL, BaseColor.BLACK));
        NomorTanggal.setPaddingTop(5);
        tables.addCell(NomorTanggal);
        document.add(tables);
        com.itextpdf.text.Font f = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
        Paragraph Pembuka = new Paragraph("\nBerikut merupakan laporan top driver by transaksi: \n\n", f);
        Pembuka.setIndentationLeft(20);
        document.add(Pembuka);
        PdfPTable tableHeader = new PdfPTable(new float[]{3, 3, 3});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("ID Driver"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Nama Driver"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);

        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);

//        tableHeader.addCell(h5);

        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.PINK);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{3, 3, 3});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        // masukan data pegawai jadi baris
        for (LaporanTopDriverbyTransaksiFromJSON P : laporanTopDriverbyTransaksiFromJSONList) {
            tableData.addCell(P.getId_driver());
            tableData.addCell(P.getNama_driver());

            tableData.addCell(String.valueOf(P.getJumlah_transaksi()));


        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_RIGHT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakPDfLaporanDetailPendapatan(List<LaporanDetailPendapatanFromJSON> laporanDetailPendapatanFromJSONList) throws DocumentException, FileNotFoundException{
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("LAPORAN DETAIL PENDAPATAN \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);
        Paragraph kepada = new Paragraph(
                "Kepada Yth: \n" + "Manager Atma Jaya Rental" + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(kepada);
        tables.addCell(cellSupplier);
        Paragraph NomorTanggal = new Paragraph(
                "Kode Laporan: " + "LAP1-AJR-DetailPendapatan" + "\n\n" +
                        "Tanggal : " + new SimpleDateFormat("dd/MM/yyyy",
                        Locale.getDefault()).format(currentTime) + "\n",
                new
                        com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                        com.itextpdf.text.Font.NORMAL, BaseColor.BLACK));
        NomorTanggal.setPaddingTop(5);
        tables.addCell(NomorTanggal);
        document.add(tables);
        com.itextpdf.text.Font f = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
        Paragraph Pembuka = new Paragraph("\nBerikut merupakan laporan detail pendapatan: \n\n", f);
        Pembuka.setIndentationLeft(20);
        document.add(Pembuka);
        PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5, 5, 5});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("Nama Customer"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Nama Mobil"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h3 = new PdfPCell(new Phrase("Jenis Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);
        PdfPCell h4 = new PdfPCell(new Phrase("Jumlah Peminjaman"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);
        PdfPCell h5 = new PdfPCell(new Phrase("Pendapatan"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        tableHeader.addCell(h4);
        tableHeader.addCell(h5);
//        tableHeader.addCell(h5);

        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.PINK);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5, 5, 5});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        // masukan data pegawai jadi baris
        for (LaporanDetailPendapatanFromJSON P : laporanDetailPendapatanFromJSONList) {
            tableData.addCell(P.getNama_customer());
            tableData.addCell(P.getNama_mobil());
            tableData.addCell(P.getJenis_transaksi());
            tableData.addCell(String.valueOf(P.getJumlah_peminjaman()));
            tableData.addCell(String.valueOf(P.getPendapatan()));

        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_RIGHT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakPDfLaporanPenyewaan(List<LaporanPenyewaanFromJSON> laporanPenyewaanFromJSONList) throws DocumentException, FileNotFoundException {

        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Date currentTime = Calendar.getInstance().getTime();
        String pdfName = currentTime.getTime() + ".pdf";
        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document = new
                com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // bagian header
        Paragraph judul = new Paragraph("LAPORAN PENYEWAAN MOBIL \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16,
                        Font.BOLD, BaseColor.BLACK));
        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        // Buat tabel
        PdfPTable tables = new PdfPTable(new float[]{16, 8});

        // Settingan ukuran tabel
        tables.getDefaultCell().setFixedHeight(50);
        tables.setTotalWidth(PageSize.A4.getWidth());
        tables.setWidthPercentage(100);
        tables.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        PdfPCell cellSupplier = new PdfPCell();
        cellSupplier.setPaddingLeft(20);
        cellSupplier.setPaddingBottom(10);
        cellSupplier.setBorder(Rectangle.NO_BORDER);
        Paragraph kepada = new Paragraph(
                "Kepada Yth: \n" + "Manager Atma Jaya Rental" + "\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10,
                        Font.NORMAL, BaseColor.BLACK));
        cellSupplier.addElement(kepada);
        tables.addCell(cellSupplier);
        Paragraph NomorTanggal = new Paragraph(
                "Kode Laporan: " + "LAP1-AJR-PenyewaanMobil" + "\n\n" +
                        "Tanggal : " + new SimpleDateFormat("dd/MM/yyyy",
                        Locale.getDefault()).format(currentTime) + "\n",
                new
                        com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                        com.itextpdf.text.Font.NORMAL, BaseColor.BLACK));
        NomorTanggal.setPaddingTop(5);
        tables.addCell(NomorTanggal);
        document.add(tables);
        com.itextpdf.text.Font f = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
        Paragraph Pembuka = new Paragraph("\nBerikut merupakan laporan penyewaan ini: \n\n", f);
        Pembuka.setIndentationLeft(20);
        document.add(Pembuka);
        PdfPTable tableHeader = new PdfPTable(new float[]{4, 4, 4, 4});

        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        tableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        // Setup Column
        PdfPCell h1 = new PdfPCell(new Phrase("Tipe Mobil"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);
        PdfPCell h2 = new PdfPCell(new Phrase("Nama Mobil"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);
        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Pendapatan"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);
        PdfPCell h4 = new PdfPCell(new Phrase("Pendapatan"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);
        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        tableHeader.addCell(h4);
//        tableHeader.addCell(h5);

        // Beri warna untuk kolumn
        for (PdfPCell cells : tableHeader.getRow(0).getCells()) {
            cells.setBackgroundColor(BaseColor.PINK);
        }
        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{4, 4, 4, 4});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        // masukan data pegawai jadi baris
        for (LaporanPenyewaanFromJSON P : laporanPenyewaanFromJSONList) {
            tableData.addCell(P.getTipe_mobil());
            tableData.addCell(P.getNama_mobil());
            tableData.addCell(String.valueOf(P.getJumlah_peminjaman()));
            tableData.addCell(String.valueOf(P.getPendapatan()));

        }
        document.add(tableData);
        com.itextpdf.text.Font h = new
                com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 10,
                com.itextpdf.text.Font.NORMAL);
        String tglDicetak = currentTime.toLocaleString();
        Paragraph P = new Paragraph("\nDicetak tanggal " + tglDicetak, h);
        P.setAlignment(Element.ALIGN_RIGHT);
        document.add(P);
        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void previewPdf(File pdfFile) {
//        PackageManager packageManager = getPackageManager();
//        Intent testIntent = new Intent(Intent.ACTION_VIEW);
//        testIntent.setType("application/pdf");
//        List<ResolveInfo> list =
//                packageManager.queryIntentActivities(testIntent,
//                        PackageManager.MATCH_DEFAULT_ONLY);
//        if (list.size() > 0) {
            Uri uri;
            uri = FileProvider.getUriForFile(this, getPackageName() +
                            ".provider",
                    pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(uri, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            this.grantUriPermission(getPackageName(), uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                            Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(pdfIntent);
//        }
    }
}