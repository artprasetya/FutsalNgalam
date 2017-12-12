package com.example.asus.futsalngalam.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.futsalngalam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

//    private static final int CHOOSE_IMAGE = 101;
//    private ImageView imageView;
    private TextView tvEmail;
    private EditText editTextProfile, editTextPhone;
    private Button btsave;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
//    StorageReference storageReference;

//    int Image_Request_Code = 7;

    private String idPenyewa;
    private String emailPenyewa;

//    Uri uriProfileImage;
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

        firebaseAuth = FirebaseAuth.getInstance();
//        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("penyewa");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        idPenyewa = user.getUid();
        emailPenyewa = user.getEmail();

//        imageView = (ImageView) findViewById(R.id.profile_image);
        editTextProfile = (EditText) findViewById(R.id.etName);
        editTextPhone = (EditText) findViewById(R.id.etPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        btsave = (Button) findViewById(R.id.btsave);

        tvEmail.setText(emailPenyewa);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showImageChooser();
//            }
//        });

        btsave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btsave){
            simpanDataPenyewa();
            finish();
        }
    }

    private void simpanDataPenyewa(){
        String nama = editTextProfile.getText().toString().trim();
        String telepon = editTextPhone.getText().toString().trim();

        if(nama.isEmpty()){
            editTextProfile.setError("Wajib diisi");
        }
        if(telepon.isEmpty()){
            editTextProfile.setError("Wajib diisi");
        }

        databaseReference.child(idPenyewa).child("Nama").setValue(nama);
        databaseReference.child(idPenyewa).child("Telepon").setValue(telepon);
        Toast.makeText(this, "Data saved.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
//            uriProfileImage = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
//                imageView.setImageBitmap(bitmap);
//                uploadImage();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
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

//    public String GetFileExtension(Uri uri) {
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        // Returning the file Extension.
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
//    }

//    private void showImageChooser(){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Photo"), CHOOSE_IMAGE);
//    }
}
