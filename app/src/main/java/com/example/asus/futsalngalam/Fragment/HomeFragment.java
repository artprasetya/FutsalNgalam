package com.example.asus.futsalngalam.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.futsalngalam.Activity.DetailFieldActivity;
import com.example.asus.futsalngalam.Model.TempatFutsal;
import com.example.asus.futsalngalam.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private DatabaseReference databaseReference;
    private TextView textView1, textView2, textView3;
    private Button button1, button2, button3;
    private CardView cardView1, cardView2, cardView3;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        textView1 = (TextView) rootView.findViewById(R.id.textView1);
        textView2 = (TextView) rootView.findViewById(R.id.textView2);
        textView3 = (TextView) rootView.findViewById(R.id.textView3);
        cardView1 = (CardView) rootView.findViewById(R.id.championTidar);
        cardView2 = (CardView) rootView.findViewById(R.id.zonaSM);
        cardView3 = (CardView) rootView.findViewById(R.id.vivaFutsal);
        button1 = (Button) rootView.findViewById(R.id.detail1);
        button2 = (Button) rootView.findViewById(R.id.detail2);
        button3 = (Button) rootView.findViewById(R.id.detail3);

        loadData1();
        loadData2();
        loadData3();

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.getActivity(), DetailFieldActivity.class));
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.getActivity(), DetailFieldActivity.class));
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.getActivity(), DetailFieldActivity.class));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.getActivity(), DetailFieldActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.getActivity(), DetailFieldActivity.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeFragment.this.getActivity(), DetailFieldActivity.class));
            }
        });
        return rootView;
    }

    private void loadData1() {
        databaseReference = FirebaseDatabase.getInstance().getReference("tempat futsal");
        databaseReference.child("zona sm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TempatFutsal tempatFutsal = dataSnapshot.getValue(TempatFutsal.class);
                textView1.setText(tempatFutsal.getNama());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadData2() {
        databaseReference = FirebaseDatabase.getInstance().getReference("tempat futsal");
        databaseReference.child("viva futsal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TempatFutsal tempatFutsal = dataSnapshot.getValue(TempatFutsal.class);
                textView2.setText(tempatFutsal.getNama());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadData3() {
        databaseReference = FirebaseDatabase.getInstance().getReference("tempat futsal");
        databaseReference.child("champion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TempatFutsal tempatFutsal = dataSnapshot.getValue(TempatFutsal.class);
                textView3.setText(tempatFutsal.getNama());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
