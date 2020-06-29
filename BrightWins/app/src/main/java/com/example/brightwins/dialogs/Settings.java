package com.example.brightwins.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.brightwins.BuildConfig;
import com.squareup.seismic.ShakeDetector;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.DialogFragment;

import com.example.brightwins.MainActivity;
import com.example.brightwins.MainInterface;
import com.example.brightwins.R;

import static android.content.Context.SENSOR_SERVICE;

public class Settings extends DialogFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ShakeDetector.Listener {

    public static int SHAKE = 0;
    public static int SWIPE = 1;

    private Activity mainActivity;
    private MainInterface interface1;
    RadioGroup rg;
    SensorManager sm;
    ShakeDetector shakeDetector;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        interface1 = (MainInterface) context;
    }

    public Settings(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View v = inflater.inflate(R.layout.settings, null);
        v.findViewById(R.id.btn_share).setOnClickListener(this);
        v.findViewById(R.id.change_bg).setOnClickListener(this);
        rg = v.findViewById(R.id.rg_settings);
        rg.setOnCheckedChangeListener(this);
        sm = (SensorManager) mainActivity.getSystemService(SENSOR_SERVICE);
        shakeDetector = new ShakeDetector(this);



        return v;

    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btn_1:
                interface1.setFlag(SHAKE);
                break;
            case R.id.btn_2:
                interface1.setFlag(SWIPE);
                break;
            default:
                break;
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                ShareCompat.IntentBuilder.from(mainActivity)
                        .setType("text/plain")
                        .setChooserTitle("Share URL")
                        .setText("http://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
                        .startChooser();
                break;
            case R.id.change_bg:
                interface1.callDialogChangeBg();
                break;
            default:
                break;
        }
    }


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void hearShake() {

    }
}
