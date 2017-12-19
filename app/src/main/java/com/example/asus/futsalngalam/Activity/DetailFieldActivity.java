package com.example.asus.futsalngalam.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.asus.futsalngalam.Fragment.ReviewFragment;
import com.example.asus.futsalngalam.R;

public class DetailFieldActivity extends AppCompatActivity {

    private Button btnUlasan, btnPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_field);

        btnUlasan = (Button) findViewById(R.id.btn_ulasan);
        btnPesan = (Button) findViewById(R.id.btn_pesan);

        btnUlasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailFieldActivity.this, ReviewFragment.class));
            }
        });

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailFieldActivity.this, BookActivity.class));
            }
        });
    }
}
