package com.example.proiect_android.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.proiect_android.R;

public class DatePickerFragment extends Fragment {

    private DatePicker datePicker;
    private Button buttonSetHour;
    private Button buttonAbort;
    public TimePickerFragment timePickerFragment;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_picker, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        final FirstPageFragment firstPageFragment = (FirstPageFragment) fragmentManager.findFragmentById(R.id.container);
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();

        buttonAbort = (Button) view.findViewById(R.id.button_abort);
        buttonSetHour = (Button) view.findViewById(R.id.button_set_hour);
        buttonSetHour.setEnabled(false);

        buttonAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.remove(firstPageFragment.datePickerFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        datePicker = view.findViewById(R.id.date_picker);
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                firstPageFragment.setDay(dayOfMonth);
                firstPageFragment.setMonth(monthOfYear);
                firstPageFragment.setYear(year);


                buttonSetHour.setEnabled(true);
                buttonSetHour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timePickerFragment = new TimePickerFragment();
                        transaction.replace(R.id.time_date_container, timePickerFragment, "DATE_PICKER");
                        transaction.commit();
                    }
                });

            }
        });


        return view;
    }
}
