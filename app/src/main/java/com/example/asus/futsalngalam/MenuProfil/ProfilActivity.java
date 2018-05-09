package com.example.asus.futsalngalam.MenuProfil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.futsalngalam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilActivity extends AppCompatActivity {

    private DatabaseReference dbRef;

    private TextView textViewNama,
            textViewNoTelepon,
            textViewAlamat;

    private Button ubahProfil;

    private String idPemesan;
    private Toolbar toolbar;

    private CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        setToolbar();

        circleImageView = (CircleImageView) findViewById(R.id.fotoProfil);
        textViewNama = (TextView) findViewById(R.id.tvNama);
        textViewAlamat = (TextView) findViewById(R.id.tvAlamat);
        textViewNoTelepon = (TextView) findViewById(R.id.tvNotelp);
        ubahProfil = (Button) findViewById(R.id.btn_ubah);

        dbRef = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;
        idPemesan = user.getUid();

        getDataPemesan();

        ubahProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilActivity.this, UbahProfilActivity.class));
            }
        });
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profil Pemesan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void getDataPemesan() {
        dbRef.child("pemesan").child(idPemesan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pemesan dataPemesan = dataSnapshot.getValue(Pemesan.class);
                if (dataPemesan != null) {
                    Glide.with(getApplication()).load(dataPemesan.getFotoProfil()).into(circleImageView);
                    textViewNama.setText(dataPemesan.getNama());
                    textViewAlamat.setText(dataPemesan.getAlamat());
                    textViewNoTelepon.setText(dataPemesan.getTelepon());
                }
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