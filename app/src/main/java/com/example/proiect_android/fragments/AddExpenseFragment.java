package com.example.proiect_android.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proiect_android.R;

import java.util.Calendar;

public class AddExpenseFragment extends Fragment implements View.OnClickListener{
    private Button datePicker;
    private TextView textDate;
    private int mYear,mMonth,mDay;
    private View view;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_expense, container, false);
        datePicker = view.findViewById(R.id.button_select_date_expense);
        textDate = view.findViewById(R.id.text_date_expense);
        datePicker.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                },mYear,mMonth,mDay);
        datePickerDialog.show();
    }
}
