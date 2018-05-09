package com.example.asus.futsalngalam.MenuProfil;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.futsalngalam.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class UbahProfilActivity extends AppCompatActivity implements View.OnClickListener {
    //constant to track image chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;

    private CircleImageView circleImageView;

    private EditText editTextNama,
            editTextAlamat,
            editTextNoTelepon;

    private Button btnSimpan,
            pilihFoto,
            simpanFoto;

    private DatabaseReference dbRef;
    private StorageReference storageRef;

    private String idPemesan;

    //uri store file
    private Uri filePath;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ubah Profil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        circleImageView = (CircleImageView) findViewById(R.id.profil);
        pilihFoto = (Button) findViewById(R.id.pilihFoto);
        simpanFoto = (Button) findViewById(R.id.simpan_foto);
        editTextNama = (EditText) findViewById(R.id.etNama);
        editTextAlamat = (EditText) findViewById(R.id.etAlamat);
        editTextNoTelepon = (EditText) findViewById(R.id.etNoTelepon);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("pemesan");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        storageRef = FirebaseStorage.getInstance().getReference();

        assert user != null;
        idPemesan = user.getUid();

        getDataPemesan();

        pilihFoto.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan) {
            simpanData();
            finish();
        } else if (v == pilihFoto) {
            showFileChooser();
        } else if (v == simpanFoto) {
            simpanFoto();
        }
    }

    private void simpanData() {
        final String nama = editTextNama.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String telepon = editTextNoTelepon.getText().toString().trim();

        if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(alamat) && !TextUtils.isEmpty(telepon)) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Menyimpan Data");
            progressDialog.show();

            dbRef.child(idPemesan).child("nama").setValue(nama);
            dbRef.child(idPemesan).child("alamat").setValue(alamat);
            dbRef.child(idPemesan).child("telepon").setValue(telepon);

            progressDialog.dismiss();
            startActivity(new Intent(UbahProfilActivity.this, ProfilActivity.class));
        }

    }

    private void simpanFoto() {

        //checking if file is available
        if (filePath != null) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            StorageReference sRef = storageRef.child("uploads/" + System.currentTimeMillis() + "." + getFileExtension(filePath));

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "Berhasil Disimpan. ", Toast.LENGTH_LONG).show();

                            //simpan ke database
                            dbRef.child(idPemesan).child("fotoProfil").setValue(taskSnapshot.getDownloadUrl().toString());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Menyimpan " + ((int) progress) + "%...");
                        }
                    });
        } else {
            //display an error if no file is selected
        }
    }

    public void getDataPemesan() {
        dbRef.child(idPemesan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pemesan dataPemesan = dataSnapshot.getValue(Pemesan.class);
                if (dataPemesan != null) {
                    Glide.with(getApplication()).load(dataPemesan.getFotoProfil()).into(circleImageView);
                    editTextNama.setText(dataPemesan.getNama());
                    editTextAlamat.setText(dataPemesan.getAlamat());
                    editTextNoTelepon.setText(dataPemesan.getTelepon());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                circleImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
