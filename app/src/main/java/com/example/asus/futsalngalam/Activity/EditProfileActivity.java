package com.example.asus.futsalngalam.Activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.futsalngalam.Model.Penyewa;
import com.example.asus.futsalngalam.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CHOOSE_IMAGE = 101;
    private ImageView imageView;
    private TextView tvEmail;
    private EditText editTextProfile, editTextPhone;
    private Button btsave, selectImage;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    StorageReference storageReference;

//    int Image_Request_Code = 7;

    private String idPenyewa;
    private String emailPenyewa;

    Uri uriProfileImage;
//    String profileImageUrl;

//    // Folder path for Firebase Storage.
//    String Storage_Path = "All_Image_Uploads/";
//
//    // Root Database Name for Firebase Database.
//    String Database_Path = "All_Image_Uploads_Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imageView = (ImageView) findViewById(R.id.profile_image);
        selectImage = (Button) findViewById(R.id.btn_select);
        editTextProfile = (EditText) findViewById(R.id.etName);
        editTextPhone = (EditText) findViewById(R.id.etPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        btsave = (Button) findViewById(R.id.btsave);

        loadData();

        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("penyewa");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        tvEmail.setText(emailPenyewa);

        idPenyewa = user.getUid();
        emailPenyewa = user.getEmail();

        selectImage.setOnClickListener(this);
        btsave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btsave){
            simpanDataPenyewa();
            finish();
        } else if (v == selectImage) {
            showImageChooser();
        }
    }

    private void simpanDataPenyewa(){
        StorageReference sRef = storageReference.child("Upload/"+ System.currentTimeMillis() + "." + getFileExtension(uriProfileImage));
        if (uriProfileImage != null) {
            sRef.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String nama = editTextProfile.getText().toString();
                    String telepon = editTextPhone.getText().toString();

                    if(nama.isEmpty()){
                        editTextProfile.setError("Wajib diisi");
                    } else if (telepon.isEmpty()){
                        editTextProfile.setError("Wajib diisi");
                    } else {
                        databaseReference.child("penyewa").child(idPenyewa).child("Nama").setValue(nama);
                        databaseReference.child("penyewa").child(idPenyewa).child("Telepon").setValue(telepon);
                        databaseReference.child("penyewa").child(idPenyewa).child("uriProfileImage").setValue(taskSnapshot.getDownloadUrl().toString());
                        Toast.makeText(getApplicationContext(), "Biodata Anda Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }

                }
            });
        }
    }

    //    private void uploadImage() {
//        StorageReference profileImageRef = FirebaseStorage.getInstance().getReference("profileImg/"+System.currentTimeMillis() + ".jpg");
//        if(uriProfileImage != null) {
//            profileImageRef.putFile(uriProfileImage)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    profileImageUrl = taskSnapshot.getDownloadUrl().toString();
//
//                }})
//                    .addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }

    private void loadData() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("penyewa").child(idPenyewa).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Penyewa penyewa = dataSnapshot.getValue(Penyewa.class);
                if (penyewa != null) {
                    editTextProfile.setText(penyewa.getNama());
                    tvEmail.setText(emailPenyewa);
                    editTextPhone.setText(penyewa.getTelepon());
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
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriProfileImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                imageView.setImageBitmap(bitmap);
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

    private void showImageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Photo"), CHOOSE_IMAGE);
    }
}
