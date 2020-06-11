package com.example.taskflashlight1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

public class FlashlightActivity extends AppCompatActivity implements View.OnClickListener {

    private Camera camera;
    android.hardware.Camera.Parameters parameters;
    private boolean isFlashOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashlight);

        ImageView btnOn = findViewById(R.id.btn_on);
        ImageView btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(this);

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFlashOn) {
                    setFlashLigthOn();
                } else {
                    setFlashLightOff();
                }
            }
        });

        boolean isCameraFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!isCameraFlash) {
            showCameraAlert();
        } else {
            camera = Camera.open();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    private void showCameraAlert() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.error_title)
                .setMessage(R.string.error_text)
                .setPositiveButton(R.string.exit_message, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void setFlashLigthOn() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (camera != null) {
                    parameters = camera.getParameters();

                    if (parameters != null) {
                        List supportedFlashModes = parameters.getSupportedFlashModes();

                        if (supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        } else if (supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_ON)) {
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                        } else camera = null;

                        if (camera != null) {
                            camera.setParameters(parameters);
                            camera.startPreview();
                            try {
                                camera.setPreviewTexture(new SurfaceTexture(0));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }).start();
        isFlashOn = true;
    }

    private void setFlashLightOff() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (camera != null) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(parameters);
                    camera.stopPreview();
                }
            }
        }).start();
        isFlashOn = false;
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
//        mySwitch.setChecked(false);
        isFlashOn = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (camera == null) {
            camera = Camera.open();
        }
    }
}

