package com.example.coinmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView btnOff = findViewById(R.id.btn_off);
        final ImageView btnOn = findViewById(R.id.btn_on);
        final SeekBar seekBar = findViewById(R.id.seekBar);
        final RelativeLayout colorLayout = findViewById(R.id.screen);

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOff.setVisibility(View.INVISIBLE);
                btnOn.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.VISIBLE);

            }
        });

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOff.setVisibility(View.VISIBLE);
                btnOn.setVisibility(View.INVISIBLE);
                seekBar.setVisibility(View.INVISIBLE);
                colorLayout.setBackgroundColor(getResources().getColor(R.color.colorImage));
                seekBar.setProgress(0);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int color = Color.argb(100-progress, 128, 128, 128);
                colorLayout.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
