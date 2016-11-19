package com.example.a68.listapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * Created by 68 on 19/11/2016.
 **/

public class ListaActivity extends ListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] ciudades = {"bogota", "cali", "medellin", "pasto"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, ciudades);
        setListAdapter(arrayAdapter);
    }
}
