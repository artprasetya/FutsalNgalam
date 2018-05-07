package com.example.asus.futsalngalam.MenuTempatFutsal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.futsalngalam.MenuTempatFutsal.Model.Lapangan;
import com.example.asus.futsalngalam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class LapanganAdapter extends RecyclerView.Adapter<LapanganAdapter.ViewHolder> implements View.OnClickListener {

    Context context;
    LayoutInflater mInflater;
    private List<Lapangan> lapanganList;
    private DatabaseReference dbRef;

    public LapanganAdapter(Context context, List<Lapangan> lapanganList) {
        this.context = context;
        this.lapanganList = lapanganList;

        mInflater = LayoutInflater.from(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaLapangan;
        public TextView tvHarga;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNamaLapangan = (TextView) itemView.findViewById(R.id.tvNamaLapangan);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHarga);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lapangan_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        dbRef = FirebaseDatabase.getInstance().getReference();

        final String namaLapangan = lapanganList.get(position).getNamaLapangan();
        final double hargaSewa = lapanganList.get(position).getHargaSewa();

        holder.tvNamaLapangan.setText(namaLapangan);
        holder.tvHarga.setText("Rp. " + String.valueOf(hargaSewa));

    }

    @Override
    public int getItemCount() {
        return lapanganList.size();
    }

    @Override
    public void onClick(View v) {

    }
}
