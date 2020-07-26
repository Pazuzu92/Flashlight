package com.example.fruitturn;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btnReader = findViewById(R.id.list_btn);
        ImageView btnWheel = findViewById(R.id.wheel_btn);

        btnReader.setOnClickListener(this);
        btnWheel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_btn:
                Intent intent = new Intent(this, ActivityViewPager.class);
                startActivity(intent);
                break;
            case R.id.wheel_btn:
                Intent intent1 = new Intent(this, WheelActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
