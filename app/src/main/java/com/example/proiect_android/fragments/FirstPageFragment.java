package com.example.proiect_android.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proiect_android.R;
import com.example.proiect_android.activities.AddTransactionActivity;
import com.example.proiect_android.activities.MainActivity;
import com.example.proiect_android.activities.ShowTransactions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FirstPageFragment extends Fragment {

    private Context context;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        View view = inflater.inflate(R.layout.main_page, container, false);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.button_add_transaction);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), AddTransactionActivity.class);
                startActivity(intent);
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
        return view;
    }

}


