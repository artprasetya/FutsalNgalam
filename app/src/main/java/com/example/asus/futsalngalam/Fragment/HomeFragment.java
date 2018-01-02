package com.example.asus.futsalngalam.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.futsalngalam.Activity.DetailFieldActivity;
import com.example.asus.futsalngalam.R;
import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private DatabaseReference databaseReference;
    private TextView textView1, textView2, textView3;
    private Button button1, button2, button3;
    private CardView cardView1, cardView2, cardView3;
    private RecyclerView recyclerView;
    private DatabaseReference myref;
    private Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


//        View rootView = inflater.inflate(R.layout.fragment_detail_field, container, false);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        myref = FirebaseDatabase.getInstance().getReference().child("/tempat futsal");
//        FirebaseRecyclerAdapter<TempatFutsal, TempatFutsalHolder> recyclerAdapter = new FirebaseRecyclerAdapter<TempatFutsal, TempatFutsalHolder>(TempatFutsal.class, R.layout.individual_row, TempatFutsalHolder.class, myref) {
//            @Override
//            protected void populateViewHolder(TempatFutsalHolder viewHolder, TempatFutsal model, int position) {
//                viewHolder.setNama(model.getNama());
//                viewHolder.setImageUrl(model.getImageUrl());
//            }
//        };
//        recyclerView.setAdapter(recyclerAdapter);
//        return view;
//    }
//}


        textView1 = (TextView) rootView.findViewById(R.id.textView1);
        textView2 = (TextView) rootView.findViewById(R.id.textView2);
        textView3 = (TextView) rootView.findViewById(R.id.textView3);
        cardView1 = (CardView) rootView.findViewById(R.id.championTidar);
        cardView2 = (CardView) rootView.findViewById(R.id.zonaSM);
        cardView3 = (CardView) rootView.findViewById(R.id.vivaFutsal);

//        loadData1();
//        loadData2();
//        loadData3();

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

        return rootView;
    }

//    private void loadData1() {
//        databaseReference = FirebaseDatabase.getInstance().getReference("tempat futsal");
//        databaseReference.child("zona sm").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                TempatFutsal tempatFutsal = dataSnapshot.getValue(TempatFutsal.class);
//                textView1.setText(tempatFutsal.getNama());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void loadData2() {
//        databaseReference = FirebaseDatabase.getInstance().getReference("tempat futsal");
//        databaseReference.child("viva futsal").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                TempatFutsal tempatFutsal = dataSnapshot.getValue(TempatFutsal.class);
//                textView2.setText(tempatFutsal.getNama());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void loadData3() {
//        databaseReference = FirebaseDatabase.getInstance().getReference("tempat futsal");
//        databaseReference.child("champion").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                TempatFutsal tempatFutsal = dataSnapshot.getValue(TempatFutsal.class);
//                textView3.setText(tempatFutsal.getNama());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
}
