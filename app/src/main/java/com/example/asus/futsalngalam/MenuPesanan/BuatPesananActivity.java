package com.example.asus.futsalngalam.MenuPesanan;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.futsalngalam.Model.Lapangan;
import com.example.asus.futsalngalam.Model.Pemesan;
import com.example.asus.futsalngalam.Model.TempatFutsal;
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

public class BuatPesananActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseReference dbRef;
    private String idPemesan;
    private String idPesanan;
    private TextView namaPemesan;
    private TextView nomorPemesan;
    private TextView namaTempatFutsal;
    private TextView namaLapangan;
    private EditText catatan;
    private Button btnCekJadwal;
    private Button btnPembayaran;
    private FirebaseAuth auth;
    private TextView tanggalPesan;
    private boolean jadwal = false;
    private boolean jam = false;
    private boolean tgl = false;
    private ProgressDialog mProgress;

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
        setContentView(R.layout.activity_buat_pesanan);

        setToolbar();

        mProgress = new ProgressDialog(this);
        namaPemesan = findViewById(R.id.tvNamaPemesan);
        nomorPemesan = findViewById(R.id.tvNomorTelepon);
        namaTempatFutsal = findViewById(R.id.tvTempatFutsal);
        namaLapangan = findViewById(R.id.tvNamaLapangan);
        tanggalPesan = findViewById(R.id.tvTanggalPesan);
        spinnerMulai = findViewById(R.id.jamMulai);
        spinnerSelesai = findViewById(R.id.jamSelesai);
        catatan = findViewById(R.id.etCatatan);
        btnCekJadwal = findViewById(R.id.btnCek);
        btnPembayaran = findViewById(R.id.btnPembayaran);

        setSpinner();

        dbRef = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        idPemesan = user.getUid();
        idPesanan = dbRef.push().getKey();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        tanggalPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        getDataPesanan();

        btnCekJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cekTanggal()) {
                    cekJam();
                }

                if (tgl == true && jam == true) {
                    cekJadwal();
                }
            }
        });

        btnPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tgl == true && jam == true && jadwal == true) {
                    String idPetugas = getIntent().getStringExtra("idPetugas");
                    String idLapangan = getIntent().getStringExtra("idLapangan");

                    buatPesanan();

                    Intent intent = new Intent(BuatPesananActivity.this, PilihRekeningActivity.class);
                    intent.putExtra("idPetugas", idPetugas);
                    intent.putExtra("idPemesan", idPemesan);
                    intent.putExtra("idLapangan", idLapangan);
                    intent.putExtra("idPesanan", idPesanan);
                    startActivity(intent);
                } else {
                    Toast.makeText(BuatPesananActivity.this, "Periksa Kembali Pesanan Anda",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void buatPesanan() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        String idLapangan = getIntent().getStringExtra("idLapangan");
        String hargaSewa = getIntent().getStringExtra("hargaSewa");
        String pemesan = namaPemesan.getText().toString();
        String noTelpon = nomorPemesan.getText().toString();
        String tempatFutsal = namaTempatFutsal.getText().toString();
        String lapangan = namaLapangan.getText().toString();
        String tglPesan = tanggalPesan.getText().toString();
        String jamMulai = spinnerMulai.getSelectedItem().toString();
        String jamSelesai = spinnerSelesai.getSelectedItem().toString();
        String getCatatan = catatan.getText().toString();
        String statusPesanan = "Belum Bayar";

        dbRef.child("pesanan").child(idPesanan).child("idPesanan").setValue(idPesanan);
        dbRef.child("pesanan").child(idPesanan).child("idPetugas").setValue(idPetugas);
        dbRef.child("pesanan").child(idPesanan).child("idPemesan").setValue(idPemesan);
        dbRef.child("pesanan").child(idPesanan).child("idLapangan").setValue(idLapangan);
        dbRef.child("pesanan").child(idPesanan).child("statusPesanan").setValue(statusPesanan);
        dbRef.child("pesanan").child(idPesanan).child("namaPemesan").setValue(pemesan);
        dbRef.child("pesanan").child(idPesanan).child("noTelepon").setValue(noTelpon);
        dbRef.child("pesanan").child(idPesanan).child("namaTempatFutsal").setValue(tempatFutsal);
        dbRef.child("pesanan").child(idPesanan).child("namaLapangan").setValue(lapangan);
        dbRef.child("pesanan").child(idPesanan).child("tanggalPesan").setValue(tglPesan);
        dbRef.child("pesanan").child(idPesanan).child("jamMulai").setValue(jamMulai);
        dbRef.child("pesanan").child(idPesanan).child("jamSelesai").setValue(jamSelesai);
        dbRef.child("pesanan").child(idPesanan).child("catatan").setValue(getCatatan);
    }

    private boolean cekJadwal() {
        mProgress.setMessage("Mengecek Jadwal");
        final String tempatFutsal = namaTempatFutsal.getText().toString();
        final String lapangan = namaLapangan.getText().toString();
        final String tglPesan = tanggalPesan.getText().toString();
        final String jamMulai = spinnerMulai.getSelectedItem().toString();
        final String jamSelesai = spinnerSelesai.getSelectedItem().toString();

        mProgress.show();
        dbRef.child("pesanan").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if ((data.child("namaTempatFutsal").getValue().equals(tempatFutsal)) &&
                            (data.child("namaLapangan").getValue().equals(lapangan)) &&
                            (data.child("tanggalPesan").getValue().equals(tglPesan)) &&
                            (data.child("jamMulai").getValue().equals(jamMulai)) &&
                            (data.child("jamSelesai").getValue().equals(jamSelesai))) {
                        btnCekJadwal.setText("Sudah Ada yang Memesan");
                        mProgress.dismiss();
                        jadwal = false;
                    } else {
                        btnCekJadwal.setText("Jadwal Tersedia");
                        mProgress.dismiss();
                        jadwal = true;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return jadwal;
    }

    private boolean cekTanggal() {
        String tanggal = tanggalPesan.getText().toString();

        if (TextUtils.isEmpty(tanggal)) {
            btnCekJadwal.setText("Silahkan pilih tanggal pesan");
            tgl = false;
        } else {
            tgl = true;
        }
        return tgl;
    }

    private boolean cekJam() {
        Integer jamMulai = (Integer) spinnerMulai.getSelectedItem();
        Integer jamSelesai = (Integer) spinnerSelesai.getSelectedItem();

        if (jamSelesai <= jamMulai) {
            btnCekJadwal.setText("Periksa kembali jam pesan");
            jam = false;
        } else {
            jam = true;
        }
        return jam;
    }

    private void showDateDialog() {
        //Calendar untuk mendapatkan tanggal sekarang
        Calendar newCalendar = Calendar.getInstance();

        //Initiate DatePicker dialog
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                //Set Calendar untuk menampung tanggal yang dipilih
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tanggalPesan.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSelesai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
        toolbar = findViewById(R.id.toolbar);
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