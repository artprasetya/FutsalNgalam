package com.example.asus.futsalngalam.MenuProfil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.futsalngalam.Model.Penyewa;
import com.example.asus.futsalngalam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CHOOSE_IMAGE = 101;
    //    private ImageView imageView;
    private EditText editTextProfile, editTextPhone;
    private Button btsave, selectImage;
    private TextView tvEmail;

    private DatabaseReference databaseReference;
//    private StorageReference storageReference;

    private String idPenyewa;

//    private Uri uriProfileImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

//        imageView = (ImageView) findViewById(R.id.profile_image);
        selectImage = (Button) findViewById(R.id.btn_select);
        editTextProfile = (EditText) findViewById(R.id.etName);
        editTextPhone = (EditText) findViewById(R.id.etPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        btsave = (Button) findViewById(R.id.btsave);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("penyewa");
        FirebaseUser user = firebaseAuth.getCurrentUser();

//        storageReference = FirebaseStorage.getInstance().getReference();

        assert user != null;
        idPenyewa = user.getUid();
        String emailPenyewa = user.getEmail();

        tvEmail.setText(emailPenyewa);

        infoPenyewa();

        selectImage.setOnClickListener(this);
        btsave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btsave) {
            simpanDataPenyewa();
            finish();
        } else if (v == selectImage) {
            showImageChooser();
        }
    }

    private void simpanDataPenyewa() {

        final String email = tvEmail.getText().toString();
        final String nama = editTextProfile.getText().toString();
        final String telepon = editTextPhone.getText().toString();

        if (nama.isEmpty()) {
            editTextProfile.setError("Wajib diisi");
        } else if (telepon.isEmpty()) {
            editTextPhone.setError("Wajib diisi");
        } else {
            Penyewa dataProfil = new Penyewa(nama, email, telepon);
            databaseReference.child(idPenyewa).setValue(dataProfil).addOnCompleteListener(EditProfileActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Biodata Anda Gagal Disimpan", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Biodata Anda Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    public void infoPenyewa() {
        databaseReference.child(idPenyewa).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Penyewa dataPenyewa = dataSnapshot.getValue(Penyewa.class);
                if (dataPenyewa != null) {
                    editTextProfile.setText(dataPenyewa.getNama());
                    editTextPhone.setText(dataPenyewa.getTelepon());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK) {
//            uriProfileImage = data.getData();
//
//            imageView.setImageURI(uriProfileImage);
//        }
//    }
//
    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Photo"), CHOOSE_IMAGE);
    }
}
