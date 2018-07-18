package com.example.asus.futsalngalam.MenuPesanan;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.asus.futsalngalam.Adapter.RekeningAdapter;
import com.example.asus.futsalngalam.Model.Rekening;
import com.example.asus.futsalngalam.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PilihRekeningActivity extends AppCompatActivity {

    private Toolbar toolbar;

    // Creating RecyclerView.
    RecyclerView recyclerView;

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter;

    // Creating List of ImageUploadInfo class.
    List<Rekening> rekeningList = new ArrayList<>();

    Context context;
    private DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_rekening);

        context = this;

        setToolbar();

        // Assign id to RecyclerView.
        recyclerView = (RecyclerView) findViewById(R.id.rekeningList);

        // Setting RecyclerView size true.
        recyclerView.setHasFixedSize(true);

        // Setting RecyclerView layout as LinearLayout.
        recyclerView.setLayoutManager(new LinearLayoutManager(PilihRekeningActivity.this));

        getDataRekening();
    }

    private void getDataRekening() {
        final String idPetugas = getIntent().getStringExtra("idPetugas");
        final String idPemesan = getIntent().getStringExtra("idPemesan");
        final String idLapangan = getIntent().getStringExtra("idLapangan");
        final String idPesanan = getIntent().getStringExtra("idPesanan");

        dbRef = FirebaseDatabase.getInstance().getReference("rekening");
        // Adding Add Value Event Listener to databaseReference.
        dbRef.child(idPetugas).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                rekeningList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    Rekening rekening = postSnapshot.getValue(Rekening.class);

                    rekeningList.add(rekening);
                }

                adapter = new RekeningAdapter(context, rekeningList, idPetugas, idPemesan, idLapangan, idPesanan);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rekening Pembayaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}