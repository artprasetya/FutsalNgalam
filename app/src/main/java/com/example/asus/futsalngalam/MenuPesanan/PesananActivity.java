package com.example.asus.futsalngalam.MenuPesanan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.futsalngalam.MenuProfil.Pemesan;
import com.example.asus.futsalngalam.MenuTempatFutsal.Model.Lapangan;
import com.example.asus.futsalngalam.MenuTempatFutsal.Model.TempatFutsal;
import com.example.asus.futsalngalam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PesananActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseReference dbRef;
    private String idPemesan;
    private TextView namaPemesan;
    private TextView nomorPemesan;
    private TextView namaTempatFutsal;
    private TextView namaLapangan;
    private Button btnCekJadwal;
    private Button btnPembayaran;
    private FirebaseAuth auth;
    private TextView tanggalPesan;

    //DatePicker
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    //Spinner
    private Spinner spinnerMulai;
    private Spinner spinnerSelesai;
    private Integer[] jamMulai = {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    private Integer[] jamSelesai = {7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        setToolbar();

        namaPemesan = (TextView) findViewById(R.id.tvNamaPemesan);
        nomorPemesan = (TextView) findViewById(R.id.tvNomorTelepon);
        namaTempatFutsal = (TextView) findViewById(R.id.tvTempatFutsal);
        namaLapangan = (TextView) findViewById(R.id.tvNamaLapangan);
        tanggalPesan = (TextView) findViewById(R.id.tvTanggalPesan);
        spinnerMulai = (Spinner) findViewById(R.id.jamMulai);
        spinnerSelesai = (Spinner) findViewById(R.id.jamSelesai);
        btnCekJadwal = (Button) findViewById(R.id.btnCek);
        btnPembayaran = (Button) findViewById(R.id.btnPembayaran);

        setSpinner();

        dbRef = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        idPemesan = user.getUid();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tanggalPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        getDataPesanan();

        btnPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idPetugas = getIntent().getStringExtra("idPetugas");
                Intent intent = new Intent(PesananActivity.this, PilihRekeningActivity.class);
                intent.putExtra("idPetugas", idPetugas);
                startActivity(intent);
            }
        });

    }

    private void showDateDialog() {
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tanggalPesan.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void setSpinner() {
        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<Integer> adapterMulai = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, jamMulai);

        // inisialiasi Array Adapter dengan memasukkan string array di atas
        final ArrayAdapter<Integer> adapterSelesai = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, jamSelesai);

        // mengeset Array Adapter tersebut ke Spinner
        spinnerMulai.setAdapter(adapterMulai);
        spinnerSelesai.setAdapter(adapterSelesai);

        spinnerMulai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PesananActivity.this, "Selected " + adapterMulai.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSelesai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PesananActivity.this, "Selected " + adapterSelesai.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getDataPesanan() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        String idLapangan = getIntent().getStringExtra("idLapangan");

        dbRef.child("pemesan").child(idPemesan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pemesan dataPemesan = dataSnapshot.getValue(Pemesan.class);
                if (dataPemesan != null) {
                    namaPemesan.setText(dataPemesan.getNama());
                    nomorPemesan.setText(dataPemesan.getTelepon());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbRef.child("tempatFutsal").child(idPetugas).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TempatFutsal dataTempatFutsal = dataSnapshot.getValue(TempatFutsal.class);
                if (dataTempatFutsal != null) {
                    namaTempatFutsal.setText(dataTempatFutsal.getNamaTempatFutsal());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbRef.child("lapangan").child(idPetugas).child(idLapangan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Lapangan dataLapangan = dataSnapshot.getValue(Lapangan.class);
                if (dataLapangan != null) {
                    namaLapangan.setText(dataLapangan.getNamaLapangan());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pesan Lapangan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
