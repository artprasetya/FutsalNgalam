package com.example.asus.futsalngalam.MenuPesanan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.futsalngalam.Model.Pembayaran;
import com.example.asus.futsalngalam.Model.Pesanan;
import com.example.asus.futsalngalam.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailPesananActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView invoice, statusPesanan,
            namaPemesan, nomorTelepon,
            namaTempatFutsal, namaLapangan,
            tanggalPesan, durasiSewa,
            totalPembayaran, tvNamaBank,
            tvNomorRekening, tvNamaRekening;
    private Button btnUnggah, btnUlasan;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        setToolbar();

        invoice = findViewById(R.id.tvInvoice);
        statusPesanan = findViewById(R.id.tvStatus);
        namaPemesan = findViewById(R.id.tvNamaPemesan);
        nomorTelepon = findViewById(R.id.tvNomorPemesan);
        namaTempatFutsal = findViewById(R.id.tvNamaTempatFutsal);
        namaLapangan = findViewById(R.id.tvNamaLapangan);
        tanggalPesan = findViewById(R.id.tvTanggalPesan);
        durasiSewa = findViewById(R.id.tvDurasi);
        totalPembayaran = findViewById(R.id.tvTotal);
        tvNamaBank = findViewById(R.id.tvNamaBank);
        tvNomorRekening = findViewById(R.id.tvNomorRekening);
        tvNamaRekening = findViewById(R.id.tvNamaRekening);
        btnUnggah = findViewById(R.id.btnUnggahBukti);
        btnUlasan = findViewById(R.id.btnBeriUlasan);

        getDataPesanan();

        btnUnggah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUnggah();
            }
        });
        btnUlasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUlasan();
            }
        });
    }

    private void getDataPesanan() {
        String idPesanan = getIntent().getStringExtra("idPesanan");

        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("pesanan").child(idPesanan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pesanan dataPesanan = dataSnapshot.getValue(Pesanan.class);
                if (dataPesanan != null) {
                    invoice.setText(dataPesanan.getInvoice());
                    statusPesanan.setText(dataPesanan.getStatusPesanan());
                    namaPemesan.setText(dataPesanan.getNamaPemesan());
                    nomorTelepon.setText(dataPesanan.getNoTelepon());
                    namaTempatFutsal.setText(dataPesanan.getNamaTempatFutsal());
                    namaLapangan.setText(dataPesanan.getNamaLapangan());
                    tanggalPesan.setText(dataPesanan.getTanggalPesan());
                    durasiSewa.setText("Jam " + (dataPesanan.getJamMulai() + " - " + (dataPesanan.getJamSelesai() + " WIB")));
                    totalPembayaran.setText("Rp. " + String.valueOf(dataPesanan.getTotalPembayaran()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbRef.child("pesanan").child(idPesanan).child("pembayaran").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pembayaran dataPembayaran = dataSnapshot.getValue(Pembayaran.class);
                if (dataPembayaran != null) {
                    tvNamaBank.setText(dataPembayaran.getNamaBank());
                    tvNomorRekening.setText(dataPembayaran.getNomorRekening());
                    tvNamaRekening.setText(dataPembayaran.getNamaRekening());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void gotoUlasan() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        String idPesanan = getIntent().getStringExtra("idPesanan");
        String tempatFutsal = namaTempatFutsal.getText().toString();
        String pemesan = namaPemesan.getText().toString();
        String telepon = nomorTelepon.getText().toString();

        Intent intent = new Intent(DetailPesananActivity.this, BeriUlasanActivity.class);
        intent.putExtra("idPetugas", idPetugas);
        intent.putExtra("idPesanan", idPesanan);
        intent.putExtra("namaTempatFutsal", tempatFutsal);
        intent.putExtra("namaPemesan", pemesan);
        intent.putExtra("nomorTelepon", telepon);
        startActivity(intent);
    }

    private void gotoUnggah() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        String idPesanan = getIntent().getStringExtra("idPesanan");
        String pemesan = namaPemesan.getText().toString();

        Intent intent = new Intent(DetailPesananActivity.this, UnggahBuktiActivity.class);
        intent.putExtra("idPetugas", idPetugas);
        intent.putExtra("idPesanan", idPesanan);
        intent.putExtra("namaPemesan", pemesan);
        startActivity(intent);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Pesanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
