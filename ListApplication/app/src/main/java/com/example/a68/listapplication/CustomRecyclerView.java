package com.example.a68.listapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 68 on 26/11/2016.
 **/

public class CustomRecyclerView extends RecyclerView.Adapter<CustomRecyclerView.ViewHolder>{

    private ArrayList<String> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomRecyclerView(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recycler_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public final View mView;
        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTextView = (TextView) view.findViewById(R.id.txt);
        }
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String txt = mDataset.get(position);
        holder.mTextView.setText(txt);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
