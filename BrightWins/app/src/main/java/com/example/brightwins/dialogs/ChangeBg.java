package com.example.brightwins.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.brightwins.MainActivity;
import com.example.brightwins.MainInterface;
import com.example.brightwins.R;

public class ChangeBg extends DialogFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private Activity mainActivity;
    private MainInterface interface1;
    RadioGroup radioGroup;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        interface1 = (MainInterface) context;
    }

    public ChangeBg(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

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

    @Override
    public void onClick(View v) {

    }
}
