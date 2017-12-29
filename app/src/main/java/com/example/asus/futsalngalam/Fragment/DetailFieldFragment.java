package com.example.asus.futsalngalam.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asus.futsalngalam.Activity.BookActivity;
import com.example.asus.futsalngalam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFieldFragment extends Fragment {

    private Button btn;

    public static DetailFieldFragment newInstance() {
        return new DetailFieldFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_field, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn = (Button) view.findViewById(R.id.book);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}