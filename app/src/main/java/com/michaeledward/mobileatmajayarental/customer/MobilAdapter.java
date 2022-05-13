package com.michaeledward.mobileatmajayarental.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.michaeledward.mobileatmajayarental.R;

import java.util.ArrayList;
import java.util.List;

public class MobilAdapter extends RecyclerView.Adapter<MobilAdapter.ViewHolder> implements Filterable {

    private List<MobilFromJSON> mobilList, filteredMobilList;
    private Context context;
    public MobilAdapter(List<MobilFromJSON> mobilList, Context context) {
        this.mobilList = mobilList;
        filteredMobilList = new ArrayList<>(mobilList);
        this.context = context;
    }

    @NonNull
    @Override
    public MobilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_mobil, parent, false);
        return new MobilAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MobilAdapter.ViewHolder holder, int position) {
        MobilFromJSON mobil = filteredMobilList.get(position);
        holder.tvPlatNomor.setText(mobil.getPlat_nomor());
        holder.tvNamaMobil.setText(mobil.getNama_mobil());
        holder.tvInfo.setText(mobil.getStatus_ketersediaan() + " - " + mobil.getBiaya_sewa());
        Glide.with(context)
                .load("http://192.168.100.8:8000/storage/"+mobil.getFoto_mobil())
                .placeholder(R.drawable.no_image)
                .into(holder.ivgambar);
    }

    @Override
    public int getItemCount() {
        return filteredMobilList.size();
    }

    public void setMobilList(List<MobilFromJSON> mahasiswaList) {
        this.mobilList = mahasiswaList;
        filteredMobilList = new ArrayList<>(mahasiswaList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<MobilFromJSON> filtered = new ArrayList<>();
                if (charSequenceString.isEmpty()) {
                    filtered.addAll(mobilList);
                } else {
                    for (MobilFromJSON mobil : mobilList) {
                        if (mobil.getNama_mobil().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(mobil);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                filteredMobilList.clear();
                filteredMobilList.addAll((List<MobilFromJSON>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlatNomor, tvNamaMobil, tvInfo;
        ImageView ivgambar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivgambar = itemView.findViewById(R.id.iv_gambar);
            tvNamaMobil = itemView.findViewById(R.id.tv_nama_mobil);
            tvPlatNomor = itemView.findViewById(R.id.tv_plat_nomor);
            tvInfo = itemView.findViewById(R.id.tv_info);

        }
    }


}

