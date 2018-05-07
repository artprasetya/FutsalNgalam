package com.example.asus.futsalngalam.MenuTempatFutsal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.futsalngalam.MenuTempatFutsal.Adapter.FasilitasAdapter;
import com.example.asus.futsalngalam.MenuTempatFutsal.Adapter.LapanganAdapter;
import com.example.asus.futsalngalam.MenuTempatFutsal.Model.Fasilitas;
import com.example.asus.futsalngalam.MenuTempatFutsal.Model.Lapangan;
import com.example.asus.futsalngalam.MenuTempatFutsal.Model.TempatFutsal;
import com.example.asus.futsalngalam.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailTempatFutsalActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseReference dbRef;
    private String namaTempatFutsal;
    private String alamat;
    private ImageView imageView;
    private TextView tvKontak;
    private TextView tvDeskripsi;
    private ImageView whatsapp;
    private ImageView mapView;
    private Button btn_foto;

    // Creating RecyclerView.
    RecyclerView recyclerViewFasilitas, recyclerViewLapangan;

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter;

    // Creating List of ImageUploadInfo class.
    List<Fasilitas> fasilitasList = new ArrayList<>();
    List<Lapangan> lapanganList = new ArrayList<>();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tempat_futsal);

        context = this;

        setToolbar();

        // Assign id to RecyclerView.
        recyclerViewFasilitas = (RecyclerView) findViewById(R.id.fasilitas_list);
        recyclerViewLapangan = (RecyclerView) findViewById(R.id.lapangan_list);

        // Setting RecyclerView size true.
        recyclerViewFasilitas.setHasFixedSize(true);
        recyclerViewLapangan.setHasFixedSize(true);

        // Setting RecyclerView layout as LinearLayout.
        recyclerViewFasilitas.setLayoutManager(new LinearLayoutManager(DetailTempatFutsalActivity.this));
        recyclerViewLapangan.setLayoutManager(new LinearLayoutManager(DetailTempatFutsalActivity.this));

        imageView = (ImageView) findViewById(R.id.imageView);
        mapView = (ImageView) findViewById(R.id.maps);
        whatsapp = (ImageView) findViewById(R.id.whatsapp);
        tvKontak = (TextView) findViewById(R.id.tvKontak);
        tvDeskripsi = (TextView) findViewById(R.id.tvDeskripsi);
        btn_foto = (Button) findViewById(R.id.btn_foto);
//        btnUbah = (Button) findViewById(R.id.btn_ubah);

        getDataFutsal();
        getDataFasilitas();
        getDataLapangan();

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWhatsApp();
            }
        });

        tvKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDial();
            }
        });

        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMap();
            }
        });

        btn_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFoto();
            }
        });

    }

    private void getDataLapangan() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        dbRef = FirebaseDatabase.getInstance().getReference("lapangan");
        // Adding Add Value Event Listener to databaseReference.
        dbRef.child(idPetugas).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lapanganList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    Lapangan lapangan = postSnapshot.getValue(Lapangan.class);

                    lapanganList.add(lapangan);
                }

                adapter = new LapanganAdapter(context, lapanganList);

                recyclerViewLapangan.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDataFasilitas() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        dbRef = FirebaseDatabase.getInstance().getReference("fasilitas");
        // Adding Add Value Event Listener to databaseReference.
        dbRef.child(idPetugas).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                fasilitasList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    Fasilitas fasilitas = postSnapshot.getValue(Fasilitas.class);

                    fasilitasList.add(fasilitas);
                }

                adapter = new FasilitasAdapter(context, fasilitasList);

                recyclerViewFasilitas.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void gotoFoto() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        Intent intent = new Intent(this, LihatFotoActivity.class);
        intent.putExtra("idPetugas", idPetugas);
        startActivity(intent);
    }

    private void gotoMap() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("idPetugas", idPetugas);
        startActivity(intent);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String idPetugas = getIntent().getStringExtra("idPetugas");

        dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.child("tempatFutsal").child(idPetugas).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                namaTempatFutsal = (String) dataSnapshot.child("namaTempatFutsal").getValue();
                alamat = (String) dataSnapshot.child("alamat").getValue();
                toolbar.setTitle(namaTempatFutsal);
                toolbar.setSubtitle(alamat);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void getDataFutsal() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        dbRef.child("tempatFutsal").child(idPetugas).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TempatFutsal tempatFutsal = dataSnapshot.getValue(TempatFutsal.class);
                if (tempatFutsal != null) {
                    Glide.with(getApplication()).load(tempatFutsal.getFotoProfil()).into(imageView);
                    tvKontak.setText(tempatFutsal.getNoTelepon());
                    tvDeskripsi.setText(tempatFutsal.getDeskripsi());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void goToWhatsApp() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        dbRef.child("tempatFutsal").child(idPetugas).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String notelp = (String) dataSnapshot.child("noTelepon").getValue();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + notelp));
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void goToDial() {
        String idPetugas = getIntent().getStringExtra("idPetugas");
        dbRef.child("tempatFutsal").child(idPetugas).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String notelp = (String) dataSnapshot.child("noTelepon").getValue();
                String dial = "tel:" + notelp;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
