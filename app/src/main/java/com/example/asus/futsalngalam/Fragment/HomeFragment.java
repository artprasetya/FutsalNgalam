package com.example.asus.futsalngalam.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asus.futsalngalam.Activity.SlidingTab;
import com.example.asus.futsalngalam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button detail1, detail2, detail3;
    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);*/

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        context = rootView.getContext(); // Assign your rootView to context

        Button detail1 = (Button) rootView.findViewById(R.id.detail1);
        detail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context, SlidingTab.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
