package com.example.a68.intentsapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.a68.intentsapplication.PersonActivity.TELEFONO;
import static com.example.a68.intentsapplication.PersonActivity.WEB;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Person> arrayListPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        ListView listView = (ListView) findViewById(R.id.listView);
        arrayListPerson = new ArrayList<>();
        arrayListPerson.add(new Person("Google", 1111, "http://www.google.com/"));
        arrayListPerson.add(new Person("Twitter", 2222, "http://www.twitter.com/"));
        arrayListPerson.add(new Person("Facebook", 3333, "http://www.facebook.com/"));
        arrayListPerson.add(new Person("Yahoo", 4444, "http://www.yahoo.com/"));
        arrayListPerson.add(new Person("Emergencia", 123, "http://www.google.com/"));
        ArrayList<String> arrayList = new ArrayList<>();
        for (Person person : arrayListPerson) {
            arrayList.add(person.getNombre());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);

        final String[] permissions =
                new String[]{Manifest.permission.CALL_PHONE};

        ActivityCompat.requestPermissions(this, permissions,
                1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, PersonActivity.class);
        intent.putExtra("obj", arrayListPerson.get(position));
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String datos = data.getExtras().getString("data");
        Log.i(TAG, "datos: " + datos);
        switch (resultCode) {
            case TELEFONO:
                Intent in = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:" + datos)
                );
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                startActivity(in);
                break;
            case WEB:
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(datos)
                        );
                startActivity(i);
                break;
        }
    }
}
