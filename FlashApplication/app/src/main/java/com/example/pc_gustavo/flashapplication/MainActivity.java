package com.example.pc_gustavo.flashapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_HANDLE_CAMERA_PERM = 3;
    private ToggleButton toggleButton;
    private Camera camera;
    private Camera.Parameters param;
    private boolean isFlashOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        boolean isFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!isFlash){
            Log.e(TAG, "Este dispositivo no tiene flash");
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Advertencia")
                    .setMessage("Este dispositivo no tiene flash, por tanto la app no funciona.")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setCancelable(false);
            builder.create().show();
            return;
        }

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) flashOn();
                else flashOff();
            }
        });
    }

    private void flashOff() {
        if (isFlashOn) {
            if (camera == null || param == null) return;
            param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(param);
            camera.stopPreview();
            isFlashOn = false;
            Log.d(TAG, "El flash ha sido apagado ...");
        }
    }

    private void flashOn() {
        if (!isFlashOn) {
            if (camera == null || param == null) return;
            param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(param);
            camera.startPreview();
            isFlashOn = true;
            Log.d(TAG, "El flash ha sido prendido ...");
        }
    }

    private void requestCameraPermission() {
        final String[] permissions = new String[]{Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions(this, permissions,
                RC_HANDLE_CAMERA_PERM);
    }

    private void getCamera() {
        if (camera == null){
            camera = Camera.open();
            param = camera.getParameters();
        }else{
            Log.e(TAG, "Error de camara");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null){
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            getCamera();
        } else {
            requestCameraPermission();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFlashOn) {
            isFlashOn = false;
            flashOn();
        }
    }
}
