package com.example.asus.futsalngalam.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.futsalngalam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {


    public static ReviewFragment newInstance(){
        return new ReviewFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false);
    }

}
