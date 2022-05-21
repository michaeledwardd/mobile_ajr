package com.michaeledward.mobileatmajayarental.customer;

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

import java.util.ArrayList;
import java.util.List;

public class RiwayatTransaksiAdapter extends RecyclerView.Adapter<RiwayatTransaksiAdapter.ViewHolder> implements Filterable {

    private List<RiwayatTransaksiFromJSON> riwayatTransaksiList, filteredRiwayatTransaksiList;
    private Context context;
    public RiwayatTransaksiAdapter(List<RiwayatTransaksiFromJSON> riwayatTransaksiList, Context context) {
        this.riwayatTransaksiList = riwayatTransaksiList;
        filteredRiwayatTransaksiList = new ArrayList<>(riwayatTransaksiList);
        this.context = context;
    }

    @NonNull
    @Override
    public RiwayatTransaksiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_riwayattrans_cust, parent, false);
        return new RiwayatTransaksiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTransaksiAdapter.ViewHolder holder, int position) {
        RiwayatTransaksiFromJSON riwayattransaksi = filteredRiwayatTransaksiList.get(position);

        holder.tvIDTransaksi.setText(riwayattransaksi.getId_transaksi());
        holder.tvStatusTransaksi.setText(riwayattransaksi.getStatus_transaksi());
        holder.tvNamaMobil.setText(riwayattransaksi.getNama_mobil());
        holder.tvNamaPegawai.setText(riwayattransaksi.getNama_pegawai());
        holder.tvNamaDriver.setText(riwayattransaksi.getNama_driver());
        holder.tvInfo.setText(riwayattransaksi.getJenis_promo() + "-" + riwayattransaksi.getSubtotal_all());
    }

    @Override
    public int getItemCount() {
        return filteredRiwayatTransaksiList.size();
    }

    public void setRiwayatTransaksiList(List<RiwayatTransaksiFromJSON> mahasiswaList) {
        this.riwayatTransaksiList = mahasiswaList;
        filteredRiwayatTransaksiList = new ArrayList<>(mahasiswaList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<RiwayatTransaksiFromJSON> filtered = new ArrayList<>();
                if (charSequenceString.isEmpty()) {
                    filtered.addAll(riwayatTransaksiList);
                } else {
                    for (RiwayatTransaksiFromJSON riwayattransaksi : riwayatTransaksiList) {
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
                filteredRiwayatTransaksiList.addAll((List<RiwayatTransaksiFromJSON>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIDTransaksi, tvStatusTransaksi, tvNamaDriver, tvNamaPegawai, tvNamaMobil, tvInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tv_info);
            tvIDTransaksi = itemView.findViewById(R.id.tv_id_transaksi);
            tvStatusTransaksi = itemView.findViewById(R.id.tv_status_transaksi);
            tvNamaDriver = itemView.findViewById(R.id.tv_nama_driver);
            tvNamaPegawai = itemView.findViewById(R.id.tv_nama_pegawai);
            tvNamaMobil = itemView.findViewById(R.id.tv_nama_mobil);
        }
    }


}

