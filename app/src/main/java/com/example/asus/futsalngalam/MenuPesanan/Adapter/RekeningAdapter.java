package com.example.asus.futsalngalam.MenuPesanan.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.futsalngalam.MenuTempatFutsal.Model.Rekening;
import com.example.asus.futsalngalam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RekeningAdapter extends RecyclerView.Adapter<RekeningAdapter.ViewHolder> implements View.OnClickListener {

    Context context;
    LayoutInflater mInflater;
    private List<Rekening> rekeningList;
    private DatabaseReference dbRef;

    public RekeningAdapter(Context context, List<Rekening> rekeningList) {
        this.context = context;
        this.rekeningList = rekeningList;
        mInflater = LayoutInflater.from(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNamaBank;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaBank = (TextView) itemView.findViewById(R.id.tvNamaBank);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rekening_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        dbRef = FirebaseDatabase.getInstance().getReference();

        final String idPetugas = rekeningList.get(position).getIdPetugas();
        final String idRekening = rekeningList.get(position).getIdRekening();
        final String namaRekening = rekeningList.get(position).getNamaRekening();
        final String namaBank = rekeningList.get(position).getNamaBank();

        holder.tvNamaBank.setText(namaBank);
    }

    @Override
    public int getItemCount() {
        return rekeningList.size();
    }

    @Override
    public void onClick(View v) {

    }
}
