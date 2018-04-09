package com.example.asus.futsalngalam.MenuBeranda;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.futsalngalam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView futsalList;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbRef = FirebaseDatabase.getInstance().getReference().child("tempatFutsal");

        futsalList = (RecyclerView) findViewById(R.id.futsal_list);
        futsalList.setHasFixedSize(true);
        futsalList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
//
//        FirebaseRecyclerAdapter<TempatFutsal,FutsalViewHolder> recyclerAdapter = new FirebaseRecyclerAdapter<TempatFutsal, FutsalViewHolder>(
//                TempatFutsal.class,
//                R.layout.futsal_row,
//                FutsalViewHolder.class,
//                dbRef
//        ) {
//            @Override
//            protected void populateViewHolder(FutsalViewHolder viewHolder, TempatFutsal model, int position) {
//                viewHolder.setNamaFutsal(model.getNamaFutsal());
//                viewHolder.setImage(getApplicationContext(), model.getImage());
//            }
//        };
//        futsalList.setAdapter(recyclerAdapter);
    }

    public static class FutsalViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public FutsalViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setNamaFutsal(String namaFutsal ){
            TextView nama_futsal = (TextView) mView.findViewById(R.id.futsal_name);
            nama_futsal.setText(namaFutsal);
        }

        public void setImage(Context ctx, String image) {
            ImageView image_futsal = (ImageView) mView.findViewById(R.id.futsal_image);
//            Picasso.with(ctx).load(image).into(image_futsal);
        }
    }
}
