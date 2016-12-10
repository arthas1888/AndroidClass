package com.example.a68.menuapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int VIEW_IMAGE = 1;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));
        registerForContextMenu(gridView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Opciones");
        AdapterView.AdapterContextMenuInfo cmi =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(VIEW_IMAGE, cmi.position, 0, "Ver imagen");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Integer resourceId = (Integer)
                gridView.getItemAtPosition(item.getItemId());
        ImageView imageView = (ImageView) findViewById(resourceId);
        //View view = gridView.getSelectedView();
        switch (item.getGroupId()) {
            case VIEW_IMAGE:
                Intent intent = new Intent(MainActivity.this, PreviewActivity.class);
                intent.putExtra("image", resourceId);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(this, gridView, "robot");
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                }
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit:
                finish();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
