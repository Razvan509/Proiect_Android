package com.example.proiect_android.fragments;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.proiect_android.R;
import com.example.proiect_android.activities.AddTransactionActivity;
import com.example.proiect_android.activities.AuthActivity;
import com.example.proiect_android.activities.MainActivity;
import com.example.proiect_android.activities.ShowTransactions;
import com.example.proiect_android.alarmHelpers.Receiver;
import com.example.proiect_android.beans.Transaction;
import com.example.proiect_android.models.TransactionViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FirstPageFragment extends Fragment {

    private Context context;
    public static final int ADD_TRANSACTION_REQUEST = 1;
    private TransactionViewModel transactionViewModel;
    private Button logOut;
    private FirebaseAuth mAuth;
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
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.main_page, container, false);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.button_add_transaction);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AddTransactionActivity.class);
                startActivityForResult(intent,ADD_TRANSACTION_REQUEST);
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
        Button button = view.findViewById(R.id.allTransactions);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowTransactions.class);
                startActivity(intent);
            }
        });

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);

        logOut = view.findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), AuthActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TRANSACTION_REQUEST && resultCode == MainActivity.RESULT_OK){
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String description = data.getStringExtra(AddTransactionActivity.EXTRA_DESCRIPTION);
            float amount = Float.valueOf(data.getStringExtra(AddTransactionActivity.EXTRA_AMOUNT));
            int categoryId = Integer.valueOf(data.getStringExtra(AddTransactionActivity.EXTRA_CATEGORYID));

            Date transactionDate = null;
            try {
                transactionDate = format.parse(data.getStringExtra(AddTransactionActivity.EXTRA_DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setCategoryId(categoryId);
            transaction.setDescription(description);
            transaction.setTransactionDate(transactionDate);
            transaction.setUserId(getArguments().getString("userId"));

            transactionViewModel.insert(transaction);

            Toast.makeText(getActivity(), "Transaction saved", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getActivity(), "Transaction not saved", Toast.LENGTH_SHORT).show();
        }
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


