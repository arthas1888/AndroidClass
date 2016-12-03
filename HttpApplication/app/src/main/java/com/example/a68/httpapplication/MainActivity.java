package com.example.a68.httpapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private MyPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myPreferences = new MyPreferences(this);
        if (!myPreferences.getUsername().isEmpty()){
            launchHttpActivity();
        }

        editText = (EditText) findViewById(R.id.usernameEditText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText.getText().toString();
                if (username.isEmpty())
                    Toast.makeText(MainActivity.this, "Campo obligatorio", Toast.LENGTH_LONG).show();
                else{
                    myPreferences.setUsername(username);
                    Toast.makeText(MainActivity.this, "Usuario logueado exitosamente", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            launchHttpActivity();
                        }}, 2000);
                }
            }
        });


    }

    private void launchHttpActivity() {
        Intent intent = new Intent(this, HttpActivity.class);
        startActivity(intent);
        finish();
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
}
