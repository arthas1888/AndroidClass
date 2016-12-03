package com.example.a68.viewapplication.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a68.viewapplication.R;
import com.example.a68.viewapplication.views.CustomView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomViewFragment extends Fragment {


    public CustomViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_view, container, false);
        final CustomView customView = (CustomView) view.findViewById(R.id.customView);
        customView.setLabelColor(Color.BLUE);
        customView.setLabelText("Esto es una vista personalizada");
        customView.setSquareColor(Color.YELLOW);
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.setLabelColor(Color.YELLOW);
                customView.setSquareColor(Color.BLUE);
            }
        });
        return view;
    }

}
