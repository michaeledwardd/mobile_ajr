package com.michaeledward.mobileatmajayarental.customer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.michaeledward.mobileatmajayarental.R;

import java.util.ArrayList;
import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder> implements Filterable {

    private List<PromoFromJSON> promoList, filteredPromoList;
    private Context context;
    public PromoAdapter(List<PromoFromJSON> promoList, Context context) {
        this.promoList = promoList;
        filteredPromoList = new ArrayList<>(promoList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_promo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PromoFromJSON promo = filteredPromoList.get(position);
        holder.tvJenisPromo.setText(promo.getJenisPromo());
        holder.tvKodePromo.setText(promo.getKodePromo());
        holder.tvInfo.setText(promo.getStatus_promo() + " - " + promo.getJumlahpotongan());

    }

    @Override
    public int getItemCount() {
        return filteredPromoList.size();
    }

    public void setPromoList(List<PromoFromJSON> mahasiswaList) {
        this.promoList = mahasiswaList;
        filteredPromoList = new ArrayList<>(mahasiswaList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<PromoFromJSON> filtered = new ArrayList<>();
                if (charSequenceString.isEmpty()) {
                    filtered.addAll(promoList);
                } else {
                    for (PromoFromJSON promo : promoList) {
                        if (promo.getKodePromo().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(promo);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                filteredPromoList.clear();
                filteredPromoList.addAll((List<PromoFromJSON>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJenisPromo, tvKodePromo, tvInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJenisPromo = itemView.findViewById(R.id.tv_jenis_promo);
            tvKodePromo = itemView.findViewById(R.id.tv_kode_promo);
            tvInfo = itemView.findViewById(R.id.tv_info);

        }
    }


}
