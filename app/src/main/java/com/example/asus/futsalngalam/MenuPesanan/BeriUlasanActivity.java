package com.example.asus.futsalngalam.MenuPesanan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.futsalngalam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BeriUlasanActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTempatFutsal;
    private TextView tvNamaPemesan;
    private TextView tvNomorTelepon;
    private RatingBar ratingBar;
    private EditText getUlasan;
    private Button btnSimpan;
    private DatabaseReference dbRef;
    private FirebaseAuth auth;
    private String idPemesan;
    private String idUlasan;
    private Calendar calander;
    private SimpleDateFormat simpledateformat;
    private String Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beri_ulasan);

        setToolbar();

        tvTempatFutsal = findViewById(R.id.namaTempatFutsal);
        tvNamaPemesan = findViewById(R.id.namaPemesan);
        tvNomorTelepon = findViewById(R.id.nomorTelepon);
        ratingBar = findViewById(R.id.ratingBar);
        getUlasan = findViewById(R.id.etUlasan);
        btnSimpan = findViewById(R.id.btnSimpan);

        dbRef = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        idPemesan = user.getUid();
        idUlasan = dbRef.push().getKey();

        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date = simpledateformat.format(calander.getTime());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });

        getDataPemesan();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanUlasan();
                String idPesanan = getIntent().getStringExtra("idPesanan");
                String namaBank = getIntent().getStringExtra("namaBank");
                String nomorRekening = getIntent().getStringExtra("nomorRekening");
                String namaRekening = getIntent().getStringExtra("namaRekening");
                Intent intent = new Intent(BeriUlasanActivity.this, DetailPesananActivity.class);
                intent.putExtra("idPesanan", idPesanan);
                intent.putExtra("namaBank", namaBank);
                intent.putExtra("nomorRekening", nomorRekening);
                intent.putExtra("namaRekening", namaRekening);
                startActivity(intent);
            }
        });

    }

    private void simpanUlasan() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        String idPesanan = getIntent().getStringExtra("idPesanan");
        String pemesan = tvNamaPemesan.getText().toString();
        String telepon = tvNomorTelepon.getText().toString();
        String rating = String.valueOf(ratingBar.getRating());
        String ulasan = getUlasan.getText().toString();

        if (TextUtils.isEmpty(ulasan)) {
            Toast.makeText(this, "Silahkan beri ulasan anda", Toast.LENGTH_LONG).show();
        }

        dbRef.child("ulasan").child(idPetugas).child(idUlasan).child("idPetugas").setValue(idPetugas);
        dbRef.child("ulasan").child(idPetugas).child(idUlasan).child("idUlasan").setValue(idUlasan);
        dbRef.child("ulasan").child(idPetugas).child(idUlasan).child("idPesanan").setValue(idPesanan);
        dbRef.child("ulasan").child(idPetugas).child(idUlasan).child("idPemesan").setValue(idPemesan);
        dbRef.child("ulasan").child(idPetugas).child(idUlasan).child("namaPemesan").setValue(pemesan);
        dbRef.child("ulasan").child(idPetugas).child(idUlasan).child("nomorTelepon").setValue(telepon);
        dbRef.child("ulasan").child(idPetugas).child(idUlasan).child("rating").setValue(rating);
        dbRef.child("ulasan").child(idPetugas).child(idUlasan).child("ulasan").setValue(ulasan);
        dbRef.child("ulasan").child(idPetugas).child(idUlasan).child("timestamp").setValue(Date);
    }

    private void getDataPemesan() {
        String namaTempatFutsal = getIntent().getStringExtra("namaTempatFutsal");
        String namaPemesan = getIntent().getStringExtra("namaPemesan");
        String nomorTelepon = getIntent().getStringExtra("nomorTelepon");

        tvTempatFutsal.setText(namaTempatFutsal);
        tvNamaPemesan.setText(namaPemesan);
        tvNomorTelepon.setText(nomorTelepon);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Beri Ulasan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
