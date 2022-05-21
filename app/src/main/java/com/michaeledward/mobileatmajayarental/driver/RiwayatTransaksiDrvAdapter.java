package com.michaeledward.mobileatmajayarental.driver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michaeledward.mobileatmajayarental.R;
import com.michaeledward.mobileatmajayarental.driver.RiwayatTransaksiDrvAdapter;
import com.michaeledward.mobileatmajayarental.driver.RiwayatTransaksiDrvFromJSON;
import com.michaeledward.mobileatmajayarental.driver.RiwayatTransaksiDrvResponse;

import java.util.ArrayList;
import java.util.List;

public class RiwayatTransaksiDrvAdapter extends RecyclerView.Adapter<RiwayatTransaksiDrvAdapter.ViewHolder> implements Filterable {

    private List<RiwayatTransaksiDrvFromJSON> riwayatTransaksiList, filteredRiwayatTransaksiList;
    private Context context;
    public RiwayatTransaksiDrvAdapter(List<RiwayatTransaksiDrvFromJSON> riwayatTransaksiList, Context context) {
        this.riwayatTransaksiList = riwayatTransaksiList;
        filteredRiwayatTransaksiList = new ArrayList<>(riwayatTransaksiList);
        this.context = context;
    }

    @NonNull
    @Override
    public RiwayatTransaksiDrvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_riwayattrans_drv, parent, false);
        return new RiwayatTransaksiDrvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTransaksiDrvAdapter.ViewHolder holder, int position) {
        RiwayatTransaksiDrvFromJSON riwayattransaksi = filteredRiwayatTransaksiList.get(position);

        holder.tvIDTransaksi.setText(riwayattransaksi.getId_transaksi());
        holder.tvStatusTransaksi.setText(riwayattransaksi.getStatus_transaksi());
        holder.tvNamaMobil.setText(riwayattransaksi.getNama_mobil());
        holder.tvNamaPegawai.setText(riwayattransaksi.getNama_pegawai());
        holder.tvNamaCustomer.setText(riwayattransaksi.getNama_customer());
        holder.tvInfo.setText(riwayattransaksi.getJenis_promo() + "-" + riwayattransaksi.getSubtotal_all());
    }

    @Override
    public int getItemCount() {
        return filteredRiwayatTransaksiList.size();
    }

    public void setRiwayatTransaksiDrvList(List<RiwayatTransaksiDrvFromJSON> riwayatTransaksiDrvList) {
        this.riwayatTransaksiList = riwayatTransaksiDrvList;
        filteredRiwayatTransaksiList = new ArrayList<>(riwayatTransaksiDrvList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<RiwayatTransaksiDrvFromJSON> filtered = new ArrayList<>();
                if (charSequenceString.isEmpty()) {
                    filtered.addAll(riwayatTransaksiList);
                } else {
                    for (RiwayatTransaksiDrvFromJSON riwayattransaksi : riwayatTransaksiList) {
                        if (riwayattransaksi.getNama_mobil().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(riwayattransaksi);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                filteredRiwayatTransaksiList.clear();
                filteredRiwayatTransaksiList.addAll((List<RiwayatTransaksiDrvFromJSON>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIDTransaksi, tvStatusTransaksi, tvNamaCustomer, tvNamaPegawai, tvNamaMobil, tvInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tv_info);
            tvIDTransaksi = itemView.findViewById(R.id.tv_id_transaksi);
            tvStatusTransaksi = itemView.findViewById(R.id.tv_status_transaksi);
            tvNamaCustomer = itemView.findViewById(R.id.tv_nama_customer);
            tvNamaPegawai = itemView.findViewById(R.id.tv_nama_pegawai);
            tvNamaMobil = itemView.findViewById(R.id.tv_nama_mobil);
        }
    }


}


