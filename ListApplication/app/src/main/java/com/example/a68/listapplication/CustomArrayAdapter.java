package com.example.a68.listapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 68 on 26/11/2016.
 **/

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> dataSet;

    public CustomArrayAdapter(Context context, ArrayList<String> dataSet) {
        super(context, 0, dataSet);
        mContext = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView txtView = (TextView) view.findViewById(android.R.id.text1);
        txtView.setText(dataSet.get(position));

        TextView txtViewCargo = (TextView) view.findViewById(android.R.id.text2);
        txtViewCargo.setText("cargo: " + dataSet.get(position));
        return view;
    }
}
