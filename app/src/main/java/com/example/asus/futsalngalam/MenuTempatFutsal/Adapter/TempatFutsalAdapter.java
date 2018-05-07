package com.example.asus.futsalngalam.MenuTempatFutsal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.futsalngalam.MenuTempatFutsal.DetailTempatFutsalActivity;
import com.example.asus.futsalngalam.MenuTempatFutsal.Model.TempatFutsal;
import com.example.asus.futsalngalam.R;

import java.util.List;

public class TempatFutsalAdapter extends RecyclerView.Adapter<TempatFutsalAdapter.ViewHolder> {

    Context context;
    private List<TempatFutsal> tempatFutsalList;
    LayoutInflater mInflater;

    public TempatFutsalAdapter(Context context, List<TempatFutsal> tempatFutsalList) {
        this.context = context;
        this.tempatFutsalList = tempatFutsalList;
        mInflater = LayoutInflater.from(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fotoTempatFutsal);
            textView = itemView.findViewById(R.id.namaTempatFutsal);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.futsal_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TempatFutsal dataTempatFutsal = tempatFutsalList.get(position);
        final String idPetugas = dataTempatFutsal.getIdPetugas();
        final String fotoTempatFutsal = dataTempatFutsal.getFotoProfil();
        final String namaTempatFutsal = dataTempatFutsal.getNamaTempatFutsal();

        Glide.with(context)
                .load(fotoTempatFutsal)
                .into(holder.imageView);
        holder.textView.setText(namaTempatFutsal);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTempatFutsalActivity.class);
                intent.putExtra("idPetugas", idPetugas);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tempatFutsalList.size();
    }
}
