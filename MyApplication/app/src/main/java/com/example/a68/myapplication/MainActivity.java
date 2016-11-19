package com.example.a68.myapplication;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Camera camera;
    private boolean isFlash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!isFlash){
            Log.e(TAG, "NO HAY FLASH");
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Alerta")
                    .setCancelable(false)
                    .setMessage("Su dispositivo no posee Flash")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) turnOnFlash();
                else turnOffFlash();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCamera();
    }

    private void getCamera() {

    }

    private void turnOnFlash() {

    }

    private void turnOffFlash() {

    }

}
