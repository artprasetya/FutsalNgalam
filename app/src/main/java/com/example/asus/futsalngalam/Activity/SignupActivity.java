package com.example.asus.futsalngalam.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.futsalngalam.API.RegisterAPI;
import com.example.asus.futsalngalam.API.RetrofitServer;
import com.example.asus.futsalngalam.Model.Value;
import com.example.asus.futsalngalam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends Activity {

    private EditText inputName, inputEmail, inputUsername, inputPassword, inputPhone;
    private TextView gotoLogin;
    private FirebaseAuth auth;
    private Button btnSignUp;
    private ProgressDialog pd;


//    public static final String URL = "http://192.168.1.8/penyewa/insert.php";
//    String id, nama, email, password, telepon;
//
//    @BindView(R.id.etID) EditText etID;
//    @BindView(R.id.etName) EditText etName;
//    @BindView(R.id.etEmail) EditText etEmail;
//    @BindView(R.id.etPassword) EditText etPassword;
//    @BindView(R.id.etPhone) EditText etPhone;
//
//    @OnClick(R.id.btn_signup) void signup(){
//        id = etID.getText().toString();
//        nama = etName.getText().toString();
//        email = inputEmail.getText().toString();
//        password = inputPassword.getText().toString();
//        telepon = etPhone.getText().toString();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RegisterAPI api = retrofit.create(RegisterAPI.class);
//        Call<Value> call = api.signup(id,nama,email,password,telepon);
//
//        call.enqueue(new Callback<Value>() {
//            @Override
//            public void onResponse(Call<Value> call, Response<Value> response) {
//                String value = response.body().getValue();
//                String message = response.body().getMessage();
//
//                if(value.equals("1")){
//                    Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Value> call, Throwable t) {
//                t.printStackTrace();
//
//                Toast.makeText(SignupActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        gotoLogin = (TextView) findViewById(R.id.signin1);
        inputName = (EditText) findViewById(R.id.etName);
        inputEmail = (EditText) findViewById(R.id.etEmail);
        inputUsername = (EditText) findViewById(R.id.etID);
        inputPassword = (EditText) findViewById(R.id.etPassword);
        inputPhone = (EditText) findViewById(R.id.etPhone);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
        pd = new ProgressDialog(this);

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd.setMessage("Sending Data...");
                pd.setCancelable(false);
                pd.show();

                String nama = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();
                String telepon = inputPhone.getText().toString();

                RegisterAPI api = RetrofitServer.getClient().create(RegisterAPI.class);

                Call<Value> call = api.signup(nama,email, username, password,telepon);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        pd.hide();
                        Log.d("RETRO", " response : " + response.body().toString());
                        String value = response.body().getValue();

                        if (value.equals("1")){
                            Toast.makeText(SignupActivity.this, "Data berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignupActivity.this, "Data gagal Disimpan", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Failure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignupActivity.this, "Authentication Success." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}