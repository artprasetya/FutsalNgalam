package com.example.asus.futsalngalam.MenuPesanan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.futsalngalam.Model.Pesanan;
import com.example.asus.futsalngalam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailPesananActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView statusPesanan;
    private TextView namaPemesan;
    private TextView nomorTelepon;
    private TextView namaTempatFutsal;
    private TextView namaLapangan;
    private TextView tanggalPesan;
    private TextView durasiSewa;
    private Button btnUnggah;
    private Button btnUlasan;
    private DatabaseReference dbRef;
    private FirebaseAuth auth;
    private String idPemesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);

        setToolbar();

        statusPesanan = (TextView) findViewById(R.id.tvStatus);
        namaPemesan = (TextView) findViewById(R.id.tvNamaPemesan);
        nomorTelepon = (TextView) findViewById(R.id.tvNomorPemesan);
        namaTempatFutsal = (TextView) findViewById(R.id.tvNamaTempatFutsal);
        namaLapangan = (TextView) findViewById(R.id.tvNamaLapangan);
        tanggalPesan = (TextView) findViewById(R.id.tvTanggalPesan);
        durasiSewa = (TextView) findViewById(R.id.tvDurasi);
        btnUnggah = (Button) findViewById(R.id.btnUnggahBukti);
        btnUlasan = (Button) findViewById(R.id.btnBeriUlasan);

        dbRef = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        idPemesan = user.getUid();

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

        dbRef.child("pesanan").child(idPesanan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pesanan dataPesanan = dataSnapshot.getValue(Pesanan.class);
                if (dataPesanan != null) {
                    statusPesanan.setText(dataPesanan.getStatusPesanan());
                    namaPemesan.setText(dataPesanan.getNamaPemesan());
                    nomorTelepon.setText(dataPesanan.getNoTelepon());
                    namaTempatFutsal.setText(dataPesanan.getNamaTempatFutsal());
                    namaLapangan.setText(dataPesanan.getNamaLapangan());
                    tanggalPesan.setText(dataPesanan.getTanggalPesan());
                    durasiSewa.setText("Jam " + (dataPesanan.getJamMulai() + " - " + (dataPesanan.getJamSelesai() + " WIB")));
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
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
