package com.example.asus.futsalngalam.MenuProfil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.futsalngalam.Model.Penyewa;
import com.example.asus.futsalngalam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private TextView textViewName, textViewEmail, textViewPhone;
    private Button editProfile;

    private String idPenyewa, emailPenyewa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editProfile = (Button) findViewById(R.id.editprofile);
        textViewName = (TextView) findViewById(R.id.profileName);
        textViewEmail = (TextView) findViewById(R.id.tvEmail);
        textViewPhone = (TextView) findViewById(R.id.tvPhone);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;
        idPenyewa = user.getUid();
        emailPenyewa = user.getEmail();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        infoPenyewa();
        textViewEmail.setText(emailPenyewa);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
            }
        });
    }

    public void infoPenyewa() {
        databaseReference.child("penyewa").child(idPenyewa).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Penyewa dataPenyewa = dataSnapshot.getValue(Penyewa.class);
                textViewName.setText(dataPenyewa.getNama());
                textViewPhone.setText(dataPenyewa.getTelepon());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}