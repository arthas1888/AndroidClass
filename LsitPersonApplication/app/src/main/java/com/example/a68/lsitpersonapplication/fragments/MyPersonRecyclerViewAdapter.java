package com.example.a68.lsitpersonapplication.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a68.lsitpersonapplication.R;
import com.example.a68.lsitpersonapplication.models.Person;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPersonRecyclerViewAdapter extends RecyclerView.Adapter<MyPersonRecyclerViewAdapter.ViewHolder> {

    private final List<Person> mValues;
    private final PersonListFragment.OnListFragmentInteractionListener mListener;

    public MyPersonRecyclerViewAdapter(List<Person> items, PersonListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNombreView.setText(mValues.get(position).getNombre());
        holder.mCargoView.setText(mValues.get(position).getCargo());
        holder.imageView.setImageResource (mValues.get(position).getImage());

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNombreView;
        public final TextView mCargoView;
        public final ImageView imageView;
        public Person mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNombreView = (TextView) view.findViewById(R.id.textViewNombre);
            mCargoView = (TextView) view.findViewById(R.id.textViewCargo);
            imageView = (ImageView) view.findViewById(R.id.image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombreView.getText() + "'";
        }
    }
}
