package com.example.asus.futsalngalam.MenuUtama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.asus.futsalngalam.MenuAutentikasi.LoginActivity;
import com.example.asus.futsalngalam.MenuBeranda.HomeActivity;
import com.example.asus.futsalngalam.MenuPemesanan.PesananActivity;
import com.example.asus.futsalngalam.MenuProfil.ProfilActivity;
import com.example.asus.futsalngalam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    GridView androidGridView;

    String[] gridViewString = {
            "Profil",
            "Beranda",
            "Pesanan",
            "Keluar"};

    int[] gridViewImageId = {
            R.drawable.profil,
            R.drawable.lapangan,
            R.drawable.pesanan,
            R.drawable.keluar};

    private FirebaseAuth auth;
    private String idPemesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        idPemesan = user.getUid();

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(MainActivity.this, gridViewString, gridViewImageId);
        androidGridView = (GridView) findViewById(R.id.grid_view);
        androidGridView.setAdapter(adapterViewAndroid);


        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                switch (i) {
                    case 0:
                        Intent a = new Intent(view.getContext(), ProfilActivity.class);
                        view.getContext().startActivity(a);
                        break;
                    case 1:
                        Intent b = new Intent(view.getContext(), HomeActivity.class);
                        view.getContext().startActivity(b);
                        break;
                    case 2:
                        Intent c = new Intent(view.getContext(), PesananActivity.class);
                        view.getContext().startActivity(c);
                        break;
                    case 3:
                        signOut();
                        break;
                }
                Toast.makeText(MainActivity.this, "  " + gridViewString[+i], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signOut() {
        auth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
