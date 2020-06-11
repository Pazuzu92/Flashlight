package com.example.taskflashlight1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView btn_flashlight = findViewById(R.id.btn_flashlight);
        ImageView btn_screen = findViewById(R.id.btn_screen);

        btn_flashlight.setOnClickListener(this);
        btn_screen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_screen:
                Intent intent = new Intent(this, ScreenActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_flashlight:
                if (checkPermission()) {
                    Intent intent1 = new Intent(this, FlashlightActivity.class);
                    startActivity(intent1);

                } else {
                    requestPermission();
                }
                break;
            default:
                break;
        }
    }

    private boolean checkPermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                return false;
            }
            return true;
        }

        private void requestPermission() {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CODE);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
            switch (requestCode) {
                case PERMISSION_REQUEST_CODE:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(this, FlashlightActivity.class);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                    != PackageManager.PERMISSION_GRANTED) {
                                showMessageOKCancel("You need to allow access permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermission();
                                                }
                                            }
                                        });
                            }
                        }
                    }
                    break;
            }
        }

        private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(message)
                    .setPositiveButton("OK", okListener)
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        }

}
