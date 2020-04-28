package com.example.proiect_android.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proiect_android.R;

public class TimePickerFragment extends Fragment {

    private TimePicker timePicker;
    private Button buttonSaveAlarm;
    private Button buttonAbort;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_picker, container, false);

        final FragmentManager fragmentManager = getFragmentManager();
        final FirstPageFragment firstPageFragment = (FirstPageFragment) fragmentManager.findFragmentById(R.id.container);
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();

        buttonAbort = (Button) view.findViewById(R.id.button_abort);
        buttonSaveAlarm = (Button) view.findViewById(R.id.button_save_alarm);
        buttonSaveAlarm.setEnabled(false);

        buttonAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.remove(TimePickerFragment.this).commit();
            }
        });

        timePicker = view.findViewById(R.id.time_picker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                firstPageFragment.setMinute(minute);
                firstPageFragment.setHour(hourOfDay);

                buttonSaveAlarm.setEnabled(true);
                buttonSaveAlarm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firstPageFragment.saveAlarm();
                        transaction.remove( TimePickerFragment.this).commit();
                    }
                });

            }
        });


        return view;
    }
}
