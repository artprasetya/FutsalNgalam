package com.example.asus.futsalngalam.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.futsalngalam.R;

public class ReviewActivity extends Activity {

    private Button ulasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Button ulasan = (Button) findViewById(R.id.ulasan);

        ulasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReviewActivity.this, SubmitReviewActivity.class));
            }
        });
    }
}
