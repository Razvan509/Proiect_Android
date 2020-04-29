package com.example.proiect_android.Fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.proiect_android.AlarmHelpers.Receiver;
import com.example.proiect_android.R;
import com.example.proiect_android.activities.AddTransactionActivity;
import com.example.proiect_android.activities.MainActivity;

import java.util.Calendar;

public class FirstPageFragment extends Fragment {

    private Context context;
    private Button buttonSetAlarm;
    public DatePickerFragment datePickerFragment;
    private int hour;
    private int minute;
    private int year;
    private int month;
    private int day;

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();

        View view = inflater.inflate(R.layout.main_page, container, false);

        Button button = (Button) view.findViewById(R.id.button_add_transaction);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        buttonSetAlarm = (Button) view.findViewById(R.id.button_set_alarm);

        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment = new DatePickerFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.time_date_container, datePickerFragment, "DATE_PICKER");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    public void saveAlarm(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        System.out.println(getArguments());
        String title = "Verifica Tranzactiile";

        sendOnChannel(title, calendar);
    }

    public void sendOnChannel(String title, Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), Receiver.class);
        intent.putExtra("title", title);
        Toast.makeText(getContext(), "Alarm created", Toast.LENGTH_SHORT).show();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

}


