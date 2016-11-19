package com.example.a68.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_HANDLE_CAMERA_PERM = 2;
    private Camera camera;
    private boolean isFlash;
    private Camera.Parameters param;
    private boolean isFlashOn;


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

        int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            getCamera();
        } else {
            requestCameraPermission();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Aplicacion destruida");
    }

    private void getCamera() {
        if (camera == null){
            camera = Camera.open();
            param = camera.getParameters();
        }else{
            Log.e(TAG, "Error de camara");
        }
    }

    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || param == null) return;
            param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(param);
            camera.startPreview();
            isFlashOn = true;
            Log.d(TAG, "El flash ha sido prendido ...");
        }
    }

    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || param == null) return;
            param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(param);
            camera.stopPreview();
            isFlashOn = false;
            Log.d(TAG, "El flash ha sido aoagado ...");
        }
    }

    private void requestCameraPermission() {

        final String[] permissions = new String[]{Manifest.permission.CAMERA};

        /*if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
            return;
        }*/

        ActivityCompat.requestPermissions(this, permissions,
                        RC_HANDLE_CAMERA_PERM);
    }

}
