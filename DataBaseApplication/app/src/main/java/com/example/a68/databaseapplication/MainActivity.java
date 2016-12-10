package com.example.a68.databaseapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import static com.example.a68.databaseapplication.StudentDataBase.COLUMN_CAREER;
import static com.example.a68.databaseapplication.StudentDataBase.COLUMN_NAME;

public class MainActivity extends AppCompatActivity {

    private StudentDataBase studentDataBase;
    private ListView listView;
    private SimpleCursorAdapter cursorAdapter;

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
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] data = {"Ing. Sistemas", "Ing. Mecanica", "Ing. Electronica",
                "Ing. Civil", "Ing. Industrial"};
        spinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1,
                data));
        spinner.setOnItemSelectedListener(new ListenerSpinner());

        studentDataBase = new StudentDataBase(this);

        listView = (ListView) findViewById(R.id.listView);

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

    class ListenerSpinner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            Log.e("Main", "item: " + item);
            Cursor cursor = studentDataBase.getByCareer(item);
            updateList(cursor);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void updateList(Cursor cursor) {
        cursorAdapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_2, cursor,
                new String[]{COLUMN_NAME, COLUMN_CAREER},
                new int[]{android.R.id.text1, android.R.id.text2},
                1
        );
        listView.setAdapter(cursorAdapter);
        //cursorAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        studentDataBase.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
        studentDataBase.openConnection();
        Cursor cursor = studentDataBase.getAll();
        cursorAdapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_2, cursor,
                new String[]{COLUMN_NAME, COLUMN_CAREER},
                new int[]{android.R.id.text1, android.R.id.text2},
                1
        );
        listView.setAdapter(cursorAdapter);
    }
}
