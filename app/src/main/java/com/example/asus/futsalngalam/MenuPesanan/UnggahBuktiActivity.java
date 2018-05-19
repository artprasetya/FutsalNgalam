package com.example.asus.futsalngalam.MenuPesanan;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.asus.futsalngalam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class UnggahBuktiActivity extends AppCompatActivity {

    //object idPetugas
    String idPetugas;

    // Root Database Name for Firebase Database.
    String Database_Path = "tempatFutsal";

    // Creating button.
    Button pilihGambar, btnUnggah;

    // Creating EditText.
    EditText namaRekening, nomorRekening;

    // Creating ImageView.
    ImageView SelectImage;

    // Creating URI.
    Uri FilePathUri;

    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;
    DatabaseReference databaseReference;

    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;

    ProgressDialog progressDialog;


    private Toolbar toolbar;
    private RadioGroup radioPembayaran;
    private RadioButton rbLunas, rbUangMuka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unggah_bukti);

        setToolbar();

        namaRekening = findViewById(R.id.etNamaRekening);
        nomorRekening = findViewById(R.id.etNomorRekening);
        radioPembayaran = findViewById(R.id.rgPembayaran);
        pilihGambar = findViewById(R.id.pilihGambar);
        btnUnggah = findViewById(R.id.unggah);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

    }

    private void hitungLunas() {

    }

    private void hitungUangMuka() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        String idPesanan = getIntent().getStringExtra("idPesanan");
        String namaBank = getIntent().getStringExtra("namaBank");
        String nomorRekening = getIntent().getStringExtra("nomorRekening");
        String namaRekening = getIntent().getStringExtra("namaRekening");
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
