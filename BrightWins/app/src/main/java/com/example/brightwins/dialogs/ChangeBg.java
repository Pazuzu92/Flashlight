package com.example.brightwins.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.brightwins.MainActivity;
import com.example.brightwins.MainInterface;
import com.example.brightwins.R;

public class ChangeBg extends DialogFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private MainInterface interface1;
    private RadioGroup radioGroup;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        interface1 = (MainInterface) context;
    }

    public ChangeBg(MainActivity mainActivity) {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View v = inflater.inflate(R.layout.dialog, null);

        radioGroup = v.findViewById(R.id.rgGravity);
        radioGroup.setOnCheckedChangeListener(this);
        return v;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb1:
                interface1.changeBg(R.drawable.bg_1);
                break;
            case R.id.rb2:
                interface1.changeBg(R.drawable.bg_2);
                break;
            case R.id.rb3:
                interface1.changeBg(R.drawable.bg_3);
                break;
            default:
                break;
        }
    }

    public void onResume(){
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, 1200);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onClick(View v) {

    }
}
