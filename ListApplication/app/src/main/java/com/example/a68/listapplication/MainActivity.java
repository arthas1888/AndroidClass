package com.example.a68.listapplication;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener{

    private String[] ciudades = {"bogota", "cali", "medellin", "pasto"};
    private ArrayList<String> cities;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cities = new ArrayList<String>(Arrays.asList(ciudades));

        ListView listView = (ListView) findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, cities);

        arrayAdapter = new CustomArrayAdapter(this, cities);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(this);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        CustomRecyclerView mAdapter = new CustomRecyclerView(cities);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void add(View view) {
        cities.add("Manizales");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        cities.remove(position);
        arrayAdapter.notifyDataSetChanged();
        return false;
    }
}
