package com.example.a68.intentsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity
        implements View.OnClickListener{

    private static final String TAG = PersonActivity.class.getSimpleName();

    public static final int TELEFONO = 0;
    public static final int WEB = 1;
    private TextView telefonoTextView;
    private TextView webTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            Person person = (Person) extras.getSerializable("obj");
            Log.e(TAG, person.getNombre() + " " + person.getTelefono());

            TextView nombreTextView = (TextView) findViewById(R.id.textViewNombre);
            telefonoTextView = (TextView) findViewById(R.id.textViewTelefono);
            webTextView = (TextView) findViewById(R.id.textViewWeb);

            nombreTextView.setText(person.getNombre());
            telefonoTextView.setText(person.getTelefono() + "");
            webTextView.setText(person.getWeb());

            telefonoTextView.setOnClickListener(this);
            webTextView.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        switch (v.getId()){
            case R.id.textViewTelefono:
                i.putExtra("data", telefonoTextView.getText().toString());
                setResult(TELEFONO, i);
                break;
            case R.id.textViewWeb:
                i.putExtra("data", webTextView.getText().toString());
                setResult(WEB, i);
                break;
        }
        finish();
    }

}
